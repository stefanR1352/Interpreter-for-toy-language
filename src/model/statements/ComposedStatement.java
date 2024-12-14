package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionExcpetion;
import exceptions.StatementException;
import model.ADT.MyIStack;
import model.state.PrgState;

public class ComposedStatement implements IStatement {
    IStatement firstStatement;
    IStatement secondStatement;

    public ComposedStatement(IStatement firstStatement, IStatement secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    @Override
    public PrgState execute(PrgState p) throws StatementException, ExpressionExcpetion, ADTException {
        MyIStack<IStatement> stk = p.getExeStack();
        stk.push(secondStatement);
        stk.push(firstStatement);
        return null;
    }

    @Override
    public String toString() {
        return firstStatement + "; " + secondStatement;
    }

    @Override
    public IStatement deepCopy() {
        return new ComposedStatement(firstStatement.deepCopy(), secondStatement.deepCopy());
    }
}
