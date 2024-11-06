package model.ADT;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T>{
    private List<T> list;
    public MyList() {
        this.list = new ArrayList<>();
    }

    @Override
    public void add(T element) {
        this.list.add(element);
    }

    @Override
    public List<T> getAll() {
        return this.list;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Output List[ " );
        for( T t:list){
            str.append(t).append(" ");
        }
        str.append("]");
        return str.toString();
    }
}
