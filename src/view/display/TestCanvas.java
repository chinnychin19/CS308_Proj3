package view.display;



import jgame.JGPoint;
import jgame.platform.JGEngine;
import org.jbox2d.common.Vec2;
import org.junit.Test;
import view.Constants;
import static org.junit.Assert.*;

public class TestCanvas  {
    Canvas eng = new Canvas(new JGPoint(600, 600));

    private final double DELTA = 0.01;

    @Test

    public void testOnScreen(){     
        Vec2 testVector = eng.forceWithinBounds(1500, 1500);
        assertEquals(300, testVector.x, DELTA);
        assertEquals(300, testVector.y, DELTA);
        
        testVector = eng.forceWithinBounds(-800, -800);
        assertEquals(400, testVector.x, DELTA);
        assertEquals(400, testVector.y, DELTA);

    }
}
