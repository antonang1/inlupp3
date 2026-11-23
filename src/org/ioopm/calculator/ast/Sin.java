package org.ioopm.calculator.ast;

public class Sin extends Unary {
    public Sin(SymbolicExpression argument) {
        super(argument);
    }

    @Override
    public String getName() {
        return "sin";
    }

    public SymbolicExpression eval(Environment vars) {
        SymbolicExpression arg = this.getArgument().eval(vars);
        if (arg.isConstant()) {
            return new Constant(Math.sin(arg.getValue()));
        } else {
            return new Sin(arg);
        }
    }
}
