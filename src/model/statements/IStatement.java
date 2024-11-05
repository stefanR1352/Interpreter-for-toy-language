package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionExcpetion;
import exceptions.StatementException;
import model.state.PrgState;

public interface IStatement {
    public PrgState execute(PrgState p) throws StatementException, ExpressionExcpetion, ADTException;

    public IStatement deepCopy();
}
