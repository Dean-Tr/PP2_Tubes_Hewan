package module;

import dao.JenisHewanDao;
import dao.HewanDao;
import dao.HabitatDao;

public class DaoModule {
    private final JenisHewanDao jenisHewanDao;
    private final HabitatDao habitatDao;
    private final HewanDao hewanDao;

    public DaoModule() {
        this.jenisHewanDao = new JenisHewanDao();
        this.habitatDao = new HabitatDao();
        this.hewanDao = new HewanDao();
    }

    public JenisHewanDao getJenisHewanDao() {
        return jenisHewanDao;
    }

    public HabitatDao getHabitatDao() {
        return habitatDao;
    }

    public HewanDao getHewanDao() {
        return hewanDao;
    }
}
