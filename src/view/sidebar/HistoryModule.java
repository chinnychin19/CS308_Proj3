package view.sidebar;

import java.util.Collection;
import java.util.List;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class HistoryModule extends Module {

    public HistoryModule (int width, int height) {
        super(width, height);
        this.add(new JLabel("History Module"));
        // TODO Auto-generated constructor stub
    }

    public HistoryModule () {
        super();
        this.add(new JLabel("History Module"));
        // TODO Auto-generated constructor stub
    }

    @Override
    protected List<ModuleData> initializeModuleContents () {

        return null;
    }

    @Override
    protected void click () {
        // TODO Auto-generated method stub

    }

    @Override
    protected void updatePanel () {
        // TODO Auto-generated method stub

    }

    @Override
    protected Collection<ModuleData> getStoredModelInformation () {
        // TODO Auto-generated method stub
        return null;
    }

}
