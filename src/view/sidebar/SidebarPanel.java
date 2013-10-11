package view.sidebar;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import view.input.Textbox;


@SuppressWarnings("serial")
public class SidebarPanel extends JPanel {
    private List<ContentContainer> ContentContainers;

    public SidebarPanel (Textbox textbox) {
        super();
        ContentContainers = new ArrayList<ContentContainer>();
        setLayout(new GridLayout(3, 1));
        addModule(new HistoryModule(textbox));
        addModule(new CommandsModule(textbox));
        addModule(new VariableModule(textbox));
    }

    public void updateModules () {
        for (ContentContainer module : ContentContainers) {
            module.updateContent();
        }
    }

    private void addModule (ContentContainer module) {
        ContentContainers.add(module);
        add((Component) module);
    }
}
