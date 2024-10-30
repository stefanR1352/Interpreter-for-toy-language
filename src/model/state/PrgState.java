package model.state;

import exceptions.ADTException;
import exceptions.ExpressionExcpetion;
import model.ADT.*;
import model.statements.IStatement;
import model.statements.IfStatement;
import model.values.IValue;
import model.values.StringValue;

import java.io.BufferedReader;

public class PrgState {
    private MyIStack<IStatement> exeStack;
    private MyIMap<String, IValue> symTabel;
    private MyIList<IValue> outlist;
    private MyIMap<StringValue, BufferedReader> filetable;
    private IStatement originalStatement;


    public MyIStack<IStatement> getExeStack() {return exeStack;}
    public MyIMap<String, IValue> getSymTabel() {return symTabel;}
    public MyIList<IValue> getOutlist() {return outlist;}
    public MyIMap<StringValue, BufferedReader> getFiletable() {return filetable;}
    public IStatement getOriginalStatement() {return originalStatement;}

    public PrgState(IStatement OGStatement){
        this.exeStack = new MyStack<IStatement>();
        this.symTabel = new MyMap<String, IValue>();
        this.outlist = new MyList<IValue>();
        this.filetable = new MyMap<StringValue, BufferedReader>();
        this.originalStatement = OGStatement.deepCopy();
        this.exeStack.push(originalStatement.deepCopy());
    }

    public String fileTableToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("FileTable: \n");
        for(StringValue path: filetable.getKeys()){
            sb.append(path).append("\n");
        }
        return sb.toString();
    }

    public String toString(){
        return "PrgState: \n" +exeStack.toString() + "\n" +symTabel.toString()
                + "\n" +outlist.toString() + filetable.toString()+ "\n";
    }
}
