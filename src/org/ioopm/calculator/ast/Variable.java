package org.ioopm.calculator.ast;

public class Variable extends Atom {
    private String identifier;

    public Variable(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Variable) {
            return this.equals((Variable) other);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.identifier.hashCode();
    }

    public boolean equals(Variable other) {
        return this.identifier.equals(other.identifier);
    }

    public SymbolicExpression eval(Environment vars) {
        if (vars.containsKey(this)) {
            return vars.get(this);
        } else {
            return this;
        }
    }

    public String toString() {
        return this.identifier;
    }
}
