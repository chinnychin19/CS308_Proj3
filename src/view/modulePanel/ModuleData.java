package view.modulePanel;

import javax.swing.JComponent;


@SuppressWarnings("serial")
public class ModuleData extends JComponent {
    String myDisplay;
    String myContent;

    public String toString () {
        return myDisplay;
    }

    public ModuleData (String displayString, String contentString) {
        myDisplay = displayString;
        myContent = contentString;

    }

    public String getDisplay () {
        String displayCopy = myDisplay;
        return displayCopy;

    }

    public String getContent () {
        String contentCopy = myContent;
        return contentCopy;
    }

    protected String setContent (String newContent) {
        myContent = newContent;
        return newContent;
    }
}
