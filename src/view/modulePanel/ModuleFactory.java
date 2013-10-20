package view.modulePanel;

import view.Controller;
import view.inputPanel.Textbox;


public class ModuleFactory {
    public static Module createModule (String module, Textbox textbox, Controller controller) {
        if (module.equals("history")) { return new Module("History", controller);

        }
        if (module.equals("command")) { return new Module("Commands", controller);

        }
        if (module.equals("variable")) { return new VariableModule("Variables", controller);

        }
        return null;

    }
}
