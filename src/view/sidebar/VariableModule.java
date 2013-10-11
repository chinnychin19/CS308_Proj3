package view.sidebar;

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
    public VariableModule (int width, int height, Textbox textbox) {
        super(width, height, textbox);
        this.add(new JLabel("Variable Module"));
        edit =new JButton("Edit");
        edit.addActionListener(new EditListener());
        this.add(edit);
        
    }

    public VariableModule (Textbox textbox) {
        super(textbox);
        this.add(new JLabel("Variable Module"));
        
        edit =new JButton("Edit");
        edit.addActionListener(new EditListener());
        this.add(edit);
    }
    class EditListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            System.out.println("Edit clicked");
//            int index = list.getSelectedIndex();
//            Object o = listModel.get(index);
//            listModel.remove(index);
//            listModel.add(index, "hello");
//            int size = listModel.getSize();

//            if (size == 0) { //Nobody's left, disable firing.
//                fireButton.setEnabled(false);
//
//            } else { //Select an index.
//                if (index == listModel.getSize()) {
//                    //removed item in last position
//                    index--;
//                }
//
//                list.setSelectedIndex(index);
//                list.ensureIndexIsVisible(index);
//            }
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

}
