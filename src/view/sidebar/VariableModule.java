package view.sidebar;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.Constants;
import view.input.Textbox;


@SuppressWarnings("serial")
public class VariableModule extends JPanel implements ContentContainer {

    public VariableModule (int width, int height, Textbox textbox) {
        this.setPreferredSize(new Dimension(width, height));
        this.add(new JLabel("Variable Module"));

    }

    public VariableModule (Textbox textbox) {
        this.setPreferredSize(new Dimension(Constants.DISPLAY_WIDTH, Constants.DISPLAY_HEIGHT));
        this.add(new JLabel("Variable Module"));
    }

    @Override
    public void updateContent () {
        // TODO Auto-generated method stub

    }

}
