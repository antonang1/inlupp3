package org.ioopm.calculator.ast;

public class Cos extends Unary {
    public Cos(SymbolicExpression lhs) {
        super(lhs);
    }

    @Override
    public String getName() { return "cos"; }

    public SymbolicExpression eval() {
        SymbolicExpression arg = this.getArgument().eval();
        if (arg.isConstant())
        {
            return new Constant(Math.cos(arg.getValue()));
        } else {
            return new Cos(arg);
        }
    }

}
