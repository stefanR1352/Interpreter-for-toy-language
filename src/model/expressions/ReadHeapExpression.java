package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionExcpetion;
import model.ADT.MyIHeap;
import model.ADT.MyIMap;
import model.values.IValue;
import model.values.RefValue;

public class ReadHeapExpression implements IExpression {
    private IExpression expression;

    public ReadHeapExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public IValue eval(MyIMap<String, IValue> symTabel, MyIHeap heap) throws ExpressionExcpetion, ADTException {
        IValue value = expression.eval(symTabel, heap);
        if (!(value instanceof RefValue)) {
            throw new ExpressionExcpetion("Value is not a RefValue");
        }
        RefValue refValue = (RefValue) value;
        return heap.getValue(refValue.getAddress());
    }

    @Override
    public IExpression deepCopy() {
        return new ReadHeapExpression(expression.deepCopy());
    }

    @Override
    public String toString() {
        return "Read expression( " + expression.toString() + ") ";
    }
}
