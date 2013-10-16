package view.modulePanel;

import view.inputPanel.Textbox;


public class ModuleFactory {
    public static Module createModule (String module, Textbox textbox) {
        if (module.equals("history")) { return new HistoryModule(textbox);

        }
        if (module.equals("command")) { return new CommandsModule(textbox);

        }
        if (module.equals("variable")) { return new VariableModule(textbox);

        }
        return null;

    }
}
