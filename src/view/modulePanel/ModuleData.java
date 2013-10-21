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

    protected String getDisplay () {
        String displayCopy = myDisplay;
        return displayCopy;

    }

    protected String getContent () {
        String contentCopy = myContent;
        return contentCopy;
    }

    protected String setContent (String newContent) {
        myContent = newContent;
        return newContent;
    }
}
