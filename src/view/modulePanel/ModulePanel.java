package view.modulePanel;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import view.inputPanel.Textbox;


@SuppressWarnings("serial")
public class ModulePanel extends JPanel {
    private List<Module> myModules;

    public ModulePanel (Textbox textbox) {
        super();
        myModules = new ArrayList<Module>();

        addModule(ModuleFactory.createModule("history", textbox));
        addModule(ModuleFactory.createModule("command", textbox));
        addModule(ModuleFactory.createModule("variable", textbox));

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
}
