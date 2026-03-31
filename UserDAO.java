import java.sql.*;
import java.util.*;

public class UserDAO {

    // INSERT
public void insertUser(int id, String name, String email, String country) {
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(
             "INSERT INTO users (id, name, email, country) VALUES (?, ?, ?, ?)")) {

        stmt.setInt(1, id);
        stmt.setString(2, name);
        stmt.setString(3, email);
        stmt.setString(4, country);

        int rows = stmt.executeUpdate();
        System.out.println("Inserted rows: " + rows);

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    // READ
    public List<String> getAllUsers() {
        List<String> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {

            while (rs.next()) {
                String row = rs.getInt("id") + " - " +
                             rs.getString("name") + " - " +
                             rs.getString("email") + " - " +
                             rs.getString("country");
                System.out.println("Fetched: " + row); // ✅ Debug print
                list.add(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public void updateUser(int id, String name, String email, String country) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "UPDATE users SET name=?, email=?, country=? WHERE id=?")) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, country);
            stmt.setInt(4, id);

            int rows = stmt.executeUpdate();
            System.out.println("✅ Updated rows: " + rows);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteUser(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "DELETE FROM users WHERE id=?")) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println("✅ Deleted rows: " + rows);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    