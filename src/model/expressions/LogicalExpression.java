package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionExcpetion;
import model.ADT.MyIHeap;
import model.ADT.MyIMap;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.IValue;

public class LogicalExpression implements IExpression {
    private IExpression left;
    private IExpression right;
    private LogicalOperator operator;

    public LogicalExpression(IExpression left, LogicalOperator operator, IExpression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public IValue eval(MyIMap<String, IValue> symTabel, MyIHeap heap) throws ExpressionExcpetion, ADTException {
        IValue leftVal = left.eval(symTabel, heap);
        IValue rightVal = right.eval(symTabel, heap);

        if(!leftVal.getType().equals(new BoolType())){
            throw new ExpressionExcpetion("Left operand is not of type bool");
        }
        if(!rightVal.getType().equals(new BoolType())){
            throw new ExpressionExcpetion("Right operand is not of type bool");
        }

        BoolValue rightBool = (BoolValue) rightVal;
        BoolValue leftBool = (BoolValue) leftVal;
        return switch (operator) {
            case AND -> new BoolValue(leftBool.getValue() && rightBool.getValue());
            case OR -> new BoolValue(leftBool.getValue() || rightBool.getValue());
            default -> throw new ExpressionExcpetion("Unknown operator");
        };
    }

    @Override
    public IExpression deepCopy() {
        return new LogicalExpression(left.deepCopy(), operator, right.deepCopy());
    }

    @Override
    public String toString() {
        return "LogicalExpression{" +
                "left=" + left +
                ", right=" + right +
                ", operator=" + operator +
                "};\n";
    }
}
