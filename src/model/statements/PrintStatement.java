package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionExcpetion;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.values.IValue;

public class PrintStatement implements IStatement{
    private IExpression expression;
    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }


    @Override
    public PrgState execute(PrgState p) throws StatementException, ExpressionExcpetion, ADTException {
        IValue v = expression.eval(p.getSymTabel(), p.getHeap());
        p.getOutlist().add(v);
        return p;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(expression.deepCopy());
    }

    public String toString() {return "print(" + expression + ")";}
}
