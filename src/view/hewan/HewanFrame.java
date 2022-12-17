package view.hewan;

import model.JenisHewan;
import model.Habitat;
import model.Hewan;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public class HewanFrame extends JFrame {
    private List<JenisHewan> jenisHewanList;
    private List<Habitat> habitatList;
    private List<Hewan> hewanList;
    private final JTextField textFieldNama;
    private final JTextField textFieldUmur;
    private JRadioButton radioButtonL;
    private JRadioButton radioButtonP;
    private HewanTableModel tableModel;
    private final JComboBox<String> comboJenis;
    private final JComboBox<String> comboHabitat;
    private final JButton buttonSimpan;
    private final JButton buttonDelete;
    private final JButton buttonExportPdf;
    private final JTable table;

    public HewanFrame() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelInputNama = new JLabel("Nama:");
        labelInputNama.setBounds(15, 40, 350, 10);
        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        JLabel labelInputUmur = new JLabel("Umur:");
        labelInputUmur.setBounds(15, 100, 350, 10);
        textFieldUmur = new JTextField();
        textFieldUmur.setBounds(15, 120, 350, 30);

        JLabel labelJenisKelamin = new JLabel("Jenis Kelamin:");
        labelJenisKelamin.setBounds(15, 150, 350, 30);
        radioButtonL = new JRadioButton("Laki-Laki", true);
        radioButtonL.setBounds(15, 175, 350, 30);
        radioButtonP = new JRadioButton("Perempuan");
        radioButtonP.setBounds(15, 200, 350, 30);

        JLabel labelJenis = new JLabel("Jenis Hewan:");
        labelJenis.setBounds(15, 240, 350, 10);
        comboJenis = new JComboBox<String>();
        comboJenis.setBounds(15, 260, 150, 30);

        JLabel labelHabitat = new JLabel("Habitat Hewan:");
        labelHabitat.setBounds(15, 300, 350, 10);
        comboHabitat = new JComboBox<String>();
        comboHabitat.setBounds(15, 320, 150, 30);

        buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 360, 100, 40);

        buttonDelete = new JButton("Hapus");
        buttonDelete.setBounds(130, 360, 100, 40);

        buttonExportPdf = new JButton("Export PDF");
        buttonExportPdf.setBounds(245, 360, 100, 40);
        table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 410, 350, 200);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButtonL);
        bg.add(radioButtonP);

        this.add(buttonSimpan);
        this.add(buttonDelete);
        this.add(buttonExportPdf);
        this.add(textFieldNama);
        this.add(labelInputNama);
        this.add(textFieldUmur);
        this.add(labelInputUmur);
        this.add(radioButtonL);
        this.add(radioButtonP);
        this.add(labelJenisKelamin);
        this.add(labelJenis);
        this.add(comboJenis);
        this.add(labelHabitat);
        this.add(comboHabitat);
        this.add(scrollableTable);

        this.setSize(400, 700);
        this.setLayout(null);
    }

    public void populateTable(List<Hewan> hewanList) {
        this.hewanList = hewanList;
        tableModel = new HewanTableModel(hewanList);
        table.setModel(tableModel);
    }

    public void populateComboJenis(List<JenisHewan> jenisHewanList) {
        this.jenisHewanList = jenisHewanList;
        comboJenis.removeAllItems();
        for (JenisHewan jenisHewan : jenisHewanList) {
            comboJenis.addItem(jenisHewan.getJenis());
        }
    }

    public void populateComboHabitat(List<Habitat> habitatList) {
        this.habitatList = habitatList;
        comboHabitat.removeAllItems();
        for (Habitat habitat : habitatList) {
            comboHabitat.addItem(habitat.getNamaHabitat());
        }
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public String getUmur() {
        return textFieldUmur.getText();
    }

    public String getJenisKelamin() {
        String jenisKelamin = "";

        if (radioButtonL.isSelected()) {
            jenisKelamin = radioButtonL.getText();
        }
        if (radioButtonP.isSelected()) {
            jenisKelamin = radioButtonP.getText();
        }
        return jenisKelamin;
    }

    public JenisHewan getJenisHewan() {
        return jenisHewanList.get(comboJenis.getSelectedIndex());
    }

    public Habitat getHabitat() {
        return habitatList.get(comboHabitat.getSelectedIndex());
    }

    public void addHewan(Hewan hewan) {
        tableModel.add(hewan);
        textFieldNama.setText("");
        textFieldUmur.setText("");
    }

    public String takeHewan() {
        int[] selection = table.getSelectedRows();
        for (int i = 0; i < selection.length; i++) {
            selection[i] = table.convertRowIndexToModel(selection[i]);
        }

        if (selection.length > 0) {
            String namaHewan = (String) tableModel.getValueAt(selection[0], 5);
            return namaHewan;
        } else {
            return "";
        }
    }

    public void deleteHewan() {
        int[] selection = table.getSelectedRows();
        for (int i = 0; i < selection.length; i++) {
            selection[i] = table.convertRowIndexToModel(selection[i]);
        }
        tableModel.remove(selection[0]);
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void addButtonSimpanActionListener(ActionListener actionListener) {
        buttonSimpan.addActionListener(actionListener);
    }

    public void addButtonExportPdfActionListener(ActionListener actionListener) {
        buttonExportPdf.addActionListener(actionListener);
    }

    public void addButtonDeleteActionListener(ActionListener actionListener) {
        buttonDelete.addActionListener(actionListener);
    }

    public List<Hewan> getHewanList() {
        return hewanList;
    }
}
