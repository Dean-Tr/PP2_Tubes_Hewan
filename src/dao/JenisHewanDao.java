package dao;

import db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import model.JenisHewan;

public class JenisHewanDao {
    public int insert(JenisHewan jenisHewan) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("insert into jenis_hewan (id, jenis) value (?, ?)");
            statement.setString(1, jenisHewan.getId());
            statement.setString(2, jenisHewan.getJenis());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(JenisHewan jenisHewan) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("update jenis_hewan set jenis = ? where id = ?");
            statement.setString(1, jenisHewan.getJenis());
            statement.setString(2, jenisHewan.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(JenisHewan jenisHewan) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("delete from jenis_hewan where id = ?");
            statement.setString(1, jenisHewan.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<JenisHewan> findAll() {
        List<JenisHewan> list = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
                Statement statement = connection.createStatement();) {
            try (ResultSet resultSet = statement.executeQuery("select * from jenis_hewan");) {
                // Retrieving the data
                while (resultSet.next()) {
                    JenisHewan jenisHewan = new JenisHewan();
                    jenisHewan.setId(resultSet.getString("id"));
                    jenisHewan.setJenis(resultSet.getString("jenis"));
                    list.add(jenisHewan);
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
