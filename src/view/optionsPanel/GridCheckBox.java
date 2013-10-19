package view.optionsPanel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import view.View;
import view.display.Canvas;


public class GridCheckBox extends JCheckBox {
    private Canvas myCanvas;

    public GridCheckBox (View view, Canvas canvas) {
        super("Grid", null, false);
        myCanvas = canvas;
        addItemListener(new ItemListener() {

            public void itemStateChanged (ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    myCanvas.toggleGrid();
                }
                else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    myCanvas.toggleGrid();
                }
            }

        });
    }

}
