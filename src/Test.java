import org.ioopm.calculator.ast.*;

public class Test
{
    public void testPrinting(String expected, SymbolicExpression e) {
        if (expected.equals("" + e)) {
            System.out.println("Passed: " + e);
        } else {
            System.out.println("Error: expected '" + expected + "' but got '" + e + "'");
        }
    }
    
    /*
    public void testEvaluating(SymbolicExpression expected, SymbolicExpression e) {
        SymbolicExpression r = e.eval();
        if (r.equals(expected)) {
            System.out.println("Passed: " + e);
        } else {
            System.out.println("Error: expected '" + expected + "' but got '" + e + "'");
        }
    }
     */

    private static void testEquals(String description,
                                SymbolicExpression actual,
                                SymbolicExpression expected,
                                boolean expectedResult)
    {
        boolean result = actual.equals(expected);

        System.out.println(description + ": "
            + (result == expectedResult ? "PASS" : "FAIL"));

        if (result != expectedResult) {
            System.out.println("  Expected: " + expectedResult);
            System.out.println("  Got: " + result);
        }
    }


    public void main(String[] args)
    {
        Constant d1 = new Constant(5);
        Constant d2 = new Constant(2);
        Variable v = new Variable("x");
        Addition a = new Addition(d1, v);
        Multiplication m = new Multiplication(a, d2);

        testPrinting("(5.0 + x) * 2.0", m);

        // Constants
        Constant c1 = new Constant(5);
        Constant c2 = new Constant(5);
        Constant c3 = new Constant(2);

        testEquals("Same constant object", c1, c1, true);
        testEquals("Different constants, same value", c1, c2, true);
        testEquals("Different constants, different value", c1, c3, false);

        // Variables
        Variable v1 = new Variable("x");
        Variable v2 = new Variable("x");
        Variable v3 = new Variable("y");

        testEquals("Same variable name", v1, v2, true);
        testEquals("Different variable names", v1, v3, false);
        testEquals("Variable vs constant", v1, c1, false);

        // Binary expressions
        Addition a1 = new Addition(c1, v1);
        Addition a2 = new Addition(c2, v2);
        Addition a3 = new Addition(v1, c1);

        testEquals("Equal additions", a1, a2, true);
        testEquals("Addition with reversed operands", a1, a3, false);
        testEquals("Addition vs multiplication", a1,
                new Multiplication(c1, v1), false);

        // Nested expressions
        Multiplication m1 = new Multiplication(a1, c3);
        Multiplication m2 = new Multiplication(a2, new Constant(2));
        Multiplication m3 = new Multiplication(c3, a1);

        testEquals("Equal nested expressions", m1, m2, true);
        testEquals("Nested expressions with reversed operands", m1, m3, false);

        // Unary expressions
        Sin s1 = new Sin(v1);
        Sin s2 = new Sin(v2);
        Cos cos1 = new Cos(v1);

        testEquals("Equal unary expressions", s1, s2, true);
        testEquals("Different unary operators", s1, cos1, false);

        // Different expression types
        testEquals("Addition vs variable", a1, v1, false);
        testEquals("Constant vs addition", c1, a1, false);

        // Null
        testEquals("Expression vs null", c1, null, false);

        Vars x1 = new Vars();
        Vars x2 = new Vars();
        // Commands (adapt to your constructors/singletons)
        testEquals("Same command type", x1, x2, true);

        }
}