<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Student Table</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 30px;
            background: #f4f6f9;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 25px;
        }

        .table-container {
            width: 80%;
            margin: auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            border-radius: 10px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            border-radius: 10px;
            overflow: hidden;
        }

        th {
            background: #4CAF50;
            color: white;
            padding: 12px;
            font-size: 16px;
        }

        td {
            padding: 12px;
            text-align: center;
            color: #444;
        }

        tr:nth-child(even) {
            background: #f2f2f2;
        }

        tr:hover {
            background: #d5f5e3;
            transition: 0.2s;
        }
    </style>
</head>
<body>

<h2>Current Students in Database</h2>

<div class="table-container">

<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
    </tr>

<%
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
</div>

</body>
</html>
