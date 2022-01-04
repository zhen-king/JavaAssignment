import jdbc.utils.JdbcConfig;

import java.sql.*;

public class Creation {
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
            System.out.println("--------------------Creating------------------");
            String create = "create table newStudent as select id, name, age from student";
            int countCreate = statement.executeUpdate(create);
            System.out.println(countCreate + " records are create");

            System.out.println("--------------------After Creating------------------");
            String selection = "select * from newStudent";
            ResultSet afterSet = statement.executeQuery(selection);
            while(afterSet.next()){
                int id = afterSet.getInt("id");
                String name = afterSet.getString("Name");
                int age = afterSet.getInt("age");
                System.out.println("ID: " + id + "\tName: " + name + "\tAge: "+ age);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
