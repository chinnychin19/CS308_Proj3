package test;

import static org.junit.Assert.*;
import org.junit.Test;
import dataType.DataTypeChecker;


public class TestDataType {

    @Test
    public void test () {
        assertEquals(DataTypeChecker.isDouble("94.33"), true);
        assertEquals(DataTypeChecker.isInteger("73"), true);

        assertEquals(DataTypeChecker.isDouble("94..33"), false);
        assertEquals(DataTypeChecker.isDouble("94.33 9.9"), false);
        assertEquals(DataTypeChecker.isDouble("94.33.9.9"), false);
        assertEquals(DataTypeChecker.isInteger("7 0 3"), false);

        assertEquals(DataTypeChecker.isInteger("7.3"), false); // is double
        assertEquals(DataTypeChecker.isDouble("7"), false); // is integer

    }

}
