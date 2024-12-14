package model.state;

import exceptions.ADTException;
import exceptions.EmptyStackException;
import exceptions.ExpressionExcpetion;
import model.ADT.*;
import model.statements.IStatement;
import model.statements.IfStatement;
import model.values.IValue;
import model.values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class PrgState {
    private MyIStack<IStatement> exeStack;
    private MyIMap<String, IValue> symTabel;
    private MyIList<IValue> outlist;
    private MyIMap<StringValue, BufferedReader> filetable;
    private IStatement originalStatement;
    private MyIHeap heap;
    private int id;
    private static int nextId;

    //getters
    public MyIStack<IStatement> getExeStack() {
        return exeStack;
    }

    public MyIMap<String, IValue> getSymTabel() {
        return symTabel;
    }

    public MyIList<IValue> getOutlist() {
        return outlist;
    }

    public MyIMap<StringValue, BufferedReader> getFiletable() {
        return filetable;
    }

    public IStatement getOriginalStatement() {
        return originalStatement;
    }

    public MyIHeap getHeap() {
        return heap;
    }

    public synchronized int getId() {
        return nextId++;
    }

    //setters
    public void setExeStack(MyIStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public void setOriginalStatement(IStatement originalStatement) {
        this.originalStatement = originalStatement;
    }

    public void setFiletable(MyIMap<StringValue, BufferedReader> filetable) {
        this.filetable = filetable;
    }

    public void setOutlist(MyIList<IValue> outlist) {
        this.outlist = outlist;
    }

    public void setSymTabel(MyIMap<String, IValue> symTabel) {
        this.symTabel = symTabel;
    }

    public void setHeap(MyIHeap heap) {
        this.heap = heap;
    }

    //Constructors
    public PrgState(IStatement OGStatement) {
        this.exeStack = new MyStack<IStatement>();
        this.symTabel = new MyMap<String, IValue>();
        this.outlist = new MyList<IValue>();
        this.filetable = new MyMap<StringValue, BufferedReader>();
        this.originalStatement = OGStatement.deepCopy();
        this.exeStack.push(OGStatement);
        this.heap = new MyHeap();
        this.id = getId();
    }

    public PrgState(IStatement OGStatement, MyIStack<IStatement> stack, MyIMap<String, IValue> table, MyIList<IValue> outlist, MyIMap<StringValue, BufferedReader> filetable, MyIHeap heap) {
        this.exeStack = stack;
        this.symTabel = table;
        this.outlist = outlist;
        this.filetable = filetable;
        this.heap = heap;
        this.id = getId();
        this.originalStatement = OGStatement;
        this.exeStack.push(OGStatement);
    }

    //Methods
    public PrgState oneStep() throws EmptyStackException, ADTException, IOException {
        if (exeStack.isEmpty()) throw new EmptyStackException();
        IStatement statement = exeStack.pop();
        return statement.execute(this);
    }

    public String fileTableToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FileTable: \n");
        for (StringValue path : filetable.getKeys()) {
            sb.append(path).append("\n");
        }
        return sb.toString();
    }

    public boolean isNotCompleted() {
        return !(exeStack.isEmpty());
    }

    public String toString() {
        return "PrgState : \n ID: " + id + "\n" + exeStack.toString() + "\n" + symTabel.toString() + "\n" + outlist.toString() + "\n" + fileTableToString() + "\n" + heap.toString();

    }
}
