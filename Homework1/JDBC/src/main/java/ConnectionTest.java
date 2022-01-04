import jdbc.utils.JdbcConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest {
    public static void main(String[] args) {
        try (
                // Creating the connection
                Connection connection = DriverManager.getConnection(
                        JdbcConfig.getUrl(),
                        JdbcConfig.getUser(),
                        JdbcConfig.getPassword()
                );
                // Creating statement
                Statement statement = connection.createStatement();
        ) {
            System.out.println("Connecting to the database successfully!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
