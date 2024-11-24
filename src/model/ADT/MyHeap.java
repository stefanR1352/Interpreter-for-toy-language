package model.ADT;

import exceptions.ADTException;
import model.values.IValue;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MyHeap implements MyIHeap{
    private final Map<Integer, IValue> heap;
    private int nextFree;

    public MyHeap() {
        this.heap = new HashMap<>();
        this.nextFree = 1;
    }

    @Override
    public int allocate(IValue value) {
        heap.put(nextFree, value);
        return nextFree++;
    }

    @Override
    public IValue getValue(int key) throws ADTException {
        if(!heap.containsKey(key)) {
            throw new ADTException("Adress not found!");
        }
        return heap.get(key);
    }

    @Override
    public void set(int key, IValue value) {
        if(heap.containsKey(key)) {
            heap.replace(key, value);
        }
        else {
            heap.put(key, value);
        }
    }

    @Override
    public void setContent(Map<Integer, IValue> content) {
        heap.clear();
        heap.putAll(content);
    }

    @Override
    public boolean containsKey(int key) {
        return heap.containsKey(key);
    }

    @Override
    public Map<Integer, IValue> getHeap() {
        return heap;
    }

    @Override
    public String toString() {
        return "Heap: " + heap.entrySet()
                .stream()
                .map(entry -> entry.getKey() + " -> " + entry.getValue().getType())
                .collect(Collectors.joining(", ", "{", "}"));
    }
}
