package model;

import java.util.ArrayList;
import java.util.List;


public class CommandHistory {
    private List<String> myHistory;

    protected CommandHistory (Model m) {
        myHistory = new ArrayList<String>();
    }

    protected void add (String command) {
        myHistory.add(command);
    }

    protected List<String> getHistory () {
        return new ArrayList<String>(myHistory);
    }

    protected void clear () {
        myHistory.clear();
    }
}
