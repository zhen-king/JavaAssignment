import jdbc.utils.JdbcConfig;

import java.sql.*;

public class Delection {
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
            System.out.println("--------------------Before Delection------------------");
            String selection = "select * from student";
            ResultSet beforeSet = statement.executeQuery(selection);
            while (beforeSet.next()){
                int id = beforeSet.getInt("id");
                String name = beforeSet.getString("Name");
                int age = beforeSet.getInt("age");
                System.out.println("ID: " + id + "\tName: " + name + "\tAge: "+ age);
            }

            System.out.println("--------------------Delecting------------------");
            String deletion = "delete from student where id between 10 and 12";
            int countDelete = statement.executeUpdate(deletion);
            System.out.println(countDelete + " records are deleted");

            System.out.println("--------------------Delecting table------------------");
            String deletionTable = "drop table newstudent";
            int countDeleteTable = statement.executeUpdate(deletionTable);
            System.out.println(countDeleteTable + " records are deleted");

            System.out.println("--------------------After Delection------------------");
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
