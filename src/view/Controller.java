package view;

import java.util.ArrayList;
import java.util.List;
import view.inputPanel.Textbox;
import model.Model;


class Controller {
    private List<Model> myModels;
    private Model currentModel;
    private View myView;
    private Textbox myTextbox;
    private ModuleController myModuleController;

    protected Controller (Model model, View view, Textbox textbox) {
        myTextbox = textbox;
        myView = view;
        myModels = new ArrayList<Model>();
        currentModel = model;
        myModels.add(currentModel);
        myModuleController = new ModuleController();
    }

    void selectModel (int modelNumber) {
        currentModel = myModels.get(modelNumber);
        myView.changeModel(currentModel);
        myView.changeWorkSpace();
        myView.update();

    }

    void setBackground (int colorIndex) {
        currentModel.setBGColor(colorIndex);
        myView.updateBGColor();
        myView.updateModulePanel(myModuleController.getModelInformation(currentModel));

    }

    void undo () {
        currentModel.undo();
        myView.update();
    }

    protected void executeCommand () {
        currentModel.parseInput(myTextbox.getText());
        myView.updateModulePanel(myModuleController.getModelInformation(currentModel));
        myView.updateCanvasPanel();
        myView.updateOptionsPanel();
    }

    void editVariable () {
        // TODO not sure how this will work
        myView.update();
    }

}
