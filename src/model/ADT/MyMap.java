package model.ADT;

import exceptions.ADTException;
import exceptions.KeyNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class MyMap<K,V> implements MyIMap<K,V>{
    private Map<K,V> map;
    public MyMap() {this.map = new HashMap<K,V>();}


    @Override
    public V getValue(K key) throws KeyNotFoundException {
        if(this.map.containsKey(key)){
            return this.map.get(key);
        }
        throw new KeyNotFoundException();
    }

    @Override
    public boolean containsKey(K key) {
        return this.map.containsKey(key);
    }

    @Override
    public void put(K key, V value) {
        this.map.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SymTable { ");
        for(Map.Entry<K,V> entry : this.map.entrySet()){
            sb.append(entry.getKey() + "-> " + entry.getValue() + "\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
