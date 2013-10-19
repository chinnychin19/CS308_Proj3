package menuBar;

import model.Model;
import view.Controller;
import view.MasterSubject;


public class MenuBarController extends Controller {

    public MenuBarController (MasterSubject subject, Model model) {
        super(subject, model);

    }

    public void changeLanguage (String myLanguageValue) {
        myCurrentModel.setLanguage(myLanguageValue);

    }

}
