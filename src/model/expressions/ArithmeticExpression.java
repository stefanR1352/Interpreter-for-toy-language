package model.expressions;

import com.sun.jdi.IntegerType;
import exceptions.ExpressionExcpetion;
import model.ADT.MyIMap;
import model.statements.IStatement;
import model.types.IntType;
import model.values.IValue;
import model.values.IntValue;

public class ArithmeticExpression implements IExpression {
    private IExpression left;
    private IExpression right;
    private ArithmeticalOperator operator;

    public ArithmeticExpression(IExpression left, ArithmeticalOperator operator, IExpression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public IValue eval(MyIMap<String, IValue> symTabel) throws ExpressionExcpetion {
        IValue leftVal = left.eval(symTabel);
        IValue rightVal = right.eval(symTabel);
        if (!leftVal.getType().equals(new IntType())){
            throw new ExpressionExcpetion(leftVal + " is not an integer type");
        }
        if (!rightVal.getType().equals(new IntType())){
            throw new ExpressionExcpetion(rightVal + " is not an integer type");
        }

        IntValue leftInt = (IntValue)leftVal;
        IntValue rightInt = (IntValue)rightVal;
        return switch (operator) {
            case PLUS -> new IntValue(leftInt.getValue() + rightInt.getValue());
            case MINUS -> new IntValue(leftInt.getValue() - rightInt.getValue());
            case MULTIPLY -> new IntValue(leftInt.getValue() * rightInt.getValue());
            case DIVIDE -> {
                if (rightInt.getValue() == 0) {
                    throw new ExpressionExcpetion("Division by zero");
                }
                yield new IntValue(leftInt.getValue() / rightInt.getValue());
            }
            default -> throw new ExpressionExcpetion("Unsupported operator");
        };

    }

    @Override
    public IExpression deepCopy() {
        return new ArithmeticExpression(left.deepCopy(), operator, right.deepCopy());
    }
    @Override
    public String toString() {
        return  left.toString() +" "+ operator.toString() + " " + right.toString();
    }
}
