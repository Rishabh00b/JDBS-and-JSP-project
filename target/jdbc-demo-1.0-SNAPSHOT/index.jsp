<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Student Table</title>
</head>
<body>

<h2>Current Students in Database</h2>

<table border="1" cellpadding="10">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
    </tr>

<%
    // Database connection info
    String URL = "jdbc:mysql://localhost:3306/demo";
    String USER = "root";
    String PASS = "admin";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM student");

        while(rs.next()) {
%>
    <tr>
        <td><%= rs.getInt("id") %></td>
        <td><%= rs.getString("name") %></td>
        <td><%= rs.getString("email") %></td>
    </tr>
<%
        }
        conn.close();
    } catch(Exception e) {
        out.println("Error: " + e.getMessage());
    }
%>

</table>

</body>
</html>
