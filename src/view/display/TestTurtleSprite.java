package view.display;

import jgame.JGObject;
import jgame.JGPoint;
import jgame.platform.JGEngine;
import org.junit.BeforeClass;
import org.junit.Test;
import view.Constants;
import static org.junit.Assert.*;


public class TestTurtleSprite {
    static TurtleSprite turtle;

    @BeforeClass
    public static void setUpBeforeClass () throws Exception {
        Canvas eng = new Canvas();
        WorldManager wm = new WorldManager();
        wm.initWorld(eng);
        wm.getWorld();
        turtle = new TurtleSprite(eng, 0, 0, 1);
    }

    @Test
    public void testMove () {
        assertEquals(0, 0, 0.01);

    }

}
