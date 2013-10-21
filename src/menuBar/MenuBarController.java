package menuBar;

import java.util.Collection;
import model.Model;
import view.Controller;
import view.MasterSubject;
import view.Subject;


public class MenuBarController extends Controller {
    private MasterSubject mySubject;

    public MenuBarController (MasterSubject subject, Model model) {
        super(subject, model);
        mySubject = subject;

    }

    public void changeLanguage (String myLanguageValue) {
        myCurrentModel.setLanguage(myLanguageValue);

    }

    public Collection<String> getLanguages () {
        return myCurrentModel.getAvailableLanguages();
    }
    
    protected void loadFile(String fileName){
        myCurrentModel.readLibrary(fileName);
        mySubject.notifyObservers("");
    }
    
    protected void saveFile(String fileName){
        myCurrentModel.saveLibrary(fileName);
    }

}
