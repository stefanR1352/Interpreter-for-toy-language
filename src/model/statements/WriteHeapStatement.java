package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionExcpetion;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.values.IValue;
import model.values.RefValue;

import java.io.IOException;

public class WriteHeapStatement implements IStatement {
    private String varName;
    private IExpression expression;

    public WriteHeapStatement(String varName, IExpression expression) {
        this.varName = varName;
        this.expression = expression;
    }


    @Override
    public PrgState execute(PrgState p) throws StatementException, ExpressionExcpetion, ADTException, IOException {
        if(! p.getSymTabel().containsKey(varName)) {
            throw new StatementException("Variable " + varName + " not found");
        }

        IValue value = p.getSymTabel().getValue(varName);
        if(!(value instanceof RefValue)){
            throw new StatementException("Variable " + varName + " is not a reference");
        }

        int addr = ((RefValue)value).getAddress();

        if(!p.getHeap().containsKey(addr)){
            throw new StatementException("Address " + addr + " not found");
        }

        IValue expVal = expression.eval(p.getSymTabel(), p.getHeap());

        if(!expVal.getType().equals((RefValue)((RefValue) value).getLocation())){
            throw new StatementException("The types do not match!");
        }
        p.getHeap().set(addr, expVal);

        return p;
    }

    @Override
    public IStatement deepCopy() {
        return new WriteHeapStatement(varName, expression.deepCopy());
    }

    @Override
    public String toString() {
        return "WriteHeapStatement [varName=" + varName + ", expression=" + expression + "]";
    }
}
