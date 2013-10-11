package view.sidebar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.swing.JLabel;
import view.input.Textbox;
import model.Model;


@SuppressWarnings("serial")
public class CommandsModule extends Module {

    public CommandsModule (int width, int height, Textbox textbox) {
        super(width, height, textbox);

    }

    public CommandsModule (Textbox textbox) {
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

        return "Commands Module";
    }

}
