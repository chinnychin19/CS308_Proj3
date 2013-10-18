package view.modulePanel;

import view.Controller;
import view.inputPanel.Textbox;


@SuppressWarnings("serial")
public class VariableModule extends Module {

    private EditBar myEditBar;

    public VariableModule (Textbox textbox, String name, Controller controller) {
        super(textbox, name, controller);
        myEditBar = new EditBar(myList, myListModel,controller);
        this.add(myEditBar);
    }

}
