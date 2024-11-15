package model.expressions;

import exceptions.ExpressionExcpetion;
import model.ADT.MyIMap;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;

import javax.management.relation.Relation;

public class RelationalExpression implements IExpression{
    private IExpression left;
    private IExpression right;
    private RelationalOperator operator;
    public RelationalExpression(IExpression left, IExpression right, RelationalOperator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public IValue eval(MyIMap<String, IValue> symTable) {
        IValue left = this.left.eval(symTable);
        IValue right = this.right.eval(symTable);

        if (!(left.getType().equals(new IntType())) || !(right.getType().equals(new IntType()))) {
            throw new ExpressionExcpetion("Both operands must be integers");
        }

        int leftInt = ((IntValue) left).getValue();
        int rightInt = ((IntValue) right).getValue();

        return switch (operator){
            case LESS -> new BoolValue(leftInt<rightInt);
            case LESS_EQUAL -> new BoolValue(leftInt<=rightInt);
            case GREATER -> new BoolValue(rightInt>leftInt);
            case GREATER_EQUAL -> new BoolValue(rightInt>=leftInt);
            case EQUAL -> new BoolValue(leftInt==rightInt);
            case NOT_EQUAL -> new BoolValue(rightInt!=leftInt);
        };
    }
    @Override
    public IExpression deepCopy() {
        return new RelationalExpression(left.deepCopy(), right.deepCopy(), operator);
    }

    @Override
    public String toString() {
        String opStr = switch (operator) {
            case LESS -> "<";
            case LESS_EQUAL -> "<=";
            case EQUAL -> "==";
            case NOT_EQUAL -> "!=";
            case GREATER -> ">";
            case GREATER_EQUAL -> ">=";
        };
        return left.toString() + " " + opStr + " " + right.toString();
    }
}
