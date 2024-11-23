package model.ADT;

import exceptions.ADTException;
import exceptions.KeyNotFoundException;
import model.values.IValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MyMap<K,V> implements MyIMap<K,V>{
    private Map<K,V> map;
    public MyMap() {this.map = new HashMap<>();}


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

    public Set<K> getKeys(){
        return this.map.keySet();
    }

    @Override
    public void remove(K fileName) {
        this.map.remove(fileName);
    }

    @Override
    public String toString() {
        return "SymTable " + map.entrySet()
                .stream()
                .map(entry -> entry.getKey() + " -> " + entry.getValue().toString())
                .collect(Collectors.joining(", ", "{", "}"));
    }
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("SymTable { ");
//        for(Map.Entry<K,V> entry : this.map.entrySet()){
//            sb.append(entry.getKey() + "-> " + entry.getValue() + ", ");
//        }
//        sb.append("}");
//        return sb.toString();
//    }
}
