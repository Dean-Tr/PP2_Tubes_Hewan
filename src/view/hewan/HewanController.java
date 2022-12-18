package view.hewan;

import model.JenisHewan;
import model.Habitat;
import model.Hewan;
import module.DaoModule;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;

public class HewanController {

    private final DaoModule daoModule;
    private final HewanFrame hewanFrame;
    private final HewanPdfExport hewanPdfExport;

    public HewanController(DaoModule daoModule) {
        this.daoModule = daoModule;
        this.hewanFrame = new HewanFrame();
        this.hewanPdfExport = new HewanPdfExport();

        this.hewanFrame.addButtonSimpanActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(hewanFrame,
                        "Simpan Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    HewanController.this.simpan();
                } else {
                    JOptionPane.showMessageDialog(hewanFrame, "Anda tidak menyimpan data");
                }
            }
        });

        this.hewanFrame.addButtonExportPdfActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(hewanFrame,
                        "Export PDF?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    HewanController.this.exportPdf();
                } else {
                    JOptionPane.showMessageDialog(hewanFrame, "Anda tidak mengexport PDF");
                }
            }
        });

        this.hewanFrame.addButtonDeleteActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(hewanFrame,
                        "Hapus Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    HewanController.this.delete();
                } else {
                    JOptionPane.showMessageDialog(hewanFrame, "Anda tidak menghapus data");
                }
            }
        });
    }

    public void showHewanFrame() {
        List<Hewan> hewanList = daoModule.getHewanDao().findAll();
        hewanFrame.populateTable(hewanList);
        List<JenisHewan> jenisHewanList = daoModule.getJenisHewanDao().findAll();
        hewanFrame.populateComboJenis(jenisHewanList);
        List<Habitat> habitatList = daoModule.getHabitatDao().findAll();
        hewanFrame.populateComboHabitat(habitatList);
        hewanFrame.setVisible(true);
    }

    public void simpan() {
        String nama = this.hewanFrame.getNama();
        String umur = this.hewanFrame.getUmur();
        String jenisKelamin = this.hewanFrame.getJenisKelamin();
        JenisHewan jenisHewan = this.hewanFrame.getJenisHewan();
        Habitat habitat = this.hewanFrame.getHabitat();
        if (nama.trim().isEmpty() || umur.trim().isEmpty() || jenisKelamin.trim().isEmpty()) {
            this.hewanFrame.showAlert("Form tidak boleh ada yang kosong");
        } else {
            Hewan hewan = new Hewan();
            hewan.setId(UUID.randomUUID().toString());
            hewan.setNama(nama);
            hewan.setUmur(umur);
            hewan.setJenisKelamin(jenisKelamin);
            hewan.setJenisHewan(jenisHewan);
            hewan.setHabitat(habitat);
            this.hewanFrame.addHewan(hewan);
            daoModule.getHewanDao().insert(hewan);
        }
    }

    public void delete() {
        Hewan namaH = new Hewan();
        namaH.setId(this.hewanFrame.takeHewan());

        if (namaH.getId() != "") {
            this.hewanFrame.deleteHewan();
            daoModule.getHewanDao().delete(namaH);
        } else {
            JOptionPane.showMessageDialog(this.hewanFrame, "Tolong pilih baris tabel yang mau dihapus");
        }
    }

    public void exportPdf() {
        try {
            hewanPdfExport.export(hewanFrame.getHewanList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
