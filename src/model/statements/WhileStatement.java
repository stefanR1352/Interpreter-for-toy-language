package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionExcpetion;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.values.BoolValue;
import model.values.IValue;

import java.io.IOException;

public class WhileStatement implements IStatement {
    private final IExpression condition;
    private final IStatement body;

    public WhileStatement(IExpression condition, IStatement body) {
        this.condition = condition;
        this.body = body;
    }


    @Override
    public PrgState execute(PrgState p) throws StatementException, ExpressionExcpetion, ADTException, IOException {
        IValue conditionValue = condition.eval(p.getSymTabel(), p.getHeap());
        if(!(conditionValue instanceof BoolValue)){
            throw new StatementException("The condition is not a value");
        }

        if(((BoolValue)conditionValue).getValue() ){
            p.getExeStack().push(this);
            p.getExeStack().push(body);
        }

        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new WhileStatement(condition.deepCopy(), body.deepCopy());
    }

    @Override
    public String toString() {
        return "while (" + condition + ") " + body;
    }
}
