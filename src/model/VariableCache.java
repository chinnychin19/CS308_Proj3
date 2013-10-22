package model;

import java.util.HashMap;
import java.util.Map;
import dataType.DataTypeChecker;


/**
 * 
 * Variable cache class that keeps track of user-defined variables. Utilizes a map with a string
 * identifying the variable as the key and a double as the value
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class VariableCache {
    private Map<String, Double> myMap;

    /**
     * Constructor for the variable cache. Creates a new map to hold the user-defined variables
     */
    protected VariableCache () {
        myMap = new HashMap<String, Double>();
    }

    /**
     * Gets the value for a given variable from the variable cache
     * 
     * @param variableName The name of the variable to get the value of
     * @return The value pertaining to the given variable name
     */
    public double get (String variableName) {
        if (myMap.keySet().contains(variableName)) { return myMap.get(variableName); }
        return 0; // default value of a variable if not found in cache
    }

    /**
     * Puts a variable into the variable cache. Value of the variable is a double
     * 
     * @param name The name for the user-defined variable to be added
     * @param value The double value for the user-defined variable to be added
     */
    public void put (String name, double value) {
        myMap.put(name, value);
    }

    /**
     * Puts a variable into the variable cache. Value of the variable is a string. Checks that the
     * value is a double, if not returns an error
     * 
     * @param name The name for the user-defined variable to be added
     * @param value The string value for the user-defined variable to be added
     * @return An error if the value is not a double
     */
    protected String put (String name, String value) {
        if (DataTypeChecker.isDouble(value)) {
            double val = Double.parseDouble(value);
            put(name, val);
            return "";
        }
        return "Value provided needs to be a double!";
    }

    /**
     * @return The entire variable cache as a map of variable names to variable values
     */
    protected Map<String, String> getKeyValuePairs () {
        Map<String, String> copy = new HashMap<String, String>();
        for (String key : myMap.keySet()) {
            copy.put(key, myMap.get(key).toString());
        }
        return copy;
    }

    /**
     * Clears the variable cache
     */
    protected void clear () {
        myMap.clear();
    }
}
