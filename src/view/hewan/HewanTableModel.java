package view.hewan;

import javax.swing.table.*;
import java.util.List;
import model.Hewan;

public class HewanTableModel extends AbstractTableModel {
    private String[] columnNames = { "Nama", "Umur", "Jenis Kelamin", "Jenis Hewan", "Habitat" };
    private List<Hewan> data;

    public HewanTableModel(List<Hewan> data) {
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
        Hewan rowItem = data.get(row);
        String value = "";
        switch (col) {
            case 0:
                value = rowItem.getNama();
                break;
            case 1:
                value = rowItem.getUmur();
                break;
            case 2:
                value = rowItem.getJenisKelamin();
                break;
            case 3:
                value = rowItem.getJenisHewan().getJenis();
                break;
            case 4:
                value = rowItem.getHabitat().getNamaHabitat();
                break;
            case 5:
                value = rowItem.getId();
                break;
        }
        return value;
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(Hewan value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void remove(int value) {
        data.remove(value);
        fireTableRowsDeleted(data.size() - 1, data.size() - 1);
    }
}
