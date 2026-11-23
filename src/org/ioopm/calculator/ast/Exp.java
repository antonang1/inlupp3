package org.ioopm.calculator.ast;

public class Exp extends Unary {
    public Exp(SymbolicExpression lhs) {
        super(lhs);
    }

    @Override
    public String getName() { return "exp"; }

    public SymbolicExpression eval() {
        SymbolicExpression arg = this.getArgument().eval();
        if (arg.isConstant())
        {
            return new Constant(Math.exp(arg.getValue()));
        } else {
            return new Exp(arg);
        }
    }
}
