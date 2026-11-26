# inlupp3
Anton, Oliver

## How to run program
### Compile Calculator.java
`make all`

### Run Calculator
`make run`

### Compile and run tests
`make test`

### Remove *.class
`make clean`

## About
This is a Symbolic calculator written in Java. The superclass is SymbolicExpression, every operation (for instance addition, sin and assignment) is a subclass of this. It reads one expression per line, evaluates it as far as possible and prints the result. An expression that containss a variable without a value is printed in its simplified form instead of failing, e.g. 'x + 2 * 3' evaluates to 'x + 6.0'.

The program consists of two main parts:
Parser - (org.ioopm.calculator.parser): a recursive descent parser that turns an input string into an abstract syntax tree (AST).
AST - (org.ioopm.calculator.ast): Each node knows how to print itself ('toString()'), compare itself to other nodes ('equals()') and evaluate itself ('eval').

### Features
- Arithmetic: '+', '-', '*', '/', negation, 'sin', 'cos', 'exp', 'log'
- Variables, assigned with the value to the left: '3 = x'
- Chained assignments ('2 = x =  y') and assignments inside expressions ('(1 = y) + 2)
- Named constants that cannot be reassigned, e.g. 'pi', 'e', 'Answer' and 'L'. Constants are listed in as/Constants.java and new ones can be added there without changing the parser. All named constants and variables are stored in a HashMap that maps a variable to a SymbolicExpression.
- Commands: 'Vars' (lists all current variables), 'Clear' (removes all variables) and 'Quit' (exit and print statistics such as amount of entered and evaluated expression). The program does not support other spellings of these commands.

## Error handling
Syntax errors and attempts to reassign a named constant print an error message and the calculator continues with the next line. If an error occurs during evaluation, all variables are restored to their state before that line, so a failed expression never leaves partial assignments behind.