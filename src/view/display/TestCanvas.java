package view.display;

<<<<<<< HEAD

import jgame.JGPoint;
=======
>>>>>>> frontend
import jgame.platform.JGEngine;
import org.jbox2d.common.Vec2;
import org.junit.Test;
import view.Constants;
import static org.junit.Assert.*;

<<<<<<< HEAD
public class TestCanvas  {
    Canvas eng = new Canvas(new JGPoint(600, 600));
=======

public class TestCanvas {
    Canvas eng = new Canvas();
>>>>>>> frontend
    private final double DELTA = 0.01;

    @Test
<<<<<<< HEAD
    public void testOnScreen(){     
        Vec2 testVector = eng.forceWithinBounds(1500, 1500);
        assertEquals(300, testVector.x, DELTA);
        assertEquals(300, testVector.y, DELTA);
        
        testVector = eng.forceWithinBounds(-800, -800);
        assertEquals(400, testVector.x, DELTA);
        assertEquals(400, testVector.y, DELTA);
=======
    public void testOnScreen () {
        assertEquals(1, 1, DELTA);
        assertEquals(eng.checkOnScreen(1.1, 1.1).x, 1.1, DELTA);
        assertEquals(eng.checkOnScreen(1.1, 1.1).y, 1.1, DELTA);
>>>>>>> frontend
    }
}
