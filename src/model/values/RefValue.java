package model.values;

import model.types.IType;
import model.types.RefType;

public class RefValue implements IValue{

    private int addr;
    private IType location;

    public RefValue(int addr, IType location) {
        this.addr = addr;
        this.location = location;
    }

    public int getAddress() { return addr;}
    public IType getLocation() { return location;}

    @Override
    public boolean equals(IValue value) {
        return value instanceof RefValue && ((RefValue)value).getAddress() == this.getAddress() && ((RefValue)value).getType() == this.getType();
    }

    @Override
    public IType getType() {
        return new RefType(location);
    }

    public String toString() {
        return location.toString();
    }
}
