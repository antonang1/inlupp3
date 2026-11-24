package org.ioopm.calculator.ast;

public class Assignment extends Binary {
    public Assignment(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs);
    }

    public String getName() {
        return "=";
    }

    public SymbolicExpression eval(Environment vars){
        SymbolicExpression lhs_eval = this.getLhs().eval(vars);

        if (this.getRhs() instanceof NamedConstant) {
            throw new IllegalAssignmentException("Can not reassign named constant '" + this.getRhs().getName() + "'");
        } else if (this.getRhs() instanceof Variable) {
            vars.put((Variable) this.getRhs(), lhs_eval);
            return lhs_eval;
        } else {
            throw new RuntimeException("Right hand side must be a variable");
        }
    }

}
