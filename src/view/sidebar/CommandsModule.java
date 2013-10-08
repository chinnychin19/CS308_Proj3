package view.sidebar;


import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class CommandsModule extends Module {

    public CommandsModule (int width, int height) {
        super(width, height);
        this.add(new JLabel("Commands Module"));
    }
    public CommandsModule () {
        super();
        this.add(new JLabel("Commands Module"));
    }


    @Override
    protected List<ModuleData> initializeModuleContents () {

        return null;
    }

    public void mouseClicked (MouseEvent evt) {

    }

    @Override
    protected void click () {
        // TODO Auto-generated method stub

    }

}
