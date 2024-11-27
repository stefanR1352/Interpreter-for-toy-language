package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionExcpetion;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.types.RefType;
import model.values.IValue;
import model.values.RefValue;

import java.io.IOException;

public class AllocationStatement implements IStatement {
    private String varName;
    private IExpression expression;

    public AllocationStatement(String varName, IExpression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState p) throws StatementException, ExpressionExcpetion, ADTException, IOException {
        if(!p.getSymTabel().containsKey(varName)) {
            throw new StatementException("Variable " + varName + " not declared");
        }

        IValue varValue = p.getSymTabel().getValue(varName);
        if(!(varValue.getType() instanceof RefType)) {
            throw new StatementException("Variable " + varName + " is not a reference type");
        }

        IValue value = expression.eval(p.getSymTabel(), p.getHeap());

        RefType refType = (RefType) varValue.getType();

//        if(!value.getType().equals(refType.getInnerType())) {
        if(!value.getType().equals(((RefValue)varValue).getLocation())) {
            throw new StatementException("Variable " + varName + " is not an inner type, it expects: " + ((RefValue)varValue).getLocation().toString() + " instead of " + value.getType());
        }

        int newAdress = p.getHeap().allocate(value);
        p.getSymTabel().put(varName, new RefValue(newAdress, refType.getInnerType()));

        return p;
    }

    @Override
    public IStatement deepCopy() {
        return new AllocationStatement(varName, expression.deepCopy());
    }

    @Override
    public String toString(){
        return "new( " + varName + ", " + expression.toString() + ")";
    }
}
