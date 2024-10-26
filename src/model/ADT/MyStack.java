package model.ADT;

import exceptions.ADTException;
import exceptions.EmptyStackException;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T>{
    private Stack<T> stack;
    public MyStack() {this.stack = new Stack<>();}
    public MyStack(Stack<T> stack) {this.stack = stack;}

    @Override
    public T pop() throws EmptyStackException {
        if(stack.isEmpty()){
            throw new EmptyStackException();
        }
        return stack.pop();
    }

    @Override
    public void push(T value) {
        stack.push(value);
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Exec Stack:\n");
        for(T t : stack){
            str.append(t).append("\n");
        }
        return str.toString();
    }
}
