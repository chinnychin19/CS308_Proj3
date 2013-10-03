package model;

import java.util.ArrayList;
import java.util.List;


public class CommandHistory {
    private List<String> myHistory;

    protected CommandHistory () {
        myHistory = new ArrayList<String>();
    }

    protected void add (String command) {
        myHistory.add(command);
    }

    public List<String> getHistory () {
        return new ArrayList<String>(myHistory);
    }

    public void clear () {
        myHistory.clear();
    }
}
