package model.types;

import model.values.IValue;
import model.values.StringValue;

public class StringType implements IType{
    public StringType(){}
    public boolean equals(IType type){
        return type instanceof StringType;
    }

    @Override
    public IValue getDefaultValue() {
        return new StringValue("");
    }

    public IValue defaultValue(){
        return null;
    }
}
