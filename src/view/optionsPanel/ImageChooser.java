package view.optionsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import view.View;
import view.display.Canvas;

public class ImageChooser extends JComboBox{
    private Canvas myCanvas;
    private static String[] turtleOptions = { "Turtle1.gif", "Turtle2.gif", "Turtle3.gif" };
    
    public ImageChooser (View view) {    
        super(turtleOptions);
        
        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed (ActionEvent e) {
                JComboBox<?> cb = (JComboBox) e.getSource();
                String turtleSelection = (String) cb.getSelectedItem();
                myCanvas.changeTurtleImage(turtleSelection);
            }

        });
    }

}
