package view.inputPanel;

import view.ViewController;


@SuppressWarnings("serial")
public class RedoButton extends InputButtons {

    protected RedoButton (ViewController controller) {
        super("REDO", controller);

    }

    @Override
    protected void mouseClickAction () {
        myController.redo();

    }

}
