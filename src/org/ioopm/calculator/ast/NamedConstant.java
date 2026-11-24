package org.ioopm.calculator.ast;

public class NamedConstant extends Atom {
    private final String name;
    private final double value;

    public NamedConstant(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean isConstant() {
        return true;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override 
    public String getName() {
        return this.name;
    }

    @Override // för att equals() finns redan i superklassen Object.
    public boolean equals(Object other) {
        if (other instanceof NamedConstant) {
            return this.equals((NamedConstant) other);
        } else {
            return false;
        }
    }

    public boolean equals(NamedConstant other) {
        return this.value == other.value
                && this.name.equals(other.name);
    }

    public String toString() {
        return "" + value;
    }

    public SymbolicExpression eval(Environment vars) {
        return this;
    }

}
