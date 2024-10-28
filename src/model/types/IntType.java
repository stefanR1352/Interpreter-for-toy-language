package model.types;

import model.values.IValue;

public class IntType  implements IType {
    public boolean equals(IType t) {return t instanceof IntType;}

    @Override
    public IValue getDefaultValue() {
        return null;
    }

    public IntType(){}

    public String toString(){return "int";}


}
