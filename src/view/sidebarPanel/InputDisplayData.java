package view.sidebarPanel;

import javax.swing.JComponent;


/**
 * Used by InputDisplay classes to encapsulate
 * the data and content of the input that is displayed
 * by InputDisplays
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 * 
 */
@SuppressWarnings("serial")
class InputDisplayData extends JComponent {
    private String myDisplay;
    private String myContent;

    public String toString () {
        return myDisplay;
    }

    protected InputDisplayData (String displayString, String contentString) {
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
