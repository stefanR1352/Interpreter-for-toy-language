package model.expressions;

import exceptions.ExpressionExcpetion;
import model.ADT.MyIHeap;
import model.ADT.MyIMap;
import model.values.IValue;

public class ValueExpression implements IExpression {
    private IValue value;

    public ValueExpression(IValue value) { this.value = value; }

    @Override
    public IValue eval(MyIMap<String, IValue> symTabel, MyIHeap heap) throws ExpressionExcpetion {
        return value;
    }

    @Override
    public IExpression deepCopy() {
        return new ValueExpression(value);
    }

    public String toString(){ return value.toString(); }
}
