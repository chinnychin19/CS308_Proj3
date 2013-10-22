package view.inputPanel;

import view.ViewController;


@SuppressWarnings("serial")
public class UndoButton extends InputButtons {

    protected UndoButton (ViewController myController) {
        super("UNDO", myController);

    }

    @Override
    protected void mouseClickAction () {
        myController.undo();

    }

}
