package view.menuBar;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.AbstractAction;


/**
 * Menu bar specific to Help menus
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 */
@SuppressWarnings("serial")
public class HelpMenuItem extends AbstractAction {
    private String myURLHelpPage;

    /**
     * Constructor
     * 
     * @param name Name of menu
     * @param urlHelpPage page to be taken to when menu selected
     * 
     */
    public HelpMenuItem (String name, String urlHelpPage) {
        super(name);
        myURLHelpPage = urlHelpPage;
    }

    @Override
    /**Initiates an action when menu is selected
     */
    public void actionPerformed (ActionEvent e) {
        goToHelpPage(myURLHelpPage);

    }

    /**
     * Takes user to a browser loaded with the given url
     * 
     * @param helpPage the url of the help page
     */
    private void goToHelpPage (String helpPage) {

        try {
            Desktop.getDesktop().browse(java.net.URI.create(helpPage));
        }
        catch (IOException e1) {

            e1.printStackTrace();
        }

    }

}
