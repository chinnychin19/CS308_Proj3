package view.sidebar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 * @author susanzhang93
 * 
 */
@SuppressWarnings("serial")
public abstract class Module extends JPanel {
    private static final int DISPLAY_HEIGHT = 100;
    private static final int DISPLAY_WIDTH = 300;
    private List<ModuleData> Library;
    private JList sampleJList;

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

    protected abstract void updatePanel ();

    private void initializeModuleDisplay () {
        setBackground(Color.blue);
        Collection<ModuleData> listData = getStoredModelInformation();

        ModuleData[] entries = new ModuleData[listData.size()];
        int i = 0;
        for (ModuleData entry : listData) {
            entries[i] = entry;
            i++;
        }
        sampleJList = new JList(entries);
        sampleJList.addListSelectionListener(new ValueReporter());
        JScrollPane listPane = new JScrollPane(sampleJList);
        add(listPane);
    }

    protected abstract Collection<ModuleData> getStoredModelInformation ();

    /**
     * converts Model's output of command data to a ModuleData
     * representation
     * 
     * @return List of created ModuleData
     */
    protected abstract List<ModuleData> initializeModuleContents ();

    protected abstract void click ();

    private class ValueReporter implements ListSelectionListener {
        /**
         * You get three events in many cases -- one for the deselection
         * of the originally selected entry, one indicating the selection
         * is moving, and one for the selection of the new entry. In
         * the first two cases, getValueIsAdjusting returns true,
         * thus the test below when only the third case is of interest.
         */
        public void valueChanged (ListSelectionEvent event) {
            if (!event.getValueIsAdjusting()) {
                ModuleData md = (ModuleData) sampleJList.getSelectedValue();
                System.out.println(md.getContent());
            }

        }
    }
}
