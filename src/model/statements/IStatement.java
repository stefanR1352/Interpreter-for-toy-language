package model.statements;

import exceptions.ExpressionExcpetion;
import exceptions.StatementException;
import model.state.PrgState;

public interface IStatement {
    public PrgState execute(PrgState p) throws StatementException, ExpressionExcpetion;

    public IStatement deepCopy();
}
