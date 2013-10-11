package view.sidebar;

import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Model;
import view.input.Textbox;


@SuppressWarnings("serial")
public class VariableModule extends Module {
    JButton edit;
    TextField textfield;

    public VariableModule (int width, int height, Textbox textbox) {
        super(width, height, textbox);
        initialize();

    }

    public VariableModule (Textbox textbox) {
        super(textbox);
        initialize();

    }

    private void initialize () {

        JPanel bottomPane = new JPanel();
        bottomPane.add(addTextBox());
        bottomPane.add(addEditButton());
        this.add(bottomPane, BorderLayout.NORTH);

    }

    private JButton addEditButton () {
        edit = new JButton("Edit");
        edit.addActionListener(new EditListener());
        return edit;

    }

    private TextField addTextBox () {
        textfield = new TextField();
        textfield.setColumns(6);
        return textfield;
    }

    class EditListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {

            int index = list.getSelectedIndex();
            if (index != -1) {

                ModuleData selected = (ModuleData) listModel.get(index);
                String newValue = textfield.getText();
                String putStatus = Model.putVariable(selected.getDisplay(), newValue);
                updateVariable(index, selected, putStatus);
            }
        }

        private void displayInputError (String putStatus) {
            // TODO Auto-generated method stub

        }

        @SuppressWarnings("unchecked")
        private void updateVariable (int index, ModuleData selected, String putStatus) {
            if (putStatus.equals("")) {
                selected.setContent(textfield.getText());
                listModel.remove(index);
                listModel.add(index, selected);
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
            else {
                displayInputError(putStatus);
            }

        }
    }

    @Override
    protected Collection<ModuleData> getStoredModelInformation () {
        Collection<ModuleData> variableCollection = new ArrayList<ModuleData>();
        Map<String, String> variableMap = Model.getAllVariables();
        for (String key : variableMap.keySet()) {
            variableCollection.add(new ModuleData(key, variableMap.get(key)));
        }
        return variableCollection;
    }

    @Override
    protected String getModuleName () {
        return "Variable Module";
    }

}
