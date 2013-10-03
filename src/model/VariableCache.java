package model;

import java.util.HashMap;
import java.util.Map;
import dataType.DataType;

public class VariableCache {
    //TODO
    private Map<String, DataType> myMap;
    protected VariableCache() {
        myMap = new HashMap<String, DataType>();
    }
    
    public DataType get(String key) {
        return myMap.get(key);
    }
    
    public void put(String key, DataType value) {
        myMap.put(key, value);
    }
    
    //TODO: should this be protected? Does View need access?
    public void clear() {
        myMap.clear();
    }
}
