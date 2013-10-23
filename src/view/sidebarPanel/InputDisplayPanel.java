package view.sidebarPanel;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Model;
import view.Constants;
import view.Updatable;
import view.ViewController;


/**
 * This class holds InputDisplays that are displayed
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 * 
 */
@SuppressWarnings("serial")
public class InputDisplayPanel extends JPanel implements Updatable {

    private Map<String, InputDisplay> myInputDisplays;
    private Model myCurrentModel;

    /**
     * @param controller controller used to send information to Model
     * @param model Model used to receive updates
     */
    public InputDisplayPanel (ViewController controller, Model model) {
        super();
        myInputDisplays = new HashMap<String, InputDisplay>();
        myCurrentModel = model;
        addInputDisplayToSideBar(Constants.HISTORY_INPUT_DISPLAY_NAME,
                                 InputDisplayFactory
                                         .createInputDisplays(Constants.HISTORY_INPUT_DISPLAY_NAME,
                                                              controller));
        addInputDisplayToSideBar(Constants.COMMAND_INPUT_DISPLAY_NAME,
                                 InputDisplayFactory
                                         .createInputDisplays(Constants.COMMAND_INPUT_DISPLAY_NAME,
                                                              controller));
        addInputDisplayToSideBar(Constants.VARIABLE_INPUT_DISPLAY_NAME,
                                 InputDisplayFactory
                                         .createInputDisplays(Constants.VARIABLE_INPUT_DISPLAY_NAME,
                                                              controller));

        setLayout(new GridLayout(myInputDisplays.size(), 1));
    }

    /**
     * Used to update InputDisplays with updated content to display in GUI
     */
    private void updateInputDisplays () {
        myInputDisplays.get(Constants.HISTORY_INPUT_DISPLAY_NAME).updateContent(getStoredHistory());
        myInputDisplays.get(Constants.COMMAND_INPUT_DISPLAY_NAME).updateContent(getStoredCommand());
        myInputDisplays.get(Constants.VARIABLE_INPUT_DISPLAY_NAME)
                .updateContent(getStoredVariables());

    }

    /**
     * A method to add new InputDisplays to Sidebar
     * 
     * @param name name of InputDisplay to be identified by
     * @param inputDisplay InputDisplay to add to Sidebar
     */
    private void addInputDisplayToSideBar (String name, InputDisplay inputDisplay) {
        myInputDisplays.put(name, inputDisplay);
        add((Component) inputDisplay);
    }

    /**
     * Gathers all the variables the user defined
     * 
     * @return a collection of InputDisplayData representing the stored variables
     */
    private Collection<InputDisplayData> getStoredVariables () {
        Collection<InputDisplayData> variableCollection = new ArrayList<InputDisplayData>();
        Map<String, String> variableMap = myCurrentModel.getAllVariables();
        for (String key : variableMap.keySet()) {

            variableCollection.add(new InputDisplayData(key, key));
        }
        return variableCollection;
    }

    /**
     * Gathers all the history of user input
     * 
     * @return a collection of InputDisplayData representing the stored History
     */
    private Collection<InputDisplayData> getStoredHistory () {
        Collection<InputDisplayData> historyCollection = new ArrayList<InputDisplayData>();

        for (String history : myCurrentModel.getHistory()) {
            historyCollection.add(new InputDisplayData(history, history));
        }
        return historyCollection;

    }

    /**
     * Gathers all of the stored commands
     * 
     * @return a collection of InputDisplayData representing the stored Commands
     */
    private Collection<InputDisplayData> getStoredCommand () {
        Collection<InputDisplayData> commandCollection = new ArrayList<InputDisplayData>();

        Map<String, String> commandMap = myCurrentModel.getAllCommands();
        for (String key : commandMap.keySet()) {
            commandCollection.add(new InputDisplayData(key, commandMap.get(key)));
        }
        return commandCollection;
    }

    @Override
    public void update (String error) {
        updateInputDisplays();
        if (!error.equals("")) {
            JOptionPane.showMessageDialog(null,
                                          error);
        }

    }

    @Override
    public void changeModel (Model model) {
        myCurrentModel = model;

    }

}
