package view.sidebar;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import view.input.Textbox;


@SuppressWarnings("serial")
public class SidebarPanel extends JPanel {
    private List<Module> Modules;

    public SidebarPanel (Textbox textbox) {
        super();
        Modules = new ArrayList<Module>();

        addModule(ModuleFactory.createModule("history", textbox));
        addModule(ModuleFactory.createModule("command", textbox));
        addModule(ModuleFactory.createModule("variable", textbox));

        setLayout(new GridLayout(Modules.size(), 1));
    }

    public void updateModules () {
        for (Module module : Modules) {
            module.updateContent();
        }
    }

    private void addModule (Module module) {
        Modules.add(module);
        add((Component) module);
    }
}
