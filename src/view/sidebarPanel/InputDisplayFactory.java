package view.sidebarPanel;

import view.ViewController;


/**
 * Factory to create different InputDisplays
 * 
 * @author lalitamaraj
 * 
 */
class InputDisplayFactory {
    /**
     * method called to create an InputDisplay
     * 
     * @param inputDisplayName name used to identify which InputDisplay should be created
     * @param controller controller used to communicate with model
     * @return
     */
    protected static InputDisplay createInputDisplays (String inputDisplayName,
                                                       ViewController controller) {
        if (inputDisplayName.equals("history")) { return new InputDisplay("History", controller);

        }
        if (inputDisplayName.equals("command")) { return new InputDisplay("Commands", controller);

        }
        if (inputDisplayName.equals("variable")) { return new VariableInputDisplay("Variables",
                                                                                   controller);

        }
        return null;

    }
}
