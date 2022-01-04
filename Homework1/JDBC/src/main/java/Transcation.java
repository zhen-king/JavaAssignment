import jdbc.utils.JdbcConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Transcation {
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
            try {
                connection.setAutoCommit(false);
                statement.executeUpdate("insert into student values (10, 'King', 33)");
              //  statement.executeUpdate("insert into student values (9, 'Bill', 33)");
                statement.executeUpdate("insert into student values (11, 'Jill', 26)");
                connection.commit();
                System.out.println("Commit Successfully");
            } catch (SQLException throwables) {
                System.out.println("Rolling back changes");
                connection.rollback();
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
