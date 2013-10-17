package view.sidebar;

import javax.swing.JComponent;


@SuppressWarnings("serial")
public class ModuleData extends JComponent {
    String display;
    String content;

    public String toString () {
        return display;
    }

    public ModuleData (String displayString, String contentString) {
        display = displayString;
        content = contentString;

    }

    public String getDisplay () {
        String displayCopy = display;
        return displayCopy;

    }

    public String getContent () {
        String contentCopy = content;
        return contentCopy;
    }

    protected String setContent (String newContent) {
        content = newContent;
        return newContent;
    }
}
