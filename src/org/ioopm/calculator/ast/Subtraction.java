package org.ioopm.calculator.ast;

public class Subtraction extends Binary {
    public Subtraction(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
    }

    @Override
    public String getName() { return "-"; }

    @Override 
    public int getPriority() { return 50; }

    public SymbolicExpression eval() {
        SymbolicExpression lhs_eval = this.getLhs().eval();
        SymbolicExpression rhs_eval = this.getRhs().eval();
        
        if (lhs_eval.isConstant() && rhs_eval.isConstant()) {
            return new Constant(lhs_eval.getValue() - rhs_eval.getValue());
        } else {
            return new Subtraction(lhs_eval, rhs_eval);
        }
    }
}
