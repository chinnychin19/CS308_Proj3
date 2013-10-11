package view.sidebar;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Collection;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.input.Textbox;


/**
 * @author susanzhang93
 * 
 */
@SuppressWarnings("serial")
public abstract class Module extends JPanel implements ContentContainer {
    private static final int DISPLAY_HEIGHT = 100;
    private static final int DISPLAY_WIDTH = 300;
    private JList list;
    private DefaultListModel listModel;
    private Textbox textbox;

    public Module (Textbox textbox) {

        super();
        this.textbox = textbox;
        setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        initializeModuleDisplay();
    }

    public Module (int width, int height, Textbox textbox) {

        super();
        this.textbox = textbox;
        setPreferredSize(new Dimension(width, height));
        initializeModuleDisplay();

    }

    @Override
    public void updateContent () {
        Collection<ModuleData> listData = getStoredModelInformation();

        listModel.clear();

        for (ModuleData moduleData : listData) {
            listModel.addElement(moduleData);

        }
    }

    protected void initializeModuleContents () {
        //
        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.addListSelectionListener(new ValueReporter());
        JScrollPane listScrollPane = new JScrollPane(list);
        add(listScrollPane);

    }

    private void initializeModuleDisplay () {
        setBackground(Color.white);
        initializeModuleContents();
    }

    protected abstract Collection<ModuleData> getStoredModelInformation ();

    private class ValueReporter implements ListSelectionListener {

        @Override
        public void valueChanged (ListSelectionEvent event) {
            if (!event.getValueIsAdjusting() && list.getSelectedValue() != null) {

                ModuleData md = (ModuleData) list.getSelectedValue();
                textbox.setText(md.content);

            }

        }
    }
}
