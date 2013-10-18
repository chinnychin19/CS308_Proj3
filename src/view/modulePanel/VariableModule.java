package view.modulePanel;

import view.inputPanel.Textbox;


@SuppressWarnings("serial")
public class VariableModule extends Module {
    private static final String MODULE_NAME = "Variable Module";
    private EditBar myEditBar;

    public VariableModule (int width, int height, Textbox textbox) {
        super(width, height, textbox);
        myEditBar = new EditBar(myList, myListModel);
        this.add(myEditBar);

    }

    public VariableModule (Textbox textbox) {
        super(textbox);
        myEditBar = new EditBar(myList, myListModel);
        this.add(myEditBar);
    }

    @Override
    public String getModuleName () {
        return MODULE_NAME;
    }

}
