package dao;

import db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import model.Hewan;
import model.JenisHewan;
import model.Habitat;

public class HewanDao {
    public int insert(Hewan hewan) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(
                            "insert into hewan (id, nama, umur, jenis_kelamin, jenis_hewan_id, habitat_id) value (?, ?, ?, ?, ?, ?)");
            statement.setString(1, hewan.getId());
            statement.setString(2, hewan.getNama());
            statement.setString(3, hewan.getUmur());
            statement.setString(4, hewan.getJenisKelamin());
            statement.setString(5, hewan.getJenisHewan().getId());
            statement.setString(6, hewan.getHabitat().getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Hewan hewan) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(
                            "update hewan set nama = ?, umur = ?, jenis_kelamin = ?, jenis_hewan_id = ?, habitat_id = ? where id = ?");
            statement.setString(1, hewan.getNama());
            statement.setString(1, hewan.getUmur());
            statement.setString(1, hewan.getJenisKelamin());
            statement.setString(2, hewan.getJenisHewan().getId());
            statement.setString(2, hewan.getHabitat().getId());
            statement.setString(3, hewan.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(Hewan hewan) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("delete from hewan where id = ?");
            statement.setString(1, hewan.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Hewan> findAll() {
        List<Hewan> list = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
                Statement statement = connection.createStatement();) {
            try (ResultSet resultSet = statement
                    .executeQuery(
                            "select hewan.*, jenis_hewan.*, habitat.* "
                                    + "from hewan "
                                    + "LEFT OUTER JOIN jenis_hewan on jenis_hewan.id = hewan.jenis_hewan_id "
                                    + "LEFT OUTER JOIN habitat on habitat.id = hewan.habitat_id");) {
                // Retrieving the data
                while (resultSet.next()) {
                    Hewan hewan = new Hewan();
                    hewan.setId(resultSet.getString("id"));
                    hewan.setNama(resultSet.getString("nama"));
                    hewan.setUmur(resultSet.getString("umur"));
                    hewan.setJenisKelamin(resultSet.getString("jenis_kelamin"));

                    JenisHewan jenisHewan = new JenisHewan();
                    jenisHewan.setId(resultSet.getString("hewan.jenis_hewan_id"));
                    jenisHewan.setJenis(resultSet.getString("jenis_hewan.jenis"));

                    Habitat habitat = new Habitat();
                    habitat.setId(resultSet.getString("hewan.habitat_id"));
                    habitat.setNamaHabitat(resultSet.getString("habitat.nama_habitat"));

                    hewan.setJenisHewan(jenisHewan);
                    hewan.setHabitat(habitat);

                    list.add(hewan);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
