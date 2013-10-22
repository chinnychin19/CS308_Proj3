package view;

import model.Model;


public interface Updatable {
    public void update (String error);

    public void changeModel (Model model);
}
