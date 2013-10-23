package view.menuBar;
import java.util.Collection;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import view.Constants;
import view.ViewController;


/**
 * @author Lalita Maraj
 * @author Susan Zhang
 *The MenuBar class extends JMenuBar an Swing component
 *It has submenus specific to the Slogo game
 */
@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {

    private ViewController myController;

    /**Constructor 
     * @param controller used to send information to the Model
     */
    public MenuBar (ViewController controller) {
        myController = controller;
        add(fileMenu());
        add(addHelpMenu());

    }

    /** Creates the file menu
     * @return the file menu
     */
    private JMenu fileMenu () {
        JMenu menu = new JMenu(Constants.FILE_MENU_NAME);
        menu.add(new OpenMenuItem(myController,Constants.OPEN_MENU_NAME) );
        menu.add(new SaveMenuItem(Constants.SAVE_MENU_NAME,myController ));
        menu.addSeparator();
        JMenu submenu = addLanguageMenu();
        menu.add(submenu);

        return menu;
    }

    /**Creates the menu to allow users to change language
     * @return a Language Menu
     */
    private JMenu addLanguageMenu () {
        JMenu submenu = new JMenu("Select Language");
        Collection<String> languages = myController.getLanguages();
        for (String language : languages) {
            submenu.add(new LanguageOption(language, myController));
        }
        return submenu;
    }

    /**Creates the help menu
     * @return a help menu
     */
    private JMenu addHelpMenu () {
        JMenu menu = new JMenu(Constants.HELP_MENU_NAME);
        menu.add(new HelpMenuItem(Constants.HELP_MENU_2, Constants.HELP_URL_PART_2));
        menu.add(new HelpMenuItem(Constants.HELP_MENU_3, Constants.HELP_URL_PART_3));

        return menu;
    }

}
