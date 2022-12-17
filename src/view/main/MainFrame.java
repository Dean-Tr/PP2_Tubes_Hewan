package view.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private final JButton buttonJenisHewan;
    private final JButton buttonHewan;
    private final JButton buttonHabitat;

    public MainFrame() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int exit = JOptionPane.showConfirmDialog(null,
                        "Apakah anda yakin ingin keluar?", "Keluar",
                        JOptionPane.YES_NO_OPTION);

                if (exit == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        this.setSize(400, 700);
        this.setLayout(new FlowLayout());
        this.buttonJenisHewan = new JButton("Jenis Hewan");
        this.buttonHewan = new JButton("Hewan");
        this.buttonHabitat = new JButton("Habitat");
        this.add(buttonJenisHewan);
        this.add(buttonHewan);
        this.add(buttonHabitat);
    }

    public JButton getButtonJenisHewan() {
        return buttonJenisHewan;
    }

    public JButton getButtonHewan() {
        return buttonHewan;
    }

    public JButton getButtonHabitat() {
        return buttonHabitat;
    }

    public void addButtonJenisHewanActionListener(ActionListener actionListener) {
        buttonJenisHewan.addActionListener(actionListener);
    }

    public void addButtonHewanActionListener(ActionListener actionListener) {
        buttonHewan.addActionListener(actionListener);
    }

    public void addButtonHabitatActionListener(ActionListener actionListener) {
        buttonHabitat.addActionListener(actionListener);
    }
}
