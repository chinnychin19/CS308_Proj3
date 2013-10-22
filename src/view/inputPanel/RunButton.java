package view.inputPanel;

import view.ViewController;


@SuppressWarnings("serial")
public class RunButton extends InputButtons {

    protected RunButton (ViewController myController) {
        super("RUN", myController);

    }

    @Override
    protected void mouseClickAction () {
        myController.executeCommand();

    }

}
