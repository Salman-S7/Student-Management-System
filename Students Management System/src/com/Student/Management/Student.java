package com.Student.Management;

public class Student {
    public static void main(String[] args) {
        try {
            Class.forName("");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
