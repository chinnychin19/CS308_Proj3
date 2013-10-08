package view.sidebar;


import java.util.List;
import javax.swing.JLabel;



@SuppressWarnings("serial")
public class VariableModule extends Module {

    public VariableModule (int width, int height) {
        super(width, height);
        this.add(new JLabel("Variable Module"));
        // TODO Auto-generated constructor stub
    }

    public VariableModule () {
        super();
        this.add(new JLabel("Variable Module"));
        // TODO Auto-generated constructor stub
    }

    @Override
    protected List<ModuleData> initializeModuleContents () {
        // getVariableKeyPairs();
        return null;
    }

    @Override
    protected void click () {
        // TODO Auto-generated method stub

    }

}
