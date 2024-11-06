package model.statements;

import exceptions.ADTException;
import exceptions.StatementException;
import model.state.PrgState;

public class NopStatement implements IStatement {
    @Override
    public PrgState execute(PrgState p) throws StatementException {
        return p;
    }
    @Override
    public IStatement deepCopy() {
        return new NopStatement();
    }
    @Override
    public String toString() {
        return "Nop";
    }
}
