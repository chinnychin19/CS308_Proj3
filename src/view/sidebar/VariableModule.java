package view.sidebar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import model.Model;
import view.Constants;
import view.input.Textbox;


@SuppressWarnings("serial")
public class VariableModule extends JPanel implements ContentContainer, ActionListener {

    private JTable table;
    private Textbox textbox;

    public VariableModule (int width, int height, Textbox textbox) {
        this.textbox = textbox;
        this.setPreferredSize(new Dimension(width, height));
        this.add(new JLabel("Variable Module"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.getSelectionModel().addListSelectionListener(new RowListener());
        table.getColumnModel().getSelectionModel().
                addListSelectionListener(new ColumnListener());
        add(new JScrollPane(table));
        add(new JLabel("Selection Options"));

    }

    public VariableModule (Textbox textbox) {
        this.textbox = textbox;
        this.setPreferredSize(new Dimension(Constants.DISPLAY_WIDTH, Constants.DISPLAY_HEIGHT));
        this.add(new JLabel("Variable Module"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.getSelectionModel().addListSelectionListener(new RowListener());
        table.getColumnModel().getSelectionModel().
                addListSelectionListener(new ColumnListener());
        add(new JScrollPane(table));
        add(new JLabel("Selection Options"));

    }

    public void actionPerformed (ActionEvent event) {

    }

    private void outputSelection () {

        textbox.addInput((String) table.getValueAt(table.getSelectedRows()[0], 0));

    }

    private class RowListener implements ListSelectionListener {
        public void valueChanged (ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) { return; }

            outputSelection();
        }
    }

    private class ColumnListener implements ListSelectionListener {
        public void valueChanged (ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) { return; }

            outputSelection();
        }
    }

    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = { "Variable",
                                        "Value"
        };
        private Object[][] data = {
                                   { "Kathy", "Smith" },
                                   { "John", "Black" }
                // {"John", "Doe",
                // "Rowing", new Integer(3), new Boolean(true)},
                // {"Sue", "Black",
                // "Knitting", new Integer(2), new Boolean(false)},
                // {"Jane", "White",
                // "Speed reading", new Integer(20), new Boolean(true)},
                // {"Joe", "Brown",
                // "Pool", new Integer(10), new Boolean(false)}
        };

        public int getColumnCount () {
            return columnNames.length;
        }

        public int getRowCount () {
            return data.length;
        }

        public String getColumnName (int col) {
            return columnNames[col];
        }

        public Object getValueAt (int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell. If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass (int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable (int row, int col) {
            // Note that the data/cell address is constant,
            // no matter where the cell appears onscreen.
            if (col == 0) {
                return false;
            }
            else {
                return true;
            }
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt (Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }

    }

    @Override
    public void updateContent () {

        Map<String, String> variableMap = Model.getAllVariables();
        Object[][] variables = new Object[variableMap.keySet().size()][2];
        int i = 0;
        for (String key : variableMap.keySet()) {
            Object[] entry = new Object[2];
            entry[0] = key;
            entry[1] = variableMap.get(key);
            variables[i] = entry;
        }

    }
}
