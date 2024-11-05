package model.types;

import model.values.IValue;

public class StringType implements IType{
    public StringType(){}
    public boolean equals(IType type){
        return type instanceof StringType;
    }

    @Override
    public IValue getDefaultValue() {
        return null;
    }

    public IValue defaultValue(){
        return null;
    }
}
