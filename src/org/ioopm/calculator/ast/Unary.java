package org.ioopm.calculator.ast;

public abstract class Unary extends SymbolicExpression {
    private SymbolicExpression argument;

    public Unary(SymbolicExpression argument)
    {
        this.argument = argument;
    }
}
