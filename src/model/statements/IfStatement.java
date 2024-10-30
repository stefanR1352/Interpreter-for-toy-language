package model.statements;

import exceptions.ExpressionExcpetion;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.IValue;

public class IfStatement implements IStatement {
    private IExpression condition;
    private IStatement thenStatement;
    private IStatement elseStatement;
    public IfStatement(IExpression condition, IStatement thenStatement, IStatement elseStatement) {
        this.condition = condition;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException, ExpressionExcpetion {
        IValue v = condition.eval(state.getSymTabel());
        if(!v.getType().equals(new BoolType())){
            throw new StatementException("Condition expression is not of type BoolType");
        }
        if(((BoolValue)v).getValue()){
            state.getExeStack().push(thenStatement);
        }
        else{
            state.getExeStack().push(elseStatement);
        }
        return state;
    }

    @Override
    public IStatement deepCopy(){
        return new IfStatement(condition.deepCopy(), thenStatement.deepCopy(), elseStatement.deepCopy());
    }

    public String toString() {return "if(" + condition + "){ " + thenStatement + "}\nelse{\n " + elseStatement + "\n}";}

}
