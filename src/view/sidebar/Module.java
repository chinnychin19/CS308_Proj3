package view.sidebar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;


/**
 * @author susanzhang93
 * 
 */
@SuppressWarnings("serial")
public abstract class Module extends JPanel {
    private static final int DISPLAY_HEIGHT = 100;
    private static final int DISPLAY_WIDTH = 300;
    private List<ModuleData> Library;

    public Module () {
        super();
        this.setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        initializeModuleDisplay();
    }

    public Module (int width, int height) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        initializeModuleDisplay();
    }

    private void initializeModuleDisplay () {

        setBackground(Color.blue);
    }

    /**
     * converts Model's output of command data to a ModuleData
     * representation
     * 
     * @return List of created ModuleData
     */
    protected abstract List<ModuleData> initializeModuleContents ();

    protected abstract void click ();

}
