import jdbc.utils.JdbcConfig;

import java.sql.*;

public class Transcation2 {
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
            connection.setAutoCommit(false);

            System.out.println("--------------------Before Updating------------------");
            String selection = "select * from student";
            ResultSet beforeSet = statement.executeQuery(selection);
            while(beforeSet.next()){
                int id = beforeSet.getInt("id");
                String name = beforeSet.getString("name");
                int age = beforeSet.getInt("age");
                System.out.println("ID: " + id + "\tName: " + name + "\tAge: "+ age);
            }
            connection.commit();

            System.out.println("--------------------Updating------------------");
            statement.executeUpdate("update student set age = age + 2 where id = 2");
            statement.executeUpdate("update student set age = age + 1 where id = 5");
            connection.commit();

            System.out.println("--------------------After Updating------------------");
            ResultSet afterSet = statement.executeQuery(selection);
            while(afterSet.next()){
                int id = afterSet.getInt("id");
                String name = afterSet.getString("name");
                int age = afterSet.getInt("age");
                System.out.println("ID: " + id + "\tName: " + name + "\tAge: "+ age);
            }
            connection.commit();

            System.out.println("--------------------Updating but rollback------------------");
            statement.executeUpdate("update student set age = age + 100 where id = 3");
            statement.executeUpdate("update student set age = age + 50 where id = 7");
            connection.rollback();

            System.out.println("--------------------After rollback------------------");
            ResultSet afterRoll = statement.executeQuery(selection);
            while(afterRoll.next()){
                int id = afterRoll.getInt("id");
                String name = afterRoll.getString("name");
                int age = afterRoll.getInt("age");
                System.out.println("ID: " + id + "\tName: " + name + "\tAge: "+ age);
            }
            connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
