package view.sidebar;

import java.awt.Dimension;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;


public class SideBar extends JPanel {
    protected CommandsModule myCommandsModule;
    protected HistoryModule myHistoryModule;
    protected VariableModule myVariableModule;
    private JList list;
    private DefaultListModel listModel;

    public SideBar (int width, int height) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        listModel = new DefaultListModel();
        listModel.addElement("Jane Doe");
        listModel.addElement("John Smith");
        listModel.addElement("Kathy Green");
    }

    public void update () {

    }
}
