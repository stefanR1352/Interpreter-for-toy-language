package controller;

import exceptions.ADTException;
import exceptions.EmptyStackException;
import exceptions.RepoException;
import model.ADT.MyIStack;
import model.garbage.GarbageCollector;
import model.state.PrgState;
import model.statements.IStatement;
import repo.IRepo;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller implements IController {
    IRepo repo;
    boolean displayFlag = false;
    ExecutorService executor;

    public Controller(IRepo repo) {
        this.repo = repo;
    }

    @Override
    public void oneStepForAllPrg(List<PrgState> states) throws InterruptedException {
        //print the prgList in the log file
        states.forEach(state -> {
            try {
                repo.logPrg(state);
            } catch (RepoException e) {
                throw new RuntimeException(e);
            }
        });

        List<Callable<PrgState>> callList = states.stream().map((PrgState p) -> (Callable<PrgState>) (p::oneStep)).collect(Collectors.toList());

        List<PrgState> newPrgList = executor.invokeAll(callList).stream().map(prgStateFuture -> {
            try {
                return prgStateFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());

        states.addAll(newPrgList);

        if (displayFlag) {
            states.forEach(this::displayCrtState);
        }

        //print the final prgState in the logFile
        states.forEach(prgState -> {
            try {
                repo.logPrg(prgState);
            } catch (RepoException e) {
                throw new RuntimeException(e);
            }
        });

        //save the current programs in the repo
        repo.setPrglist(states);
    }

    @Override
    public void allSteps() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> finalPrgStates = null;

        //remove the completed programs
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());

        while (!prgList.isEmpty()) {
            oneStepForAllPrg(prgList);
            finalPrgStates = prgList;
            prgList = removeCompletedPrg(repo.getPrgList());
        }

        executor.shutdownNow();
        repo.setPrglist(prgList);

        if (!displayFlag && finalPrgStates != null && !finalPrgStates.isEmpty()) {
            System.out.println("Final Program State(s):");
            finalPrgStates.forEach(this::displayCrtState);
        }
    }

    public void displayCrtState(PrgState state) {

        System.out.println(state.toString());
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream().filter(PrgState::isNotCompleted).collect(Collectors.toList());
    }

    public void setDisplayFlag(Boolean flag) {
        this.displayFlag = flag;
    }

}
