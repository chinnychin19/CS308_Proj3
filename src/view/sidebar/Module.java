package view.sidebar;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Collection;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.input.Textbox;


/**
 * @author susanzhang93
 * 
 */
@SuppressWarnings("serial")
public abstract class Module extends JPanel {
    private static final int DISPLAY_HEIGHT = 100;
    private static final int DISPLAY_WIDTH = 300;
    protected JList list;
    protected DefaultListModel listModel;
    private Textbox textbox;

    public Module (Textbox textbox) {

        super();
        setModuleName(getModuleName());
        this.textbox = textbox;
        setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        initializeModuleDisplay();
    }

    public Module (int width, int height, Textbox textbox) {

        super();
        setModuleName(getModuleName());
        this.textbox = textbox;
        setPreferredSize(new Dimension(width, height));
        initializeModuleDisplay();

    }

    public void updateContent () {
        Collection<ModuleData> listData = getStoredModelInformation();

        listModel.clear();

        for (ModuleData moduleData : listData) {
            listModel.addElement(moduleData);

        }
    }

    protected void initializeModuleContents () {
        //
        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged (ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && list.getSelectedValue() != null) {
                    ModuleData selected = (ModuleData) list.getSelectedValue();
                    textbox.setText(selected.content);

                }

            }

        });
        JScrollPane listScrollPane = new JScrollPane(list);
        add(listScrollPane);

    }

    private void setModuleName (String moduleName) {
        this.add(new JLabel(moduleName));

    }

    protected abstract String getModuleName ();

    private void initializeModuleDisplay () {
        setBackground(Color.white);
        initializeModuleContents();
    }

    protected abstract Collection<ModuleData> getStoredModelInformation ();

}
