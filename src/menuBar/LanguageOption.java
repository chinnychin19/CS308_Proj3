package menuBar;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;


@SuppressWarnings("serial")
public class LanguageOption extends AbstractAction {
    private String myLanguageValue;

    protected LanguageOption (String value) {
        super(value);
        myLanguageValue = value;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        System.out.println(myLanguageValue);
        // Model.setLanguage(myLanguageValue);

    }

}
