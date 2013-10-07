package model;

import static org.junit.Assert.*;
import org.junit.Test;


public class ParserTests {

    @Test
    public void testBasic () {
        String input = "fd 10 fd 10 fd 10";
        Parser p = new Parser(input);
        assertEquals("fd", p.next());
        assertEquals("10", p.next());
        assertEquals("fd", p.next());
        assertEquals("10", p.next());
        assertEquals("fd", p.next());
        assertEquals("10", p.next());
    }

    @Test
    public void testBrackets () {

    }
}
