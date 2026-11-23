package org.ioopm.calculator.ast;

public abstract class Binary extends SymbolicExpression {
    private SymbolicExpression lhs = null;
    private SymbolicExpression rhs = null;

    public Binary(SymbolicExpression lhs, SymbolicExpression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public String toString() {
        boolean parenthesesLhs = lhs.getPriority() < this.getPriority();
        boolean parenthesesRhs = rhs.getPriority() < this.getPriority();

        if (parenthesesLhs && parenthesesRhs) { return "(" + lhs + ") " + getName() + " (" + rhs + ")";}
        
        if (parenthesesLhs) { return "(" + lhs + ") " + getName() + " " + rhs; }
        
        if (parenthesesRhs) { return lhs + " " + getName() + " (" + rhs + ")"; }
        
        return lhs + " " + getName() + " " + rhs;
    }

    @Override 
    public boolean equals(Object other)
    {
        if (other instanceof Binary)
        {
            return this.equals((Binary) other);
        } else {
            return false;
        }
    }

    public boolean equals(Binary other)
    {
        return this.getName().equals(other.getName())
               && this.lhs.equals(other.lhs)
               && this.rhs.equals(other.rhs);
    }

    public SymbolicExpression getLhs() { return this.lhs; }
    public SymbolicExpression getRhs() { return this.rhs; }
}
