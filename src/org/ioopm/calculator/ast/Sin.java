package org.ioopm.calculator.ast;

import java.util.HashMap;

public class Sin extends Unary {
    public Sin(SymbolicExpression argument) {
        super(argument);
    }

    @Override
    public String getName() { return "sin"; }

    public SymbolicExpression eval() {
        SymbolicExpression arg = this.getArgument().eval();
        if (arg.isConstant()) {
            return new Constant(Math.sin(arg.getValue()));
        } else {
            return new Sin(arg);
        }
    }
}
