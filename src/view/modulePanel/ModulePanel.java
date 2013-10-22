package view.modulePanel;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Model;
import view.Updatable;
import view.ViewController;


@SuppressWarnings("serial")
public class ModulePanel extends JPanel implements Updatable {
    private List<Module> myModules;
    private Model myCurrentModel;

    public ModulePanel (ViewController controller, Model model) {
        super();
        myModules = new ArrayList<Module>();
        myCurrentModel = model;
        addModule(ModuleFactory.createModule("history", controller));
        addModule(ModuleFactory.createModule("command", controller));
        addModule(ModuleFactory.createModule("variable", controller));

        setLayout(new GridLayout(myModules.size(), 1));
    }

    public void updateModules (Map<String, Collection<ModuleData>> map) {
        myModules.get(0).updateContent(map.get("history"));
        myModules.get(1).updateContent(map.get("command"));
        myModules.get(2).updateContent(map.get("variable"));

    }

    private void addModule (Module module) {
        myModules.add(module);
        add((Component) module);
    }

    public void update (Map<String, Collection<ModuleData>> moduleMap, String updateVariableError) {
        updateModules(moduleMap);
        if (!updateVariableError.equals("")) {
            JOptionPane.showMessageDialog(null,
                                          updateVariableError);
        }

    }

    private Map<String, Collection<ModuleData>> getModelInformation () {
        Map<String, Collection<ModuleData>> ret = new HashMap<String, Collection<ModuleData>>();
        ret.put("variable", getStoredVariables());
        ret.put("history", getStoredHistory());
        ret.put("command", getStoredCommand());
        return ret;
    }

    private Collection<ModuleData> getStoredVariables () {
        Collection<ModuleData> variableCollection = new ArrayList<ModuleData>();
        Map<String, String> variableMap = myCurrentModel.getAllVariables();
        for (String key : variableMap.keySet()) {

            variableCollection.add(new ModuleData(key, key));
        }
        return variableCollection;
    }

    private Collection<ModuleData> getStoredHistory () {
        Collection<ModuleData> historyCollection = new ArrayList<ModuleData>();

        for (String history : myCurrentModel.getHistory()) {
            historyCollection.add(new ModuleData(history, history));
        }
        return historyCollection;

    }

    private Collection<ModuleData> getStoredCommand () {
        Collection<ModuleData> commandCollection = new ArrayList<ModuleData>();

        Map<String, String> commandMap = myCurrentModel.getAllCommands();
        for (String key : commandMap.keySet()) {
            commandCollection.add(new ModuleData(key, commandMap.get(key)));
        }
        return commandCollection;
    }

    @Override
    public void update () {
        update(getModelInformation(), "");

    }

    @Override
    public void changeModel (Model model) {
        myCurrentModel = model;

    }

}
