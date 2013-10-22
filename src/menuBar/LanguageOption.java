package menuBar;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import view.ViewController;


@SuppressWarnings("serial")
public class LanguageOption extends AbstractAction {
    private String myLanguageValue;
    private ViewController myController;

    protected LanguageOption (String value, ViewController controller) {
        super(value);
        myLanguageValue = value;
        myController = myController;
    }

    @Override
    public void actionPerformed (ActionEvent e) {

        myController.changeLanguage(myLanguageValue);

    }

}
