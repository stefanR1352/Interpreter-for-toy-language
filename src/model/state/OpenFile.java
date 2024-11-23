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
import java.io.FileReader;
import java.io.IOException;

public class OpenFile implements IStatement {
    private IExpression expression;

    public OpenFile(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState p) throws StatementException, ExpressionExcpetion, ADTException, IOException {
        IValue value = expression.eval(p.getSymTabel(), p.getHeap());
        if (!value.getType().equals(new StringType())) {
            throw new ExpressionExcpetion("Expression is not of type String.");
        }

        StringValue fileName = (StringValue) value;
        if (p.getFiletable().containsKey(fileName)) {
            throw new StatementException("File is already opened: " + fileName.getVal());
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName.getVal()));
            p.getFiletable().put(fileName, reader);
        } catch (IOException e) {
            throw new StatementException("Error opening file: " + fileName.getVal());
        }

        return p;
    }

    @Override
    public IStatement deepCopy() {
        return new OpenFile(expression.deepCopy());
    }
}

