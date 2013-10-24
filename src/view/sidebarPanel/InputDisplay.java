package view.sidebarPanel;

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
import view.Constants;
import view.ViewController;


/**
 * 
 * Acts as a container that displays the different types of user input.
 * Each element of displayed information is clickable and copy/paste enabled
 * This class gathers the information to be displayed from the Model and displays it
 * using a JList
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 * 
 */
@SuppressWarnings("serial")
public class InputDisplay extends JPanel {

    protected JList<InputDisplayData> myList;
    protected DefaultListModel<InputDisplayData> myListModel;
    private String inputDisplayName;
    private ViewController myController;

    protected InputDisplay (String name, ViewController controller) {

        super();
        myController = controller;
        inputDisplayName = name;
        initializeDisplay();

    }

    /**
     * Initializes settings relevant to display
     */
    private void initializeDisplay () {
        setBackground(Color.white);
        setName();
        initializeContents();
        setPreferredSize(new Dimension(Constants.INPUT_DISPLAY_WIDTH,
                                       Constants.INPUT_DISPLAY_HEIGHT));
    }

    /**
     * Adds JLabel of Module Name to Module
     * to set up display title
     */
    private void setName () {
        this.add(new JLabel(inputDisplayName));

    }

    /**
     * Updates the content of JList options
     */
    protected void updateContent (Collection<InputDisplayData> listData) {
        myListModel.clear();
        for (InputDisplayData moduleData : listData) {
            myListModel.addElement(moduleData);

        }
    }

    /**
     * Initlizes JList contents and adds
     */
    private void initializeContents () {
        myListModel = new DefaultListModel<InputDisplayData>();
        myList = new JList<InputDisplayData>(myListModel);
        addSelectionListener(myList);
        JScrollPane listScrollPane = new JScrollPane(myList);
        add(listScrollPane);

    }

    /**
     * Adds a selection listener to a JList
     * 
     * @param list Jlist selection listener needs to be added to
     */
    private void addSelectionListener (final JList<InputDisplayData> list) {
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged (ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && list.getSelectedValue() != null) {
                    InputDisplayData selected = (InputDisplayData) list.getSelectedValue();
                    myController.populateTextBox(selected.getContent());

                }

            }

        });
    }

}
