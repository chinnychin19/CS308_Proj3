package view.modulePanel;

import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Model;


@SuppressWarnings("serial")
public class EditBar extends JPanel {
    private static final int TEXTBOX_COLUMNS = 15;
    JButton edit;
    TextField textfield;
    JList<ModuleData> list;
    DefaultListModel<ModuleData> listModel;

    public EditBar (JList<ModuleData> list, DefaultListModel<ModuleData> listModel) {
        this.list = list;
        this.listModel = listModel;
        JPanel bottomPane = new JPanel();
        bottomPane.add(addTextBox());
        bottomPane.add(addEditButton());
        this.add(bottomPane, BorderLayout.NORTH);

    }

    private TextField addTextBox () {
        textfield = new TextField();
        textfield.setColumns(TEXTBOX_COLUMNS);
        return textfield;
    }

    private JButton addEditButton () {
        edit = new JButton("Edit");
        edit.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {

                int index = list.getSelectedIndex();
                if (index != -1) {

                    ModuleData selected = (ModuleData) listModel.get(index);
                    String newValue = textfield.getText();
                    String putStatus = Model.putVariable(selected.getDisplay(), newValue);
                    updateVariable(index, selected, putStatus);
                }
                else {
                    JOptionPane.showMessageDialog(null,
                                                  "Select a variable");
                }
            }

            private void displayInputError (String putStatus) {
                JOptionPane.showMessageDialog(null,
                                              putStatus);

            }

            private void updateVariable (int index, ModuleData selected, String putStatus) {
                if (putStatus.equals("")) {

                    listModel.remove(index);
                    listModel.add(index, selected);
                    list.setSelectedIndex(index);
                    list.ensureIndexIsVisible(index);
                }
                else {
                    displayInputError(putStatus);
                    textfield.setText("");
                }

            }
        });

        return edit;
    }

}
