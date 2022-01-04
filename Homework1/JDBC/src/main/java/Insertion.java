import jdbc.utils.JdbcConfig;

import java.sql.*;

public class Insertion {
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
            System.out.println("--------------------Before Insertion------------------");
            String selection = "select * from student";
            ResultSet beforeSet = statement.executeQuery(selection);
            while (beforeSet.next()){
                int id = beforeSet.getInt("id");
                String name = beforeSet.getString("Name");
                int age = beforeSet.getInt("age");
                System.out.println("ID: " + id + "\tName: " + name + "\tAge: "+ age);
            }

            System.out.println("--------------------insert one record------------------");
            String insertOne = "insert into student values " +
                    "(10, 'Ling', 22)";
            int countOneInsert = statement.executeUpdate(insertOne);
            System.out.println(countOneInsert + " records are inserted");

            System.out.println("--------------------insert multiple record------------------");
            String insertMore = "insert into student values " +
                    "(11, 'Li', 22)," +
                    "(12, 'Hui', 23)";
            int countMoreInsert = statement.executeUpdate(insertMore);
            System.out.println(countMoreInsert + " records are inserted");

            System.out.println("--------------------insert partial record------------------");
            String insertPart = "insert into student (id, name) values " +
                    "(13, 'Liu')," +
                    "(14, 'Jiang')";
            int countPartInsert = statement.executeUpdate(insertPart);
            System.out.println(countPartInsert + " records are inserted");

            System.out.println("--------------------After Insertion------------------");
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
