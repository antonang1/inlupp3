import org.ioopm.calculator.ast.*;

public class Test {
    public static void testPrinting(String expected, SymbolicExpression e) {
        if (expected.equals("" + e)) {
            System.out.println("Passed: " + e);
        } else {
            System.out.println("Error: expected '" + expected
                               + "' but got '" + e + "'");
        }
    }

    public static void testEvaluating(SymbolicExpression expected, 
                                    SymbolicExpression e, Environment vars) {
        SymbolicExpression r = e.eval(vars);
        if (r.equals(expected)) {
            System.out.println("Passed: " + e + " => " + r);
        } else {
            System.out.println("Error: " + e + ": expected '" + expected
                               + "' but got '" + r + "'");
        }
    }

    public static void main(String[] args) {
        System.out.print("Test constructors and toString(): \n");
        Multiplication m = new Multiplication(new Addition(new Constant(5),
                                                           new Variable("x")),
                                              new Constant(2));
        testPrinting("(5.0 + x) * 2.0", m);
        Assignment a1 =
            new Assignment(new Division(new Constant(4),
                                        new NamedConstant("pi", 3.14)),
                           new Variable("x"));
        testPrinting("(4.0 / 3.14) = x", a1);

        Cos c = new Cos(new Log(new Sin(new Constant(0))));
        testPrinting("cos(log(sin(0.0)))", c);
        Negation n = new Negation(new Subtraction(new Exp(new Constant(0)),
                                                  new Constant(2)));
        testPrinting("-(exp(0.0) - 2.0)", n);
        
        System.out.print("\nTest evaluating: \n");
        Environment env = new Environment();
        SymbolicExpression a2 = new Addition(new Constant(5), new Constant(37));
        SymbolicExpression b = new Constant(42);
        testEvaluating(b, a2, env);

        // Unära operationer med konstanter
        testEvaluating(new Constant(-3), new Negation(new Constant(3)),
                       new Environment());
        testEvaluating(new Constant(0), new Sin(new Constant(0)),
                       new Environment());

        // Okänd variabel förblir symbolisk
        testEvaluating(new Addition(new Constant(5), new Variable("x")),
                       new Addition(new Constant(5), new Variable("x")),
                       new Environment());

        // Variabel med värde i miljön: (5 + x) * 2 med x = 8 ska bli 26
        Environment env1 = new Environment();
        env1.put(new Variable("x"), new Constant(8));
        testEvaluating(new Constant(26),
                       new Multiplication(new Addition(new Constant(5),
                                                       new Variable("x")),
                                          new Constant(2)),
                       env1);

        // Tilldelning: 3 = x returnerar 3 och x är satt efteråt
        Environment env2 = new Environment();
        testEvaluating(new Constant(3),
                       new Assignment(new Constant(3), new Variable("x")),
                       env2);
        testEvaluating(new Constant(3), new Variable("x"), env2);

        // Kedjad tilldelning: 2 = x = y sätter både x och y
        Environment env3 = new Environment();
        testEvaluating(new Constant(2),
                       new Assignment(new Assignment(new Constant(2),
                                                     new Variable("x")),
                                      new Variable("y")),
                       env3);
        testEvaluating(new Constant(2), new Variable("y"), env3);

        // Sidoeffekt inuti uttryck, vänster till höger: (1 = x) + x ska bli 2
        testEvaluating(new Constant(2),
                       new Addition(new Assignment(new Constant(1),
                                                   new Variable("x")),
                                    new Variable("x")),
                       new Environment());

        // Named constant i en tilldelning: Answer = x ska ge x värdet 42
        NamedConstant answer =
            new NamedConstant("Answer", Constants.namedConstants.get("Answer"));
        Environment env4 = new Environment();
        testEvaluating(answer, new Assignment(answer, new Variable("x")), env4);
        testEvaluating(new Constant(84),
                       new Addition(new Variable("x"), new Variable("x")),
                       env4);

        // Negativt test: 43 = Answer ska kasta IllegalAssignmentException
        Environment env5 = new Environment();
        try {
            new Assignment(new Constant(43), answer).eval(env5);
            System.out.println("Error: 43 = Answer: expected "
                               + "IllegalAssignmentException");
        } catch (IllegalAssignmentException e) {
            System.out.println("Passed: 43 = Answer => " + e.getMessage());
        }

        // Negativt test: commands får inte utvärderas
        try {
            Vars.instance().eval(new Environment());
            System.out.println("Error: Vars: expected exception from eval");
        } catch (RuntimeException e) {
            System.out.println("Passed: Vars => " + e.getMessage());
        }

        System.out.print("Test isCommand()\n");
        if (Vars.instance().isCommand()
            && Clear.instance().isCommand()
            && Quit.instance().isCommand()) {
            System.out.println("Passed: All commands detected");    
        } else {
            System.out.println("Error: Commands not detected");
        }
        
        Addition a3 = new Addition(new Constant(2), new Constant(3));
        if (!a3.isCommand()) {
            System.out.println("Passed: Binary is not a command");    
        } else {
            System.out.println("Error: Binary was interperated as command");
        }
        

    }
}