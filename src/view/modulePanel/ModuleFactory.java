package view.modulePanel;

import view.inputPanel.Textbox;


public class ModuleFactory {
    public static Module createModule (String module, Textbox textbox) {
        if (module.equals("history")) { return new Module(textbox, "History");

        }
        if (module.equals("command")) { return new Module(textbox, "Commands");

        }
        if (module.equals("variable")) { return new VariableModule(textbox, "Variables");

        }
        return null;

    }
}
