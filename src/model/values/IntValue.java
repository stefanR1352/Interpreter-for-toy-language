package model.values;

import model.types.IType;
import model.types.IntType;

public class IntValue implements IValue {
    private int value;
    public IntValue(int value) {this.value = value;}
    public int getValue() {return value;}
    public void setValue(int value) {this.value = value;}

    public IType getType() {return new IntType();}
    public boolean equals(IValue v){
        if(!v.getType().equals(this.getType()))
            return false;
        return ((IntValue)v).getValue() == this.getValue();
    }

    public String toString() {return Integer.toString(value);}
}
