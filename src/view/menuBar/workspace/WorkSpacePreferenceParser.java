package view.menuBar.workspace;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import view.Constants;
import view.ViewController;


public class WorkSpacePreferenceParser {
    private ViewController myController;

    public WorkSpacePreferenceParser (ViewController controller) {
        myController = controller;
    }

    public void loadPreferences (File prefFile) throws IOException {

            BufferedReader br = new BufferedReader(new FileReader(prefFile));
            String sCurrentLine = br.readLine();

            if (!sCurrentLine.equals("preferences")) {
                JOptionPane.showMessageDialog(null, Constants.WRONG_PREF_FILE_MESSAGE);
            }

            while ((sCurrentLine = br.readLine()) != null) {
                String[] s = sCurrentLine.split(" ");

                if (s[0].equals("background") && s[1]!=null) {    
                   myController.setBGColor(Integer.parseInt(s[1]));
                }
                if (s[0].equals("penIndex") && s[1]!=null) {
                    myController.setPenColor(Integer.parseInt(s[1]));
                }
                if (s[0].equals("turtleImage") && s[1]!=null) {
                    myController.changeImage(Integer.parseInt(s[1]));
                }

            }
            br.close();
       

    }


}
