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


/**
 * Panel used to enter input when user wants to edit a variable value
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 */
@SuppressWarnings("serial")
class EditBar extends JPanel {

    private JButton myEdit;
    private TextField myTextfield;
    private JList<InputDisplayData> myList;
    private DefaultListModel<InputDisplayData> myListModel;
    private ViewController myController;

    /**
     * @param list the Jlist that the different variables are displayed in
     * @param listModel the listModel the variables are stored in
     * @param controller the controller used to communicate with the model
     */
    protected EditBar (JList<InputDisplayData> list,
                       DefaultListModel<InputDisplayData> listModel,
                       ViewController controller) {
        myController = controller;
        myList = list;
        myListModel = listModel;

        JPanel bottomPane = new JPanel();
        bottomPane.add(addTextBox());
        bottomPane.add(addEditButton());
        this.add(bottomPane, BorderLayout.NORTH);

    }

    /**
     * Helper method to add the TextBox to the EditBar panel
     * 
     * @return
     */
    private TextField addTextBox () {
        myTextfield = new TextField();
        myTextfield.setColumns(Constants.TEXTBOX_COLUMNS);
        return myTextfield;
    }

    /**
     * Creates a button that when clicked, updates a variable value
     * with input specified in textfield
     * 
     * @return
     */
    private JButton addEditButton () {
        myEdit = new JButton(Constants.EDIT_BUTTON_NAME);
        myEdit.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {

                updateVariable();
            }

            private void updateVariable () {
                int index = myList.getSelectedIndex();
                if (index != -1) {

                    changeVariableValue(index);

                }
                else {
                    JOptionPane.showMessageDialog(null,
                                                  Constants.SELECT_A_VARIABLE_MESSAGE);
                }
            }

            private void changeVariableValue (int index) {
                InputDisplayData selected = (InputDisplayData) myListModel.get(index);
                String newValue = myTextfield.getText();
                myTextfield.setText("");

                (myController).updateVariable(selected
                        .getDisplay(), newValue);
            }

        });

        return myEdit;
    }

}
