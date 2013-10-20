package view.workspace;

import java.util.ArrayList;
import java.util.List;
import model.Model;
import view.Controller;
import view.MasterSubject;


public class WorkSpaceSelectorController extends Controller {

    private List<Controller> myControllers;
    private List<MasterSubject> mySubjects;
    private List<Model> myModels = new ArrayList<Model>();

    public WorkSpaceSelectorController (MasterSubject subject, List<Controller> controllers,
                                        List<MasterSubject> subjects,
                                        Model model) {
        super(subject, model);
        myControllers = controllers;
        myControllers.add(this);
        mySubjects = subjects;
        myModels.add(model);

    }

    protected void selectWorkSpace (String choice) {
        //
    }

    protected int getModelsSize () {
        return myModels.size();
    }
}
