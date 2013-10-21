package view.inputPanel;

public interface InputObserver {

    /**Passes booleans to notify whether actions can be performed
     * @param canUndo indicates whether undo is currently doable
     * @param canRedo indicates whether redo is currently doable
     */
    void update (Boolean canUndo, Boolean canRedo);

}
