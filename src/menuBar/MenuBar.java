package menuBar;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Collection;
import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import view.ViewController;
import view.workspace.WorkSpacePreferences;


public class MenuBar extends JMenuBar {
    private ViewController myController;

    public MenuBar (ViewController controller) {
        myController = controller;
        add(fileMenu());
        add(helpMenu());

    }

    private JMenu fileMenu () {
        JMenu result = new JMenu("File");

        result.add(new AbstractAction("Open") {
            @Override
            public void actionPerformed (ActionEvent e) {

            }
        });

        result.add(new AbstractAction("Save") {
            @Override
            public void actionPerformed (ActionEvent e) {
                // TODO Auto-generated method stub
            }
        });
        result.addSeparator();
        JMenu submenu = new JMenu("Select Language");
        Collection<String> languages = myController.getLanguages();
        for (String language : languages) {
            submenu.add(new LanguageOption(language, myController));
        }

        result.add(submenu);

        return result;
    }

    private JMenu helpMenu () {
        JMenu result = new JMenu("Help");

        result.add(new AbstractAction("Part 2 Help") {
            @Override
            public void actionPerformed (ActionEvent e) {

                goToHelpPage("http://www.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands.php");

            }
        });

        result.add(new AbstractAction("Part 3 Help") {
            @Override
            public void actionPerformed (ActionEvent e) {
                goToHelpPage("http://www.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands2.php");

            }
        });

        return result;
    }

    private void goToHelpPage (String helpPage) {

        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(helpPage));
        }
        catch (IOException e1) {

            e1.printStackTrace();
        }

    }
}
