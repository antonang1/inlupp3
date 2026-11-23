package org.ioopm.calculator.ast;

public abstract class SymbolicExpression {

    public boolean isConstant() {
        return false;
    }

    public String getName() {
        throw new RuntimeException("getName() called on expression with no operator");
    }

    public double getValue() {
        throw new RuntimeException("getValue() called on a non-constant");
    }

    public int getPriority() {
        return Integer.MAX_VALUE;
    } // everything as highest possible prio, unless we override it (ex in Addition)

    public abstract SymbolicExpression eval(Environment vars);
}
