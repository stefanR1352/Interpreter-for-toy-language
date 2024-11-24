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

public class Controller implements IController {
    IRepo repo;
    boolean displayFlag = false;

    public Controller(IRepo repo) {
        this.repo = repo;
    }

    @Override
    public PrgState oneStep(PrgState state) throws EmptyStackException, ADTException, IOException {
        MyIStack<IStatement> stk = state.getExeStack();
        IStatement st = stk.pop();
        return st.execute(state);
    }

    @Override
    public void allSteps() throws EmptyStackException, ADTException, RepoException, IOException {
        PrgState prgState = repo.getCurrentPrg();
        if(displayFlag) {
            displayCrtState(prgState);
        }
        while(!prgState.getExeStack().isEmpty()){
            oneStep(prgState);
            repo.logPrg();

            //Garbage Collector
            List<Integer> symTableAddresses = GarbageCollector.getAddrFromSymTable(prgState.getSymTabel().getKeys().stream().map(key -> {
                try {
                    return prgState.getSymTabel().getValue(key);
                } catch (Exception e) {
                    return null;
                }
            }).filter(Objects::nonNull).toList()
            );

            prgState.getHeap().setContent(GarbageCollector.safeGarbageCollector(symTableAddresses, prgState.getHeap().getHeap()));

            if(displayFlag) {
                displayCrtState(prgState);
            }

        }
        if(!displayFlag) {
            displayCrtState(prgState);
        }
    }

    public PrgState returnCrtState(){
        return repo.getCurrentPrg();
    }

    public void displayCrtState(PrgState state){

        System.out.println(state.toString());
    }

    public void setDisplayFlag(Boolean flag){
        this.displayFlag = flag;
    }

}
