package org.ioopm.calculator.ast;

public class Addition extends Binary {
    public Addition(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
    }

    @Override
    public String getName() {
        return "+";
    }

    @Override
    public int getPriority() {
        return 50;
    }

    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression lhs_eval = this.getLhs().eval(vars);
        SymbolicExpression rhs_eval = this.getRhs().eval(vars);

        if (lhs_eval.isConstant() && rhs_eval.isConstant()) {
            return new Constant(lhs_eval.getValue() + rhs_eval.getValue());
        } else {
            return new Addition(lhs_eval, rhs_eval);
        }

    }
}
