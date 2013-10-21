package model;

import static org.junit.Assert.*;
import org.junit.Test;


public class ParserTests {

    @Test
    public void testBasic () {
        Model model = new Model();
        String input = "fd 10 fd 10 fd 10";
        Parser p = new Parser(input, model);
        assertEquals("fd", p.nextWord());
        assertEquals("10", p.nextWord());
        assertEquals("fd", p.nextWord());
        assertEquals("10", p.nextWord());
        assertEquals("fd", p.nextWord());
        assertEquals("10", p.nextWord());
    }

    @Test
    public void testBrackets () {
        Model model = new Model();
        String input = "repeat 5 [fd 10 fd 10]";
        Parser p = new Parser(input, model);
        try {
            assertEquals("repeat", p.nextWord());
            assertEquals("5", p.nextExpression());
            assertEquals("[fd 10 fd 10]", p.nextList());
            assertEquals(false, p.hasNext());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNestedBrackets () {
        Model model = new Model();
        String input = "repeat dotimes [ :var 5 ] [ sum :var 17 ] [ 10 fd 10 ]";
        Parser p = new Parser(input, model);
        try {
            assertEquals("repeat", p.nextWord());
            assertEquals("dotimes [ :var 5 ] [ sum :var 17 ]", p.nextExpression());
            assertEquals("[ 10 fd 10 ]", p.nextList());
            assertEquals(false, p.hasNext());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRepeatExpression () {
        Model model = new Model();
        String input = "repeat sum 3 3 [ 10 fd 10 ]";
        Parser p = new Parser(input, model);
        try {
            assertEquals("repeat", p.nextWord());
            assertEquals("sum 3 3", p.nextExpression());
            assertEquals("[ 10 fd 10 ]", p.nextList());
            assertEquals(false, p.hasNext());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
