package com.Student.Management;

import java.sql.*;
import java.util.Scanner;

public class Student {
    private static String deburl = "jdbc:mysql://localhost:3306/students";
    private static String username = "root";
    private static String password = "shaikh0099";
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(deburl,username,password)){
            //registring driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            //creating connection
            System.out.println("connection established");

            //write whatever logic you want to write here==>

            //i'll try to do CRUD operations here
            //lets ask user what he wants to do we have options like adding updating deleting and reading data
            System.out.println("do you want to perform CRUD operations\n" +
                    "Enter y for yes and no for no");
            String yn = sc.next();
            while (yn.equalsIgnoreCase("y")) {
                operations(connection);
                System.out.println("do you want to perform CRUD operations\n" +
                        "Enter y for yes and no for no");
                yn = sc.next();
            }


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void operations(Connection connection) {
        System.out.println("What do you want to do?\n" +
                "enter '1' for adding student\n" +
                "enter '2' for updating already existing student\n" +
                "enter '3' for getting information of students\n" +
                "enter '4' for deletnig student");
        int choice = sc.nextInt();
        switch (choice){
            case 1: Operations.addData(connection);
                break;
            case 2:Operations.updateData(connection);
                break;
            case 3:Operations.getData(connection);
                break;
            case 4:Operations.deleteData(connection);
                break;
            default:
                System.out.println("Enter correct input");
        }
    }
}

