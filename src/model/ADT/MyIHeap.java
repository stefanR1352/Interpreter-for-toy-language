package model.ADT;

import exceptions.ADTException;
import model.values.IValue;

import java.util.Map;

public interface MyIHeap {
    public int allocate(IValue value);
    public IValue getValue(int key) throws ADTException;
    public void set(int key, IValue value);
    public boolean containsKey(int key);
    public Map<Integer, IValue> getHeap();
    public void setContent(Map<Integer, IValue> content);
}
