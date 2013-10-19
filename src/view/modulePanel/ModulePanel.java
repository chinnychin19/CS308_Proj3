package view.modulePanel;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import view.Controller;
import view.inputPanel.Textbox;


public class ModulePanel extends JPanel implements ModuleObserver {
    private List<Module> myModules;

    public ModulePanel (Textbox textbox, Controller controller) {
        super();
        myModules = new ArrayList<Module>();

        addModule(ModuleFactory.createModule("history", textbox, controller));
        addModule(ModuleFactory.createModule("command", textbox, controller));
        addModule(ModuleFactory.createModule("variable", textbox, controller));

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

    @Override
    public void update (Map<String, Collection<ModuleData>> moduleMap) {
        updateModules(moduleMap);

    }

}
