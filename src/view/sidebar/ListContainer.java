package view.sidebar;

import java.awt.Dimension;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.input.Textbox;


public class ListContainer extends JScrollPane {
    private static DefaultListModel listModel = new DefaultListModel();
    private static JList list = new JList(listModel);
    private Textbox textbox;
    private JScrollPane listScrollPane;

    // list.addListSelectionListener(new ValueReporter());
    public ListContainer (Textbox textbox) {
        super(list);
        this.textbox = textbox;

        // listScrollPane = new JScrollPane(list);

    }

    private class ValueReporter implements ListSelectionListener {

        public void valueChanged (ListSelectionEvent event) {
            if (!event.getValueIsAdjusting() && list.getSelectedValue() != null) {

                ModuleData md = (ModuleData) list.getSelectedValue();
                textbox.setText(md.content);

            }

        }
    }

    public void add (String string) {
        listModel.addElement(string);

    }

    public JScrollPane getPane () {
        return listScrollPane;
    }
}
