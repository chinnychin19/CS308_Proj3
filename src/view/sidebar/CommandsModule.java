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
        // TODO Auto-generated constructor stub
    }

    @Override
    protected List<ModuleData> initializeModuleContents () {
       
      JList list = new JList();
      DefaultListModel listModel = new DefaultListModel();
      listModel = new DefaultListModel();
      listModel.addElement("Jane Doe");
      listModel.addElement("John Smith");
      listModel.addElement("Kathy Green");

      //Create the list and put it in a scroll pane.
      list = new JList(listModel);
      JScrollPane listScrollPane = new JScrollPane(list);
      add(listScrollPane);

        return null;
    }
    public void mouseClicked(MouseEvent evt) {
        System.out.println("test");
        JList list = (JList)evt.getSource();
        if (evt.getClickCount() == 2) {
            int index = list.locationToIndex(evt.getPoint());
        } else if (evt.getClickCount() == 3) {   // Triple-click
            int index = list.locationToIndex(evt.getPoint());

        }
    }
    @Override
    protected void click () {
        // TODO Auto-generated method stub

    }

}
