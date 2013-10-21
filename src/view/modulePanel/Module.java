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
import view.Controller;


/**
 * @author susanzhang93
 * 
 */
@SuppressWarnings("serial")
public class Module extends JPanel {
    private static final int DISPLAY_HEIGHT = 100;
    private static final int DISPLAY_WIDTH = 300;
    protected JList<ModuleData> myList;
    protected DefaultListModel<ModuleData> myListModel;

    private String MODULE_NAME;
    private ModulePanelController myController;

    protected Module (String name, Controller controller) {

        super();
        setModuleName();
        myController = (ModulePanelController) controller;

        setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        MODULE_NAME = name;
        initializeModuleDisplay();

    }

    /**
     * Initializes settings relevant to display
     */
    private void initializeModuleDisplay () {
        setBackground(Color.white);
        setModuleName();
        initializeModuleContents();
    }

    /**
     * Adds JLabel of Module Name to Module
     * to set up display title
     */
    private void setModuleName () {
        this.add(new JLabel(MODULE_NAME));

    }

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
                    myController.populateTextBox(selected.myContent);

                }

            }

        });
    }

}
