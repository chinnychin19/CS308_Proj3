package view.sidebar;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import model.Model;
import view.input.Textbox;


@SuppressWarnings("serial")
public class VariableModule extends Module {
    JButton edit;
    TextField textfield;
    public VariableModule (int width, int height, Textbox textbox) {
        super(width, height, textbox);
        this.add(new JLabel("Variable Module"));
        edit =new JButton("Edit");
        edit.addActionListener(new EditListener());
        this.add(edit);
        textfield = new TextField();
        
    }

    public VariableModule (Textbox textbox) {
        super(textbox);
        this.add(new JLabel("Variable Module"));
        
        edit =new JButton("Edit");
        edit.addActionListener(new EditListener());
        this.add(edit);
        textfield = new TextField();
    }
    class EditListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            int index = list.getSelectedIndex();
            Object o = listModel.get(index);
            listModel.remove(index);
           
            listModel.add(index, new ModuleData("updated","updatedvalue"));
            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable firing.
//                fireButton.setEnabled(false);

            } else { //Select an index.
               

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }
    @Override
    protected Collection<ModuleData> getStoredModelInformation () {
        Collection<ModuleData> variableCollection = new ArrayList<ModuleData>();
        Map<String, String> variableMap = Model.getAllVariables();
        variableMap.put("display", "value");
        for (String key : variableMap.keySet()) {
            variableCollection.add(new ModuleData(key, variableMap.get(key)));
        }
        return variableCollection;
    }

}
