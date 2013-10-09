package view.sidebar;

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
        setLayout(new GridLayout(3, 1));
        addModule(new HistoryModule(textbox));
        // addModule(new CommandsModule(textbox));
    }

    public void updateModules () {
        for (Module module : Modules) {
            module.updateContent();
        }
    }

    private void addModule (Module module) {
        Modules.add(module);
        add(module);
    }
}
