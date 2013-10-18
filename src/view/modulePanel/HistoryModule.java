package view.modulePanel;

import java.util.ArrayList;
import java.util.Collection;
import view.inputPanel.Textbox;
import model.Model;


@SuppressWarnings("serial")
public class HistoryModule extends Module {

    private static final String MODULE_NAME = "History Module";

    protected HistoryModule (int width, int height, Textbox textbox) {
        super(width, height, textbox);

    }

    protected HistoryModule (Textbox textbox) {
        super(textbox);

    }

    @Override
    protected String getModuleName () {
        return MODULE_NAME;
    }

}
