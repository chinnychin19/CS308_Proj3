package view.modulePanel;

import view.inputPanel.Textbox;


@SuppressWarnings("serial")
public class VariableModule extends Module {

    private EditBar myEditBar;

    public VariableModule (Textbox textbox, String name) {
        super(textbox, name);
        myEditBar = new EditBar(myList, myListModel);
        this.add(myEditBar);
    }

}
