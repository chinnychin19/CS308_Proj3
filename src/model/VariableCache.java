package model;

import java.util.HashMap;
import java.util.Map;
import dataType.DataType;
import dataType.DataTypeFactory;


public class VariableCache {
    // TODO
    private Map<String, DataType> myMap;

    protected VariableCache () {
        myMap = new HashMap<String, DataType>();
    }

    protected DataType get (String key) {
        return myMap.get(key);
    }

    protected void put (String key, DataType value) {
        myMap.put(key, value);
    }

    public void put (String key, String value) {
        DataType dt = DataTypeFactory.getDataType(value);
        myMap.put(key, dt);
    }

    public Map<String, String> getKeyValuePairs () {
        Map<String, String> copy = new HashMap<String, String>();
        for (String key : myMap.keySet()) {
            copy.put(key, myMap.get(key).toString());
        }
        return copy;
    }

    // TODO: should this be protected? Does View need access?
    public void clear () {
        myMap.clear();
    }
}
