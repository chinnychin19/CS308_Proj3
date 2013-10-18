package view.modulePanel;

import javax.swing.JButton;
import model.Model;
import view.Controller;
import view.Subject;

public class ModulePanelController extends Controller {
    private Model myCurrentModel;
    private Subject mySubject;
    private Module myVariableModule;
    
    public ModulePanelController(Module variableModule,Subject subject, Model model){
        myVariableModule =variableModule;
        mySubject = subject;
        myCurrentModel = model;
       
    }

}
