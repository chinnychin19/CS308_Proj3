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
import view.Controller;


@SuppressWarnings("serial")
public class EditBar extends JPanel {
    private static final int TEXTBOX_COLUMNS = 15;
    JButton myEdit;
    TextField myTextfield;
    JList<ModuleData> myList;
    DefaultListModel<ModuleData> myListModel;
    Controller myController;

    public EditBar (JList<ModuleData> list,
                    DefaultListModel<ModuleData> listModel,
                    Controller controller) {
        myController = controller;
        myList = list;
        myListModel = listModel;
        JPanel bottomPane = new JPanel();
        bottomPane.add(addTextBox());
        bottomPane.add(addEditButton());
        this.add(bottomPane, BorderLayout.NORTH);

    }

    private TextField addTextBox () {
        myTextfield = new TextField();
        myTextfield.setColumns(TEXTBOX_COLUMNS);
        return myTextfield;
    }

    private JButton addEditButton () {
        myEdit = new JButton("Edit");
        myEdit.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {

                int index = myList.getSelectedIndex();
                if (index != -1) {

                    ModuleData selected = (ModuleData) myListModel.get(index);
                    String newValue = myTextfield.getText();
                    myTextfield.setText("");

                    ((ModulePanelController) myController).updateVariable(selected
                            .getDisplay(), newValue);

                }
                else {
                    JOptionPane.showMessageDialog(null,
                                                  "Select a variable");
                }
            }

        });

        return myEdit;
    }

}
