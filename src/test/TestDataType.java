package test;

import static org.junit.Assert.*;
import org.junit.Test;
import dataType.DataType;

public class TestDataType {

    @Test
    public void test () {
        assertEquals(DataType.isDouble("94.33"), true);
        assertEquals(DataType.isInteger("73"), true);

        assertEquals(DataType.isDouble("94..33"), false);
        assertEquals(DataType.isDouble("94.33 9.9"), false);
        assertEquals(DataType.isDouble("94.33.9.9"), false);
        assertEquals(DataType.isInteger("7 0 3"), false);
        
        assertEquals(DataType.isInteger("7.3"), false); //is double
        assertEquals(DataType.isDouble("7"), false); //is integer
        
    }

}
