package org.ioopm.calculator;

import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.CalculatorParser;

public class Calculator {
    public static void main(String[] args) {
        final Environment enviroment = new Environment();
        final CalculatorParser parser = new CalculatorParser();

        boolean run = true;

        while(run) {
            System.out.print("Please enter an expression: ");
            String input = System.console().readLine();
            try {
                SymbolicExpression expression = parser.parse(input, enviroment);

                if (expression.isCommand()) {
                    if (expression == Quit.instance()) {
                        run = false;
                    } else if (expression == Clear.instance()) {
                        enviroment.clear();
                    } else if (expression == Vars.instance()) {
                        System.out.print("{");
                        StringBuilder output = new StringBuilder();
                        for (Variable key : enviroment.keySet()) {
                            if (output.length() > 0) { output.append(", "); }
                            output.append(key + " = " + enviroment.get(key));
                        }
                        System.out.print(output + "}\n");
                    }
                } else {
                    SymbolicExpression result = expression.eval(enviroment);
                    enviroment.put(new Variable("ans"), result);
                    System.out.println(result);
                }

            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }

        }
    }
}
