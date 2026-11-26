package org.ioopm.calculator;

import java.util.HashMap;

import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.CalculatorParser;

public class Calculator {
    public static void main(String[] args) {
        final Environment enviroment = new Environment();
        final CalculatorParser parser = new CalculatorParser();

        int expressionCounter = 0;
        int expressionsEvaluated = 0;
        int expressionsFullyEvaluated = 0;
        boolean run = true;
        while(run) {
            // create a backup if evaluation fails
            Environment env_copy = new Environment();
            for (Variable key : enviroment.keySet()) {
                env_copy.put(key, enviroment.get(key));
            }

            System.out.print("Please enter an expression: ");
            String input = System.console().readLine();
            if (input == null) {
                System.out.print("\n");
                continue;
            }
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
                    expressionCounter++;
                    SymbolicExpression result = expression.eval(enviroment);
                    enviroment.put(new Variable("ans"), result);
                    System.out.println(result);
                    expressionsEvaluated++;
                    if (result.isConstant()) {
                        expressionsFullyEvaluated++;
                    }
                }

            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                enviroment.clear();
                enviroment.putAll(env_copy); // restore backup if evaluation fails.
            } catch (Exception e) {
                System.out.println("Invalid input!");
                System.out.println(e.getMessage());
                enviroment.clear();
                enviroment.putAll(env_copy); // restore backup if evaluation fails.
            }
        }
        System.out.println("Amount of expressions entered: " + expressionCounter);
        System.out.println("Amount of expressions evaluated: " + expressionsEvaluated);
        System.out.println("Amount of expressions fully evaluated: " + expressionsFullyEvaluated);

    }
}
