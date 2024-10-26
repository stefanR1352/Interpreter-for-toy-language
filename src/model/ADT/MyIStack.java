package model.ADT;

import exceptions.ADTException;

public interface MyIStack<T> {
    public T pop() throws ADTException;
    public void push(T value);
}
