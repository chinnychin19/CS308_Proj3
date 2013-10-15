package view.sidebar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import view.input.Textbox;
import model.Model;


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
    protected Collection<ModuleData> getStoredModelInformation () {
        Collection<ModuleData> commandCollection = new ArrayList<ModuleData>();
        Map<String, String> commandMap = Model.getAllCommands();
        for (String key : commandMap.keySet()) {
            commandCollection.add(new ModuleData(key, commandMap.get(key)));
        }
        return commandCollection;
    }

    @Override
    protected String getModuleName () {
        return MODULE_NAME;
    }

}
