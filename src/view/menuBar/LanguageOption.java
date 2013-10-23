package view.menuBar;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import view.ViewController;


/**
 * An AbstractAction extension that
 * responds to an action by changing the language that is defined
 * for a particular instance. Each intance is defined by a language.
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 * 
 */
@SuppressWarnings("serial")
class LanguageOption extends AbstractAction {
    private String myLanguageValue;
    private ViewController myController;

    /**
     * @param value The language this class will represent
     * @param controller The controller the class will use to send language choice to Model
     */
    protected LanguageOption (String value, ViewController controller) {
        super(value);
        myLanguageValue = value;
        myController = controller;
    }

    @Override
    /**
     *sends myLanguageValue to Model as Language Preference
     */
    public void actionPerformed (ActionEvent e) {

        myController.changeLanguage(myLanguageValue);

    }

}
