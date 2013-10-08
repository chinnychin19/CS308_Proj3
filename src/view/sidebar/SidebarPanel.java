package view.sidebar;

import java.awt.GridLayout;
import javax.swing.JPanel;


public class SidebarPanel extends JPanel {
    private static final int DISPLAY_HEIGHT = 100;
    private static final int DISPLAY_WIDTH = 300;

    public SidebarPanel () {
        super();
        setLayout(new GridLayout(3, 1));
        add(new VariableModule(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        add(new HistoryModule(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        add(new CommandsModule(DISPLAY_WIDTH, DISPLAY_HEIGHT));
    }
}
