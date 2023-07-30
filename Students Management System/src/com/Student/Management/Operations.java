package com.Student.Management;

import java.sql.*;
import java.util.Scanner;

public class Operations {
    static  Scanner sc = new Scanner(System.in);
    public static void addData(Connection connection){

        System.out.println("enter roll number");
        int rollNum = sc.nextInt();
        System.out.println("entr name");
        String name = sc.next();
        System.out.println("enter city ");
        String city = sc.next();
        String sqlQuery = "insert into students(rollNum,name,city) values(?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1,rollNum);
            ps.setString(2,name);
            ps.setString(3,city);

            int rowAffected = ps.executeUpdate();
            //System.out.println("rows affected are "+rowAffected );
            if(rowAffected>0){
                System.out.println("student added succesfully");
            }else {
                System.out.println("failed to insert record");
            }
        } catch (SQLException e) {
            System.out.println("This roll number already exists\n" +
                    "enter another roll number");

            addData(connection);
        }
    }
    public static void updateData(Connection connection){
        System.out.println("enter the roll number of student you want to update data of");
        int rollNum = sc.nextInt();
        System.out.println("Enter 1 for name updation\n" +
                "enter 2 for city updation\n");
        int choice = sc.nextInt();
       // String sqlUpdate = rollNum == 1?"update students set name=? where rollNum=?": rollNum==2?"update students set city=? where rollNum=?" ;

        try {
            PreparedStatement preparedStatement;
            String sqlUpdate;
            if(choice == 1){
                sqlUpdate = "update students set name=? where rollNum=?";
                preparedStatement= connection.prepareStatement(sqlUpdate);
                System.out.println("enter updated name");
                String name = sc.next();
                preparedStatement.setString(1,name);
                preparedStatement.setInt(2,rollNum);
                //System.out.println(preparedStatement.executeUpdate());
                if(preparedStatement.executeUpdate()>0)
                    System.out.println("name updated");


            } else if (choice==2) {
                sqlUpdate = "update students set city=? where rollNum=?";
                preparedStatement = connection.prepareStatement(sqlUpdate);
                System.out.println("enter updated city name");
                String city = sc.next();
                preparedStatement.setString(1, city);
                preparedStatement.setInt(2, rollNum);
                if(preparedStatement.executeUpdate()>0)
                    System.out.println("city updated");
            }



        } catch (SQLException e) {
            System.out.println("Student with given roll number doesnt exists\n" +
                    "try again");
            updateData(connection);
        }


    }
    public static void getData(Connection connection){
        System.out.println("if you want all student to display then press 1\n" +
                "if ypu want student with specific roll number then press 2");
        int selection = sc.nextInt();
        if(selection==2)
            getDataByRollNum(connection);
        else {
            String sqlQuery = "select * from students";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sqlQuery);
                while (resultSet.next()) {
                    System.out.println("roll Number: " + resultSet.getInt(2) + ", Name: " + resultSet.getString(3) + ", City: " + resultSet.getString(4));
                }
            } catch (SQLException e) {
                getDataByRollNum(connection);
            }
        }
    }

    private static void getDataByRollNum(Connection connection) {
        System.out.println("enter roll number of student you want to get info of");
        int rollNum = sc.nextInt();
        String sqlQuery = "select * from students where rollNum="+rollNum;
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sqlQuery);
           // PreparedStatement preparedStatement = statement.executeQuery(sqlQuery);

            while (resultSet.next())
                System.out.println("roll Number: "+resultSet.getInt(2)+", Name: "+resultSet.getString(3)+", City: "+resultSet.getString(4));

        } catch (SQLException e) {
            System.out.println("Student with given roll number doesnt exists\n" +
                    "try again");
            getDataByRollNum(connection);
        }
    }

    public static void deleteData(Connection connection){
        System.out.println("enter roll number of student that you want to delete");
        int rollNum = sc.nextInt();
        String sqlDelete = "delete from students where rollNUm = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete);
            preparedStatement.setInt(1,rollNum);
            int rowAffected = preparedStatement.executeUpdate();
            //System.out.println("rows affected are "+rowAffected );
            if(rowAffected>0){
                System.out.println("student deleted");
            }else {
                System.out.println("failed to delete student");
            }
        } catch (SQLException e) {
            System.out.println("student with given roll number do not exists\n" +
                    "try another roll number ");
        }


    }
}
