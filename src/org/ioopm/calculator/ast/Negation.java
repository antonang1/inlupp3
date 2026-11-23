package org.ioopm.calculator.ast;

public class Negation extends Unary {
    public Negation(SymbolicExpression lhs) {
        super(lhs);
    }

    @Override
    public String getName() {
        return "-";
    }

    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression arg = this.getArgument().eval(vars);
        if (arg.isConstant()) {
            return new Constant(arg.getValue() * -1);
        } else {
            return new Negation(arg);
        }
    }
}
