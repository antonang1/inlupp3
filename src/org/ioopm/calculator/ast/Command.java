package org.ioopm.calculator.ast;

public abstract class Command extends SymbolicExpression {

    @Override
    public boolean equals(Object other) {
        return other != null && this.getClass() == other.getClass();
    }

    @Override
    public SymbolicExpression eval(Environment vars) {
        throw new RuntimeException("Commands may not be evaluated");
    }
}