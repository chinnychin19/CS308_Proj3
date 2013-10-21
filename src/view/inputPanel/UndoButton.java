package view.inputPanel;

@SuppressWarnings("serial")
public class UndoButton extends InputButtons {
    
    protected UndoButton (InputController controller) {
        super("UNDO",controller);
        
        
    }

    @Override
    protected void mouseClickAction () {
        myController.undo();

    }

}