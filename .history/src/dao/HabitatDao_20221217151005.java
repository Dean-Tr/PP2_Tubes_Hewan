package dao;

import db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import model.Habitat;

public class HabitatDao {
    //  Get Insert Habibat Dao in Database
    public int insert(Habitat habitat) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("insert into habitat (id, nama_habitat) value (?, ?)");
            statement.setString(1, habitat.getId());
            statement.setString(2, habitat.getNamaHabitat());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Update Habitat Dao in Database
    public int update(Habitat habitat) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("update habitat set nama_habitat = ? where id = ?");
            statement.setString(1, habitat.getNamaHabitat());
            statement.setString(2, habitat.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Delete Habibat dao in database
    public int delete(Habitat habitat) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("delete from habitat where id = ?");
            statement.setString(1, habitat.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Habitat> findAll() {
        List<Habitat> list = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
                Statement statement = connection.createStatement();) {
            try (ResultSet resultSet = statement.executeQuery("select * from habitat");) {
                // Retrieving the data
                while (resultSet.next()) {
                    Habitat habitat = new Habitat();
                    habitat.setId(resultSet.getString("id"));
                    habitat.setNamaHabitat(resultSet.getString("nama_habitat"));
                    list.add(habitat);
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
