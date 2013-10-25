package view.menuBar.workspace;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import view.Constants;
import view.ViewController;


/**
 * Class that reads in workspace preference files
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 * 
 */
public class WorkSpacePreferenceReader {
    private ViewController myController;

    public WorkSpacePreferenceReader (ViewController controller) {
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

            if (s[0].equals(Constants.BACKGROUND_KEYWORD) && s.length == Constants.COLOR_LINE_LENGTH) {
                myController.setBGColor(Integer.parseInt(s[1]));
                setPalette(s[1], s[2], s[3], s[4]);
            }
            if (s[0].equals(Constants.PEN_KEYWORD) && s.length == Constants.COLOR_LINE_LENGTH) {
                myController.setPenColor(Integer.parseInt(s[1]));
                setPalette(s[1], s[2], s[3], s[4]);
            }
            if (s[0].equals(Constants.IMAGE_KEYWORD) && s[1] != null) {
                myController.changeImage(Integer.parseInt(s[1]));
            }

        }
        br.close();

    }
    
    private void setPalette(String index, String r, String g, String b){
        myController.setPalette(Integer.parseInt(index), Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b));
    }

}
