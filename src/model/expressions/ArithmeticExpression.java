package model.expressions;

import exceptions.ExpressionExcpetion;
import model.ADT.MyIMap;
import model.statements.IStatement;
import model.values.IValue;

public class ArithmeticExpression implements IExpression {

    private IStatement left;

    @Override
    public IValue eval(MyIMap<String, IValue> symTabel) throws ExpressionExcpetion {
        return null;
    }

    @Override
    public IExpression deepCopy() {
        return new ArithmeticExpression(left.deepCopy(), operator, right.deepCopy());
    }
}
