package model.ADT;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.values.IValue;

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
//    public String toString() {
//        return "Output List[ " +
//                list.stream()
//                        .map(IValue::toString)
//                        .collect(Collectors.joining(", ")) +
//                " ]";
//    }
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Output List[ " );
        for( T t:list){
            str.append(t).append(", ");
        }
        str.append("]");
        str.deleteCharAt(str.length()-3);
        return str.toString();
    }
}
