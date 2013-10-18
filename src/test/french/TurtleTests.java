package test.french;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class TurtleTests {
    private static final double DELTA = 0.001;

    @Test
    public void testDEVANT () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("devant 10");
        assertEquals(10, model.getTurtleY(1), DELTA);
        model.parseInput("dev 10");
        assertEquals(20, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testDERRIERE () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("derriere 10");
        assertEquals(-10, model.getTurtleY(1), DELTA);
        model.parseInput("der 10");
        assertEquals(-20, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testGAUCHE () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("gauche 10");
        assertEquals(100, model.getTurtleAngle(1), DELTA);
        model.parseInput("gc 10");
        assertEquals(110, model.getTurtleAngle(1), DELTA);
    }

    @Test
    public void testDROITE () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("droite 10");
        assertEquals(80, model.getTurtleAngle(1), DELTA);
        model.parseInput("dr 10");
        assertEquals(70, model.getTurtleAngle(1), DELTA);
    }

    @Test
    public void testDEFINIRENTETE () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("definirentete 10");
        assertEquals(10, model.getTurtleAngle(1), DELTA);
        model.parseInput("defett 20");
        assertEquals(20, model.getTurtleAngle(1), DELTA);
    }

    @Test
    public void testVERS () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("vers -10 10");
        assertEquals(135, model.getTurtleAngle(1), DELTA);
    }

    @Test
    public void testPOSITIONEXY () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("positionexy -10 10");
        assertEquals(-10, model.getTurtleX(1), DELTA);
        assertEquals(10, model.getTurtleY(1), DELTA);
        model.parseInput("aller -20 20");
        assertEquals(-20, model.getTurtleX(1), DELTA);
        assertEquals(20, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testSTYLOACTIFINACTIF () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("styloactif");
        assertEquals(true, model.isTurtleDrawing(1));
        model.parseInput("styloinactif");
        assertEquals(false, model.isTurtleDrawing(1));
        model.parseInput("sac");
        assertEquals(true, model.isTurtleDrawing(1));
        model.parseInput("sic");
        assertEquals(false, model.isTurtleDrawing(1));
    }

    @Test
    public void testMONTRERCACHER () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("montrertortue");
        assertEquals(true, model.isTurtleVisible(1));
        model.parseInput("cachertortue");
        assertEquals(false, model.isTurtleVisible(1));
        model.parseInput("mt");
        assertEquals(true, model.isTurtleVisible(1));
        model.parseInput("ct");
        assertEquals(false, model.isTurtleVisible(1));
    }

    @Test
    public void testMAISON () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("dev 100 gauche 90 dev 100");
        // go 100, turn left, go 100
        assertEquals(-100, model.getTurtleX(1), DELTA);
        assertEquals(100, model.getTurtleY(1), DELTA);

        model.parseInput("maison");
        assertEquals(0, model.getTurtleX(1), DELTA);
        assertEquals(0, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testEFFACERECRAN () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("dev 100 gauche 90 styloinactif dev 50 styloactif dev 50");
        // go 100 w/ drawing, turn left, go 50 w/o drawing, then 50 w/ drawing
        assertEquals(2, model.getTurtlePaths().size());
        assertEquals(-100, model.getTurtleX(1), DELTA);
        assertEquals(100, model.getTurtleY(1), DELTA);

        model.parseInput("effacerecran");
        assertEquals(0, model.getTurtlePaths().size());
        assertEquals(0, model.getTurtleX(1), DELTA);
        assertEquals(0, model.getTurtleY(1), DELTA);

        model.parseInput("dev 100 gauche 90 styloinactif dev 50 styloactif dev 50");
        // go 100 w/ drawing, turn left, go 50 w/o drawing, then 50 w/ drawing
        assertEquals(2, model.getTurtlePaths().size());
        assertEquals(-100, model.getTurtleX(1), DELTA);
        assertEquals(100, model.getTurtleY(1), DELTA);

        model.parseInput("ee");
        assertEquals(0, model.getTurtlePaths().size());
        assertEquals(0, model.getTurtleX(1), DELTA);
        assertEquals(0, model.getTurtleY(1), DELTA);
    }
}
