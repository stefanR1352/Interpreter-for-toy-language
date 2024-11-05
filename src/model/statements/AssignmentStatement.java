package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionExcpetion;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.values.IValue;

public class AssignmentStatement implements IStatement {
    private String variable;
    private IExpression expression;

    public AssignmentStatement(String variable, IExpression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState p) throws StatementException, ExpressionExcpetion, ADTException {
        if(!p.getSymTabel().containsKey(variable)) {
            throw new StatementException("Variable " + variable + " not found");
        }
        IValue value = expression.eval(p.getSymTabel());
        if(!value.getType().equals(p.getSymTabel().getValue(variable).getType())) {
            throw new StatementException("Variable " + variable + " has wrong type");
        }
        p.getSymTabel().put(variable, value);
        return p;
    }

    @Override
    public IStatement deepCopy() {
        return new AssignmentStatement(variable, expression.deepCopy());
    }
}
