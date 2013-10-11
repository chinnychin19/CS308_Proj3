package model;

import static org.junit.Assert.*;
import org.junit.Test;


public class ParserTests {

    @Test
    public void testBasic () {
        Model.initModel();
        String input = "fd 10 fd 10 fd 10";
        Parser p = new Parser(input);
        assertEquals("fd", p.nextWord());
        assertEquals("10", p.nextWord());
        assertEquals("fd", p.nextWord());
        assertEquals("10", p.nextWord());
        assertEquals("fd", p.nextWord());
        assertEquals("10", p.nextWord());
    }

    @Test
    public void testBrackets () {
        Model.initModel();
        String input = "repeat 5 [fd 10 fd 10]";
        Parser p = new Parser(input);
        assertEquals("repeat", p.nextWord());
        assertEquals("5", p.nextExpression());
        assertEquals("[fd 10 fd 10]", p.nextList());
        assertEquals(false, p.hasNext());
    }

    @Test
    public void testNestedBrackets () {
        Model.initModel();
        String input = "repeat dotimes [ :var 5 ] [ sum :var 17 ] [ 10 fd 10 ]";
        Parser p = new Parser(input);
        assertEquals("repeat", p.nextWord());
        assertEquals("dotimes [ :var 5 ] [ sum :var 17 ]", p.nextExpression());
        assertEquals("[ 10 fd 10 ]", p.nextList());
        assertEquals(false, p.hasNext());
    }

    @Test
    public void testRepeatExpression () {
        Model.initModel();
        String input = "repeat sum 3 3 [ 10 fd 10 ]";
        Parser p = new Parser(input);
        assertEquals("repeat", p.nextWord());
        assertEquals("sum 3 3", p.nextExpression());
        assertEquals("[ 10 fd 10 ]", p.nextList());
        assertEquals(false, p.hasNext());
    }
}
