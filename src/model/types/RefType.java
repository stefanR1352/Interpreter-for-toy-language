package model.types;

import model.values.IValue;
import model.values.RefValue;

public class RefType implements IType {
    private IType inner;

    public RefType(IType inner) {
        this.inner = inner;
    }

    public IType getInnerType() { return inner; }


    @Override
    public boolean equals(IType type) {
        if(!(type instanceof RefType)) return false;
        return ((RefType)type).getInnerType().equals(inner);
    }

    @Override
    public IValue getDefaultValue() {
        return new RefValue(0, inner);
    }

    public String toString() {
        return "Ref" + inner;
    }
}
