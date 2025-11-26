import java.net.URL;
import java.sql.*;

public class JDBCdemo {
    private static final String URL="jdbc:mysql://localhost:3306/demo";
    private static final String USER="root";
    private static final String PASSWORD="admin";

    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)) {
            System.out.println("Connection Established");
            insertStudent(connection,"ronak@gmail.com","Ronak");
         //   insertStudent(connection,"yash@gmail.com","Yash");
//            updateStudent(connection,"abc@gmail.com","ABC",1);
            selectStudent(connection);
//            deletestudent(connection,2);
        } catch (SQLException e) {
            e.getStackTrace();
        }
        System.out.println("Connection Closed");
    }
    public static void insertStudent(Connection connection, String email,String name){
        String sql="INSERT INTO student (name,email) values ('" +name+"','"+email+"')";
        try(Statement statement=connection.createStatement()) {
            int rows= statement.executeUpdate(sql);
            System.out.println("INSERTED: "+rows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void  selectStudent(Connection conn){
        String sql="SELECT * from student";
        try (Statement statement = conn.createStatement();){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String email=resultSet.getString("email");
                System.out.println(id+" | "+name+" | "+email);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void updateStudent(Connection conn,String email,String name,int id){
        String sql= "UPDATE student SET name ='"+name+"',email = '"+email+"' WHERE id = '"+id+"'";
        try(Statement statement = conn.createStatement()){
            int i = statement.executeUpdate(sql);
            System.out.println("UPDATED: "+i);
        }
        catch (SQLException e){
            e.getStackTrace();
        }
    }
    public static void deletestudent(Connection connection,int id){
        String sql="DELETE from student where id = "+id;
        try(Statement statement = connection.createStatement()){
            int rows=statement.executeUpdate(sql);
            System.out.println("DELETED: "+rows);
        }
        catch(SQLException e){
            e.getStackTrace();
        }
    }
}
/*
try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Connection Established");
        } catch (SQLException e) {
            e.getStackTrace();
        }
        finally {
            try {
                connection.close();
                System.out.println("COnnection Closed");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
 */