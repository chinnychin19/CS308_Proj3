package view.inputPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

/**Buttons specific to inputPanel
 * Uses InputController to execute button actions
 * @author lalitamaraj
 *
 */
@SuppressWarnings("serial")
public abstract class InputButtons extends JButton {

    protected InputController myController;

    protected InputButtons(String name, InputController controller){
        
        super(name);
        myController = controller;

        addButtonMouseListener();
    }

    /**
     * Method to add a mouse listener to button
     */
    private void addButtonMouseListener () {
        addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked (MouseEvent e) {
                mouseClickAction();
                
            }

            @Override
            public void mousePressed (MouseEvent e) {
                
                
            }

            @Override
            public void mouseReleased (MouseEvent e) {
              
                
            }

            @Override
            public void mouseEntered (MouseEvent e) {
           
                
            }

            @Override
            public void mouseExited (MouseEvent e) {
               
                
            }
            
        });
    }

    /**
     * Specific action that happens when a user clicks on a button
     * Each subclass defines this for itself
     */
    protected abstract void mouseClickAction ();
    
}