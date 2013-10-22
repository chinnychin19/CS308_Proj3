package dataType;

import java.util.Scanner;


/**
 * 
 * Class to check the type of a string. Contains various methods that pass in a string and checks
 * whether or not they are of a certain type
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class DataTypeChecker {
    /**
     * Checks if the given string is an integer
     * 
     * @param s The string to check
     * @return True if the given string is an integer, false if it isn't
     */
    public static boolean isInteger (String s) {
        s = s.trim();
        if (s.split("\\s").length != 1) { return false; }
        Scanner sc = new Scanner(s.trim());
        return sc.hasNextInt();
    }

    /**
     * Checks if the given string is a double
     * 
     * @param s The string to check
     * @return True if the given string is a double, false if it isn't
     */
    public static boolean isDouble (String s) {
        if (isInteger(s)) { return false; }
        s = s.trim();
        if (s.split("\\s").length != 1) { return false; }
        Scanner sc = new Scanner(s);
        return sc.hasNextDouble();
    }

    /**
     * Checks if the given string is not a double or integer
     * 
     * @param s The string to check
     * @return True if the given string is not a double or integer, false if it isn't
     */
    public static boolean isString (String s) {
        return !(isInteger(s) || isDouble(s));
    }

    /**
     * Checks if the given string is either a double or integer
     * 
     * @param s The string to check
     * @return True if the given string is either a double or integer, false if it isn't
     */
    public static boolean isNumber (String s) {
        return isDouble(s) || isInteger(s);
    }

}
