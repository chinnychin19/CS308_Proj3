package view.optionsPanel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import view.View;
import view.display.Canvas;


public class StatusCheckBox extends JCheckBox {
    private Canvas myCanvas;

    public StatusCheckBox (View view, Canvas canvas) {
        super("Turtle Status", null, true);
        myCanvas = canvas;

        addItemListener(new ItemListener() {

            public void itemStateChanged (ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    myCanvas.toggleStatus();
                }
                else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    myCanvas.toggleStatus();
                }
            }

        });

    }

}
