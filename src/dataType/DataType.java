package dataType;

import java.util.Scanner;


public class DataType<T> {
    private T myVal;

    protected DataType (T val) {
        myVal = val;
    }

    public T value () {
        return myVal;
    }

    public String toString () {
        return myVal.toString();
    }

    public static boolean isInteger (String s) {
        s = s.trim();
        if (s.split("\\s").length != 1) return false;
        Scanner sc = new Scanner(s.trim());
        return sc.hasNextInt();
    }

    public static boolean isDouble (String s) {
        if (isInteger(s)) return false;
        s = s.trim();
        if (s.split("\\s").length != 1) return false;
        Scanner sc = new Scanner(s);
        return sc.hasNextDouble();
    }

    public static boolean isString (String s) {
        return !(isInteger(s) || isDouble(s));
    }
}
