package org.ioopm.calculator;

import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.CalculatorParser;

public class Calculator {
    public static void main(String[] args) {
        final Environment enviroment = new Environment();
        final CalculatorParser parser = new CalculatorParser();

        boolean run = true;

        while(run) {
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
                        for (Variable key : enviroment.keySet()) {

                            System.out.print(key + "=" + enviroment.get(key) + ", ");
                        }
                        System.out.print("}\n");
                    }
                } else {
                    SymbolicExpression ans = expression.eval(enviroment);
                    System.out.println(ans);
                }

            } catch (IllegalAssignmentException e) {
                System.out.println(e.getMessage());
    
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }

        }
    }
}
