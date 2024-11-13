package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionExcpetion;
import exceptions.StatementException;
import model.state.PrgState;

import java.io.IOException;

public interface IStatement {
    public PrgState execute(PrgState p) throws StatementException, ExpressionExcpetion, ADTException, IOException;

    public IStatement deepCopy();
}
