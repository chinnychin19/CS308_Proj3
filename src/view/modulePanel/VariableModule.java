package view.modulePanel;

import view.ViewController;


@SuppressWarnings("serial")
public class VariableModule extends Module {

    private EditBar myEditBar;

    public VariableModule (String name, ViewController controller) {
        super(name, controller);
        myEditBar = new EditBar(myList, myListModel, controller);
        this.add(myEditBar);
    }

}
