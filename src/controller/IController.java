package controller;

import model.state.PrgState;

import java.util.List;

public interface IController {
//    PrgState oneStep(PrgState state) throws Exception;
    void oneStepForAllPrg(List<PrgState> states) throws InterruptedException;
    void allSteps() throws Exception;
}
