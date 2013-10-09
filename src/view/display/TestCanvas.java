package view.display;

import jgame.platform.JGEngine;
import org.jbox2d.common.Vec2;
import org.junit.Test;
import view.Constants;
import static org.junit.Assert.*;


public class TestCanvas {
    Canvas eng = new Canvas();
    private final double DELTA = 0.01;

    @Test
    public void testOnScreen () {
        assertEquals(1, 1, DELTA);
        assertEquals(eng.checkOnScreen(1.1, 1.1).x, 1.1, DELTA);
        assertEquals(eng.checkOnScreen(1.1, 1.1).y, 1.1, DELTA);
    }
}
