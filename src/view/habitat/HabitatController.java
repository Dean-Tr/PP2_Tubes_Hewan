package view.habitat;

import model.Habitat;
import module.DaoModule;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;

public class HabitatController {
    private final DaoModule daoModule;
    private final HabitatFrame habitatFrame;

    public HabitatController(DaoModule daoModule) {
        this.daoModule = daoModule;
        habitatFrame = new HabitatFrame();

        habitatFrame.addButtonSimpanActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(habitatFrame,
                        "Simpan Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    simpan();
                } else {
                    JOptionPane.showMessageDialog(habitatFrame, "Anda tidak menyimpan data");
                }
            }
        });

        habitatFrame.addButtonDeleteActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(habitatFrame,
                        "Hapus Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    delete();
                } else {
                    JOptionPane.showMessageDialog(habitatFrame, "Anda tidak menghapus data");
                }
            }
        });
    }

    public void showHabitatFrame() {
        List<Habitat> habitatList = daoModule.getHabitatDao().findAll();
        habitatFrame.populateTable(habitatList);
        habitatFrame.setVisible(true);
    }

    public void simpan() {
        String namaHabitat = this.habitatFrame.getNamaHabitat();
        if (namaHabitat.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this.habitatFrame, "Form tidak boleh ada yang kosong");
        } else {
            Habitat habitat = new Habitat();
            habitat.setId(UUID.randomUUID().toString());
            habitat.setNamaHabitat(namaHabitat);
            this.habitatFrame.addHabitat(habitat);
            daoModule.getHabitatDao().insert(habitat);
        }
    }

    public void delete() {
        Habitat namaH = new Habitat();
        namaH.setId(this.habitatFrame.takeHabitat());

        if (namaH.getId() != "") {
            this.habitatFrame.deleteHabitat();
            daoModule.getHabitatDao().delete(namaH);
        } else {
            JOptionPane.showMessageDialog(this.habitatFrame, "Tolong pilih baris tabel yang mau dihapus");
        }
    }
}
