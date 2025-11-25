package com.example.demo1;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {

    private static final String URL = "jdbc:sqlite:cvbuilder.db";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
