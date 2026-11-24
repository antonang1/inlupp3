package org.ioopm.calculator.ast;
import java.util.HashMap;

public class Constants {
    public static final HashMap<String, Double> namedConstants = new HashMap<>();

    static {
        Constants.namedConstants.put("pi", Math.PI);
        Constants.namedConstants.put("e",  Math.E);
        Constants.namedConstants.put("Answer", 42.0);
        Constants.namedConstants.put("L", 6.022140857 * Math.pow(10.0, 23.0));
        Constants.namedConstants.put("Skrubben", 17.0);
        Constants.namedConstants.put("Ångans guldgruva", 10.0);
        Constants.namedConstants.put("Lovet", 4.30);
        Constants.namedConstants.put("Trump", 6000000000.0);
        Constants.namedConstants.put("År", 17.0);

    }
}
