package model.ADT;

import exceptions.ADTException;
import exceptions.KeyNotFoundException;

import java.util.Set;

public interface MyIMap <K, V> {
    public V getValue(K key) throws ADTException;
    public boolean containsKey(K key);
    public void put(K key, V value);
    public Set<K> getKeys();
    void remove(K fileName);
    MyIMap <K, V> deepCopy();

}
