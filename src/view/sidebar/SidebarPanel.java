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

        addModule(new HistoryModule(textbox));
        addModule(new CommandsModule(textbox));
        addModule(new VariableModule(textbox));
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
