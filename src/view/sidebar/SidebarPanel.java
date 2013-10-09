package view.sidebar;

import java.awt.GridLayout;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class SidebarPanel extends JPanel {

    public SidebarPanel () {
        super();
        setLayout(new GridLayout(3, 1));
        add(new VariableModule());
        // add(new HistoryModule());
        // add(new CommandsModule());
    }
}
