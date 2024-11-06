package controller;

import exceptions.ADTException;
import exceptions.EmptyStackException;
import exceptions.RepoException;
import model.ADT.MyIStack;
import model.state.PrgState;
import model.statements.IStatement;
import repo.IRepo;

public class Controller implements IController {
    IRepo repo;
    boolean displayFlag = false;

    public Controller(IRepo repo) {
        this.repo = repo;
    }

    @Override
    public PrgState oneStep(PrgState state) throws EmptyStackException, ADTException {
        MyIStack<IStatement> stk = state.getExeStack();
        IStatement st = stk.pop();
        return st.execute(state);
    }

    @Override
    public void allSteps() throws EmptyStackException, ADTException, RepoException {
        PrgState prgState = repo.getCurrentPrg();
        if(displayFlag) {
            displayCrtState(prgState);
        }
        while(!prgState.getExeStack().isEmpty()){
            oneStep(prgState);
            repo.logPrg();

            if(displayFlag) {
                displayCrtState(prgState);
            }

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
