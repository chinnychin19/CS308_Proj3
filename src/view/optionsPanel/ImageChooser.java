package view.optionsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import view.View;
import view.ViewController;
import view.display.Canvas;


public class ImageChooser extends JComboBox {
    private Canvas myCanvas;
    private static final String[] turtleOptions = { "Turtle1.gif", "Turtle2.gif", "Turtle3.gif" };

    @SuppressWarnings("unchecked")
    public ImageChooser (final ViewController controller) {
        super(turtleOptions);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                JComboBox<?> cb = (JComboBox<?>) e.getSource();
                String turtleSelection = (String) cb.getSelectedItem();
                System.out.println(turtleSelection);
                myCanvas.changeTurtleImage(turtleSelection);
            }

        });
    }

}
