package view.sidebar;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JLabel;


public class HistoryModule extends Module {

    public HistoryModule (int width, int height) {
        super(width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.add(new JLabel("I have a number between 1 and 1000."));
        // TODO Auto-generated constructor stub
    }

    @Override
    protected List<ModuleData> initializeModuleContents () {
        // getHistory();
        return null;
    }

    @Override
    protected void click () {
        // TODO Auto-generated method stub

    }

}
