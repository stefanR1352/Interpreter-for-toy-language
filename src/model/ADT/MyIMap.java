package model.ADT;

import exceptions.ADTException;

public interface MyIMap <K, V> {
    public V getValue(K key) throws ADTException;
    public boolean containsKey(K key);
    public void put(K key, V value);
}
