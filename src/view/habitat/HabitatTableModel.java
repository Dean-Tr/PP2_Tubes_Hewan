package view.habitat;

import javax.swing.table.*;
import java.util.List;
import model.Habitat;

public class HabitatTableModel extends AbstractTableModel {
    private String[] columnNames = { "Nama Habitat" };
    private List<Habitat> data;

    public HabitatTableModel(List<Habitat> data) {
        this.data = data;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        Habitat rowItem = data.get(row);
        String value = "";
        switch (col) {
            case 0:
                value = rowItem.getNamaHabitat();
                break;
            case 1:
                value = rowItem.getId();
                break;
        }
        return value;
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(Habitat value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void remove(int value) {
        data.remove(value);
        fireTableRowsDeleted(data.size() - 1, data.size() - 1);
    }
}
