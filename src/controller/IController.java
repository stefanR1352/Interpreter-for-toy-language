package controller;

import model.state.PrgState;

public interface IController {
    PrgState oneStep(PrgState state) throws Exception;
    void allSteps() throws Exception;
}
