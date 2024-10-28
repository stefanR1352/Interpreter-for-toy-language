package model.values;

import model.types.BoolType;
import model.types.IType;

public class BoolValue implements IValue{
    private boolean value;

    public BoolValue(boolean value){this.value = value;}

    public boolean getValue(){return this.value;}

    public IType getType(){
        return new BoolType();
    }

    public boolean equals(IValue v){
        if(!v.getType().equals(this.getType()))
            return false;
        return ((BoolValue)v).getValue() == this.getValue();
    }

    public String toString() { return Boolean.toString(value);}

}
