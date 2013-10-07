package view.sidebar;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JLabel;


public class HistoryModule extends Module {

    public HistoryModule (int width, int height) {
        super(width, height);
        this.setPreferredSize(new Dimension(width, height));
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

}
