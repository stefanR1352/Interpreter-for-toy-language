package model.state;

import exceptions.ADTException;
import exceptions.ExpressionExcpetion;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.statements.IStatement;
import model.types.StringType;
import model.values.IValue;
import model.values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFile implements IStatement {
    private IExpression expression;

    public CloseFile(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState p) throws StatementException, ExpressionExcpetion, ADTException, IOException {
        IValue value = expression.eval(p.getSymTabel(), p.getHeap());
        if (!value.getType().equals(new StringType())) {
            throw new ExpressionExcpetion("Expression is not of type String.");
        }

        StringValue fileName = (StringValue) value;
        if (!p.getFiletable().containsKey(fileName)) {
            throw new StatementException("File not found in file table: " + fileName.getVal());
        }

        BufferedReader reader = p.getFiletable().getValue(fileName);
        try {
            reader.close();
            p.getFiletable().remove(fileName);
        } catch (IOException e) {
            throw new StatementException("Error closing file: " + fileName.getVal());
        }

        return p;
    }

    @Override
    public IStatement deepCopy() {
        return new CloseFile(expression.deepCopy());
    }
}
