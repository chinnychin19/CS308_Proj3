package view.modulePanel;

import javax.swing.JButton;
import model.Model;
import view.Controller;
import view.Subject;

public class ModulePanelController extends Controller {
    public ModulePanelController (Subject subject, Model model) {
        super(subject, model);
        // TODO Auto-generated constructor stub
    }

    

    
    public String updateVariable(String key, String value){
        
        String updateVariable = myCurrentModel.putVariable(key,value);
        mySubject.notifyObservers("",updateVariable);
        return updateVariable;
        
    }


}
