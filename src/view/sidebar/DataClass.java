package view.sidebar;

import javax.swing.table.AbstractTableModel;


class DataClass extends AbstractTableModel {
    Object[][] data = { { "one", "two", "three", "four" },
                       { "five", "six", "seven", "eight" },
                       { "nine", "ten", "eleven", "twelve" },
                       { "nine", "ten", "eleven", "twelve" },
                       { "nine", "ten", "eleven", "twelve" },
                       { "nine", "ten", "eleven", "twelve" },
                       { "nine", "ten", "eleven", "twelve" },
                       { "nine", "ten", "eleven", "twelve" }
    };

    @Override
    public int getRowCount () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getColumnCount () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Object getValueAt (int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        return null;
    }

};
