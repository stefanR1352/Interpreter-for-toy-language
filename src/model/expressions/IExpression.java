package model.expressions;

import exceptions.ExpressionExcpetion;
import model.ADT.MyIMap;
import model.values.IValue;

public interface IExpression {
    IValue eval(MyIMap<String, IValue> symTabel) throws ExpressionExcpetion;
    public IExpression deepCopy();
}
