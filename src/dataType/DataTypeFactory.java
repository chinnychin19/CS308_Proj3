package dataType;

public class DataTypeFactory {
    public static DataType getDataType (String s) {
        if (DataType.isInteger(s)) { return new DataType<Integer>(Integer.parseInt(s)); }
        if (DataType.isDouble(s)) { return new DataType<Double>(Double.parseDouble(s)); }
        return new DataType<String>(s);
    }
}
