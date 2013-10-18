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

    private Model myCurrentModel;
    private Subject mySubject;

    

    
    public String updateVariable(String key, String value){
        mySubject.update();
        String updateVariable = myCurrentModel.putVariable(key,value);
        return updateVariable;
        
    }


}
