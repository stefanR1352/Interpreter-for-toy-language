package model.filetable;

public interface MyIFileTable <K,V>{
    void add(K key, V value) ;
    V get(K key) ;
    void remove(K key) ;
    boolean contains(K key);
}
