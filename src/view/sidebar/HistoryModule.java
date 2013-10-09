package view.sidebar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JLabel;
import view.input.Textbox;
import model.Model;


@SuppressWarnings("serial")
public class HistoryModule extends Module {
    private int JListindex = 0;

    public HistoryModule (int width, int height, Textbox textbox) {
        super(width, height, textbox);
        this.add(new JLabel("History Module"));
        // TODO Auto-generated constructor stub
    }

    public HistoryModule (Textbox textbox) {
        super(textbox);
        this.add(new JLabel("History Module"));
    }

    @Override
    protected Collection<ModuleData> getStoredModelInformation () {
        Collection<ModuleData> historyCollection = new ArrayList<ModuleData>();
        for (String history : Model.getHistory()) {
            historyCollection.add(new ModuleData(history, history));
        }
        return historyCollection;

    }

}
