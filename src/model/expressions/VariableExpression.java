package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionExcpetion;
import model.ADT.MyIHeap;
import model.ADT.MyIMap;
import model.values.IValue;

import java.beans.Expression;

public class VariableExpression implements IExpression {
    private String variable;

    public VariableExpression(String variable) {
        this.variable = variable;
    }

    @Override
    public IValue eval(MyIMap<String, IValue> symTabel, MyIHeap heap) throws ExpressionExcpetion {
        if(!symTabel.containsKey(variable)){
            throw new ExpressionExcpetion("Variable " + variable + " not found");
        }
        try{
            return symTabel.getValue(variable);
        } catch (ADTException e){
            throw new ExpressionExcpetion(e.getMessage());
        }
    }

    @Override
    public IExpression deepCopy() {
        return new VariableExpression(variable);
    }

    public String toString(){
        return variable;
    }
}
