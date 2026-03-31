import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/userdb?useSSL=false&serverTimezone=UTC";
    private static String jdbcUsername = "root";   // your MySQL username
    private static String jdbcPassword = "mangal123"; // your MySQL password

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            connection.setAutoCommit(true); // ✅ force commit
            System.out.println("✅ Connected to Database!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
