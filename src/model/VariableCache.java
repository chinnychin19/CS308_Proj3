package model;

import java.util.HashMap;
import java.util.Map;
import dataType.DataTypeChecker;


public class VariableCache {
    // TODO
    private Map<String, Double> myMap;

    protected VariableCache () {
        myMap = new HashMap<String, Double>();
    }

    public double get (String key) {
        if (myMap.keySet().contains(key)) { return myMap.get(key); }
        return 0; // default value of a variable if not found in cache
    }

    public void put (String key, double value) {
        myMap.put(key, value);
    }

    protected String put (String key, String value) {
        if (DataTypeChecker.isDouble(value)) {
            double val = Double.parseDouble(value);
            put(key, val);
            return "";
        }
        return "Value provided needs to be a double!";
    }

    protected Map<String, String> getKeyValuePairs () {
        Map<String, String> copy = new HashMap<String, String>();
        for (String key : myMap.keySet()) {
            copy.put(key, myMap.get(key).toString());
        }
        return copy;
    }

    protected void clear () {
        myMap.clear();
    }
}
