package menuBar;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;


public class MenuBar extends JMenuBar {
    public MenuBar () {
        add(fileMenu());
        add(helpMenu());
    }

    private JMenu fileMenu () {
        JMenu result = new JMenu("File");

        result.add(new AbstractAction("Open") {
            @Override
            public void actionPerformed (ActionEvent e) {
                // TODO Auto-generated method stub
            }
        });

        result.add(new AbstractAction("Save") {
            @Override
            public void actionPerformed (ActionEvent e) {
                // TODO Auto-generated method stub
            }
        });
        result.addSeparator();
        // TO DO FIND A WAY TO DYNAMICALLY ADD LANGUAGES
        // /WANT TO AVOID HARDCODING
        JMenu submenu = new JMenu("A submenu");
        submenu.add(new LanguageOption("English"));
        submenu.add(new LanguageOption("French"));
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
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }
}
