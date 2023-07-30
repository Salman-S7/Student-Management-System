package com.Student.Management;

import java.sql.*;

public class Student {
    static String deburl = "jdbc:mysql://localhost:3306/students";
    static String username = "root";
    static String password = "shaikh0099";
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(deburl,username,password)){
            //registring driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            //creating connection
            System.out.println("connection established");

            //write whatever logic you want to write here==>





        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

