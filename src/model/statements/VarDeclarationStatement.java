package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionExcpetion;
import exceptions.StatementException;
import model.state.PrgState;
import model.types.IType;
import model.values.IValue;

public class VarDeclarationStatement implements IStatement {
    private String varName;
    private IType type;

    public VarDeclarationStatement(String varName, IType type) {
        this.varName = varName;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState p) throws StatementException, ExpressionExcpetion, ADTException {
        if(p.getSymTabel().containsKey(varName)) {
            throw new StatementException("Variable " + varName + " already declared");
        };
        IValue varValue = type.getDefaultValue();
        p.getSymTabel().put(varName, varValue);
        return p;
    }

    @Override
    public IStatement deepCopy() {
        return new VarDeclarationStatement(varName, type);
    }
    @Override
    public String toString() {
        return type + " " + varName ;
    }
}
