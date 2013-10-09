package view.sidebar;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import view.input.Textbox;
import model.Model;


@SuppressWarnings("serial")
public class CommandsModule extends Module {

    public CommandsModule (int width, int height, Textbox textbox) {
        super(width, height, textbox);
        this.add(new JLabel("Commands Module"));
    }

    public CommandsModule (Textbox textbox) {
        super(textbox);
        this.add(new JLabel("Commands Module"));
    }

    public void mouseClicked (MouseEvent evt) {

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

}
