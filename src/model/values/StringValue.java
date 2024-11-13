package model.values;

import com.sun.jdi.Value;
import model.types.IType;

public class StringValue implements IValue{
    private String value;

    public StringValue(){
        this.value = "";
    }

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(IValue value) {
        return false;
    }

    @Override
    public IType getType() {
        return null;
    }
    public String getVal() { return value; }


}
