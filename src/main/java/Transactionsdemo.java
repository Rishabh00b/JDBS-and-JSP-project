import java.sql.*;

public class Transactionsdemo {
    private static final String URL="jdbc:mysql://localhost:3306/demo";
    private static final String USER="root";
    private static final String PASSWORD="admin";
    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)) {
            try {
                connection.setAutoCommit(false);
                System.out.println("Connection Established");
                int order_id = insert(connection, 1, "Arnav", 2000.0);
                order_items(connection, order_id, "LAPTOP", 2, 2000);
                connection.commit();
                System.out.println("OPERATIONS COMMITTED SUCCESSFULLY");
            }//Manual Commit
            catch (Exception ex) {
                connection.rollback();
                System.out.println("Operation ROLLBACK successfully");
            }
            finally {
                 connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        System.out.println("Connection Closed");
    }

    private static void order_items(Connection connection, int orderId, String product_name, int quantity, int price) {
        String sql="INSERT INTO ORDER_ITEMS (order_id,product_name,quantity,price) values(?,?,?,?)";
        try(PreparedStatement ps= connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1,orderId);
            ps.setString(2,product_name);
            ps.setInt(3,quantity);
            ps.setDouble(4,price);
            int i = ps.executeUpdate();
//            int i1 = 10/0;
            System.out.println("INSERTED INTO ORDER ITEMS : "+i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int insert(Connection connection, int id, String name, double price) {
        String sql="INSERT INTO ORDERS (user_id,customer_name,total_amount) values(?,?,?)";
        try(PreparedStatement ps= connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1,id);
            ps.setString(2,name);
            ps.setDouble(3,price);
            int i = ps.executeUpdate();
            System.out.println("UPDATED ROWS : "+i);
            try(ResultSet resultSet=ps.getGeneratedKeys()){
                if(resultSet.next()){
                    int orderID=resultSet.getInt(1);
                    return  orderID;
                }
                else{
                    throw new SQLException("ORDER ID NOT GENERATED");
                }
            }

        } catch (SQLException e) {
            e.getStackTrace();
            return -1;
        }
    }
}
