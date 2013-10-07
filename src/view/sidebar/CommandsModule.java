package view.sidebar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;


public class CommandsModule extends Module {

    public CommandsModule (int width, int height) {
        super(width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.add(new JLabel("Commands Module"));

    }

    @Override
    protected List<ModuleData> initializeModuleContents () {

        return null;
    }

    public void mouseClicked (MouseEvent evt) {

    }

    @Override
    protected void click () {
        // TODO Auto-generated method stub

    }

}
