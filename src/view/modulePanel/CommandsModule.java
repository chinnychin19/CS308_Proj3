package view.modulePanel;

import view.inputPanel.Textbox;


@SuppressWarnings("serial")
public class CommandsModule extends Module {

    private static final String MODULE_NAME = "Commands Module";

    protected CommandsModule (int width, int height, Textbox textbox) {
        super(width, height, textbox);

    }

    protected CommandsModule (Textbox textbox) {
        super(textbox);

    }

    @Override
    protected String getModuleName () {
        return MODULE_NAME;
    }

}
