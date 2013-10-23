package view.sidebarPanel;

import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import view.Constants;
import view.ViewController;


@SuppressWarnings("serial")
public class EditBar extends JPanel {

    JButton myEdit;
    TextField myTextfield;
    JList<ModuleData> myList;
    DefaultListModel<ModuleData> myListModel;
    ViewController myController;

    public EditBar (JList<ModuleData> list,
                    DefaultListModel<ModuleData> listModel,
                    ViewController controller) {
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
        myTextfield.setColumns(Constants.TEXTBOX_COLUMNS);
        return myTextfield;
    }

    private JButton addEditButton () {
        myEdit = new JButton(Constants.EDIT_BUTTON_NAME);
        myEdit.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {

                int index = myList.getSelectedIndex();
                if (index != -1) {

                    ModuleData selected = (ModuleData) myListModel.get(index);
                    String newValue = myTextfield.getText();
                    myTextfield.setText("");

                    (myController).updateVariable(selected
                            .getDisplay(), newValue);

                }
                else {
                    JOptionPane.showMessageDialog(null,
                                                  Constants.SELECT_A_VARIABLE_MESSAGE);
                }
            }

        });

        return myEdit;
    }

}
