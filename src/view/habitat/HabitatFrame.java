package view.habitat;

import model.Habitat;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public class HabitatFrame extends JFrame {
    private List<Habitat> habitatList;
    private final JTextField textFieldJenis;
    private HabitatTableModel tableModel;
    private final JTable table;
    private final JButton buttonSimpan;
    private final JButton buttonDelete;

    public HabitatFrame() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelInput = new JLabel("Nama Habitat:");
        labelInput.setBounds(15, 40, 350, 10);
        textFieldJenis = new JTextField();
        textFieldJenis.setBounds(15, 60, 350, 30);
        buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 100, 100, 40);
        buttonDelete = new JButton("Hapus");
        buttonDelete.setBounds(130, 100, 100, 40);
        table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 150, 350, 200);

        this.add(buttonSimpan);
        this.add(buttonDelete);
        this.add(textFieldJenis);
        this.add(labelInput);
        this.add(scrollableTable);

        this.setSize(400, 700);
        this.setLayout(null);
    }

    public String getNamaHabitat() {
        return textFieldJenis.getText();
    }

    public void addHabitat(Habitat habitat) {
        tableModel.add(habitat);
        textFieldJenis.setText("");
    }

    public String takeHabitat() {
        int[] selection = table.getSelectedRows();
        for (int i = 0; i < selection.length; i++) {
            selection[i] = table.convertRowIndexToModel(selection[i]);
        }

        if (selection.length > 0) {
            String namaHabitat = (String) tableModel.getValueAt(selection[0], 1);
            return namaHabitat;
        } else {
            return "";
        }
    }

    public void deleteHabitat() {
        int[] selection = table.getSelectedRows();
        for (int i = 0; i < selection.length; i++) {
            selection[i] = table.convertRowIndexToModel(selection[i]);
        }
        tableModel.remove(selection[0]);
    }

    public void addButtonSimpanActionListener(ActionListener actionListener) {
        buttonSimpan.addActionListener(actionListener);
    }

    public void addButtonDeleteActionListener(ActionListener actionListener) {
        buttonDelete.addActionListener(actionListener);
    }

    public void populateTable(List<Habitat> habitatList) {
        this.habitatList = habitatList;
        tableModel = new HabitatTableModel(habitatList);
        table.setModel(tableModel);
    }
}