import jdbc.utils.JdbcConfig;

import java.sql.*;

public class PreparedStatementTest {
    public static void main(String[] args) throws SQLException {
        try(
            Connection connection = DriverManager.getConnection(
                    JdbcConfig.getUrl(),
                    JdbcConfig.getUser(),
                    JdbcConfig.getPassword()
            );
            PreparedStatement pst = connection.prepareStatement(
                    "insert into student values(?, ?, ?)"
            );
            PreparedStatement pstSelect = connection.prepareStatement(
                    "select * from student"
            );

        ){
            //set all the parameters
            pst.setInt(1,8);
            pst.setString(2, "Wang");
            pst.setInt(3, 22);
            int rowInserted = pst.executeUpdate();
            System.out.println(rowInserted + " records are inserted");

            //partial changes
            pst.setInt(1, 9);
            rowInserted = pst.executeUpdate();
            System.out.println(rowInserted + " records are inserted");

            ResultSet resultSet = pstSelect.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                System.out.println("ID: " + id + "\tName: " + name + "\tAge: "+ age);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}


