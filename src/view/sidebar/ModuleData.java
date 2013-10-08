package view.sidebar;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;


@SuppressWarnings("serial")
public class ModuleData extends JComponent {
    String content;
    
   
    public String toString(){
        return content;
    }
    public ModuleData (String str) {
        content = str;

    }
}
