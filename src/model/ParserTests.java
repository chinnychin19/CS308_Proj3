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

    @Test
    public void testCondition () { // TODO: include ifelse test
        Model model = new Model();
        String input = "if lessp xcor 10 [ fd 3 fd 4 fd sum 3 4 ]";
        Parser p = new Parser(input, model);
        try {
            assertEquals(input, p.nextExpression());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMultiParam () {
        Model model = new Model();
        String input = "fd ( sum 3 4 5 )";
        Parser p = new Parser(input, model);
        try {
            assertEquals(input, p.nextExpression());
        }
        catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testUserCommands () {
        Model model = new Model();
        String input = "to simpleMove [ :step ] [ fd :step ] simpleMove 2";
        String status = model.parseInput("to simpleMove [ :step ] [ fd :step ]");
        Parser p = new Parser(input, model);
        try {
            assertEquals("to simpleMove [ :step ] [ fd :step ]", p.nextExpression());
            assertEquals("simpleMove 2", p.nextExpression());
        }
        catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
