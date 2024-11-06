package model.types;

import model.values.IValue;
import model.values.IntValue;

public class IntType  implements IType {
    public boolean equals(IType t) {return t instanceof IntType;}

    @Override
    public IValue getDefaultValue() {
        return new IntValue(0);
    }

    public IntType(){}

    public String toString(){return "int";}


}
