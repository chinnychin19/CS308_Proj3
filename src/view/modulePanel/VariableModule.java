package view.modulePanel;

import view.Controller;


@SuppressWarnings("serial")
public class VariableModule extends Module {

    private EditBar myEditBar;

    public VariableModule (String name, Controller controller) {
        super(name, controller);
        myEditBar = new EditBar(myList, myListModel, controller);
        this.add(myEditBar);
    }

}
