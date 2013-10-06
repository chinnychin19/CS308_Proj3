package view.sidebar;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;


/**
 * @author susanzhang93
 * 
 */
public abstract class Module extends JPanel {

    public Module (int width, int height) {
        super();
        this.setPreferredSize(new Dimension(width, height));
    }

    private List<ModuleData> Library;

    /**
     * converts Model's output of command data to a ModuleData
     * representation
     * 
     * @return List of created ModuleData
     */
    protected abstract List<ModuleData> initializeModuleContents ();

    protected abstract void click ();

}
