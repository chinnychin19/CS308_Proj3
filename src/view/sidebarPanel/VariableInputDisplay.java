package view.sidebarPanel;

import view.ViewController;


@SuppressWarnings("serial")
/**A subclass of the InputDisplay class that is specific for variables.
 * Thsi specificity is needed to allow variables to be manipulated
 * */
class VariableInputDisplay extends InputDisplay {

    private EditBar myEditBar;

    /**
     * @param name name to be displayed
     * @param controller controller used to send information to Model
     */
    protected VariableInputDisplay (String displayName, ViewController controller) {
        super(displayName, controller);
        myEditBar = new EditBar(myList, myListModel, controller);
        this.add(myEditBar);
    }

}
