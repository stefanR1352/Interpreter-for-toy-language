package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionExcpetion;
import model.ADT.MyIHeap;
import model.ADT.MyIMap;
import model.values.IValue;

public interface IExpression {
    IValue eval(MyIMap<String, IValue> symTabel, MyIHeap heap) throws ExpressionExcpetion, ADTException;
    public IExpression deepCopy();
}
