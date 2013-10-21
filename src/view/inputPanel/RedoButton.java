package view.inputPanel;

@SuppressWarnings("serial")
public class RedoButton extends InputButtons  {

    protected RedoButton (InputController controller) {
        super("REDO", controller);
       
    }


    @Override
    protected void mouseClickAction () {
        myController.redo();

    }

}