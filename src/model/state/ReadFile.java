package model.state;
//
//import exceptions.ADTException;
//import exceptions.ExpressionExcpetion;
//import exceptions.StatementException;
//import model.expressions.IExpression;
//import model.expressions.VariableExpression;
//import model.statements.IStatement;
//import model.types.IntType;
//import model.types.StringType;
//import model.values.IValue;
//import model.values.IntValue;
//import model.values.StringValue;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//
//public class ReadFile implements IStatement {
//    private IExpression expression;
//    private String var;
//
//    public ReadFile(IExpression expression, String var) {
//        this.expression = expression;
//        this.var = var;
//    }
//
//    @Override
//    public PrgState execute(PrgState p) throws StatementException, ExpressionExcpetion, ADTException, IOException {
//        IValue value = expression.eval(p.getSymTabel());
//        if(!value.getType().equals(new StringType())){
//            throw new ExpressionExcpetion("The expression is not a string");
//        }
//        if(!p.getSymTabel().containsKey(var)){
//            throw new ExpressionExcpetion("The variable " + var + " does not exist");
//        }
//        if(!p.getSymTabel().getValue(var).getType().equals(new IntType())){
//            throw new ExpressionExcpetion("The variable " + var + " is not an integer");
//        }
//        StringValue valueString = (StringValue) value;
//        if(!p.getFiletable().containsKey(valueString)){
//            throw new ExpressionExcpetion("The variable " + var + " is not in the filetable");
//        }
//        BufferedReader reader = p.getFiletable().getValue(valueString);
//
//        try{
//            String read = reader.readLine();
//            if(read == null){
//                read = ("0");
//            }
//            int readInt = Integer.parseInt(read);
//            p.getSymTabel().put(var, new IntValue(readInt));
//        }catch(IOException e){
//            throw new StatementException("Error reading file ");
//        }
//        return p;
//    }
//
//
//    @Override
//    public IStatement deepCopy() {
//        return new ReadFile(this.expression.deepCopy(), var);
//    }
//}



import exceptions.ADTException;
import exceptions.ExpressionExcpetion;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.statements.IStatement;
import model.types.IntType;
import model.types.StringType;
import model.values.IValue;
import model.values.IntValue;
import model.values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement {
    private IExpression expression;
    private String var;

    public ReadFile(IExpression expression, String var) {
        this.expression = expression;
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState p) throws ExpressionExcpetion, ADTException, IOException, StatementException {
        IValue value = expression.eval(p.getSymTabel(), p.getHeap());
        if (!value.getType().equals(new StringType())) {
            throw new ExpressionExcpetion("The expression is not a string");
        }
        if (!p.getSymTabel().containsKey(var) || !p.getSymTabel().getValue(var).getType().equals(new IntType())) {
            throw new ExpressionExcpetion("The variable does not exist or is not of type Int");
        }
        StringValue valueS = (StringValue) value;

        if (!p.getFiletable().containsKey(valueS)) {
            throw new ExpressionExcpetion("The file is not open");
        }

        BufferedReader reader = p.getFiletable().getValue(valueS);
        try {
            String readerS = reader.readLine();
            if (readerS == null) {
                p.getSymTabel().put(var, new IntValue(0));
            } else {
                readerS = readerS.replace("<NL>", "").trim();
                try {
                    int readerI = Integer.parseInt(readerS);
                    p.getSymTabel().put(var, new IntValue(readerI));
                } catch (NumberFormatException e) {
                    throw new StatementException("Invalid integer in file: " + readerS);
                }
            }
        } catch (IOException e) {
            throw new StatementException("The file cannot be read");
        }
        return p;
    }

    @Override
    public IStatement deepCopy() {
        return new ReadFile(this.expression.deepCopy(), var);
    }

    @Override
    public String toString() {
        return "ReadFile(" + expression + ", " + var + ")";
    }
}