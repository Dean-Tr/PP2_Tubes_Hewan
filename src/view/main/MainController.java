package view.main;

import module.DaoModule;
import view.hewan.HewanController;
import view.jenishewan.JenisHewanController;
import view.habitat.HabitatController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    private final MainFrame mainFrame;
    private final HewanController hewanController;
    private final JenisHewanController jenisHewanController;
    private final HabitatController habitatController;

    public MainController() {
        mainFrame = new MainFrame();
        DaoModule daoModule = new DaoModule();
        hewanController = new HewanController(daoModule);
        jenisHewanController = new JenisHewanController(daoModule);
        habitatController = new HabitatController(daoModule);
        mainFrame.addButtonJenisHewanActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showJenisHewanFrame();
            }
        });
        mainFrame.addButtonHewanActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHewanFrame();
            }
        });
        mainFrame.addButtonHabitatActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHabitatFrame();
            }
        });
    }

    public void showMainFrame() {
        this.mainFrame.setVisible(true);
    }

    public void showJenisHewanFrame() {
        jenisHewanController.showJenisHewanFrame();
    }

    public void showHewanFrame() {
        hewanController.showHewanFrame();
    }

    public void showHabitatFrame() {
        habitatController.showHabitatFrame();
    }
}
