package menuBar;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;


@SuppressWarnings("serial")
public class LanguageOption extends AbstractAction {
    private String myLanguageValue;
    private MenuBarController myController;

    protected LanguageOption (String value, MenuBarController controller) {
        super(value);
        myLanguageValue = value;
        myController = controller;
    }

    @Override
    public void actionPerformed (ActionEvent e) {

        myController.changeLanguage(myLanguageValue);

    }

}
