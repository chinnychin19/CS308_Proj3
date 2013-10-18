package view.modulePanel;

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
import view.inputPanel.Textbox;


/**
 * @author susanzhang93
 * 
 */
@SuppressWarnings("serial")
public abstract class Module extends JPanel {
    private static final int DISPLAY_HEIGHT = 100;
    private static final int DISPLAY_WIDTH = 300;
    protected JList<ModuleData> myList;
    protected DefaultListModel<ModuleData> myListModel;
    private Textbox myTextbox;

    protected Module (Textbox textbox) {

        super();
        setModuleName();
        this.myTextbox = textbox;
        setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        initializeModuleDisplay();
    }

    protected Module (int width, int height, Textbox textbox) {

        super();
        setModuleName();
        this.myTextbox = textbox;
        setPreferredSize(new Dimension(width, height));
        initializeModuleDisplay();

    }

    /**
     * Initializes settings relevant to display
     */
    private void initializeModuleDisplay () {
        setBackground(Color.white);
        initializeModuleContents();
    }

    /**
     * Adds JLabel of Module Name to Module
     * to set up display title
     */
    private void setModuleName () {
        this.add(new JLabel(getModuleName()));

    }

    /**
     * @return Name of Module
     */
    protected abstract String getModuleName ();

    /**
     * retrieves the stored information relevant to Module from
     * backend
     * 
     * @return Collection of Model data converted encapsulated as ModuleData
     *         objects
     */
    // protected abstract Collection<ModuleData> getStoredModelInformation ();

    /**
     * Updates the content of JList options
     */
    protected void updateContent (Collection<ModuleData> listData) {
        myListModel.clear();
        for (ModuleData moduleData : listData) {
            myListModel.addElement(moduleData);

        }
    }

    /**
     * Initlizes JList contents and adds
     */
    private void initializeModuleContents () {
        myListModel = new DefaultListModel<ModuleData>();
        myList = new JList<ModuleData>(myListModel);
        addSelectionListener(myList);

        JScrollPane listScrollPane = new JScrollPane(myList);
        add(listScrollPane);

    }

    /**
     * Adds a selection listener to a JList
     * 
     * @param list Jlist selection listener needs to be added to
     */
    private void addSelectionListener (final JList<ModuleData> list) {
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged (ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && list.getSelectedValue() != null) {
                    ModuleData selected = (ModuleData) list.getSelectedValue();
                    myTextbox.setText(selected.myContent);

                }

            }

        });
    }

}
