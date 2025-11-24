package com.example.demo1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:cvbuilder.db";
    private static Connection connection;

    // SQL commands
    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS cvs (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "fullName TEXT NOT NULL," +
                    "email TEXT," +
                    "phone TEXT," +
                    "address TEXT," +
                    "education TEXT," +
                    "skills TEXT," +
                    "workExperience TEXT," +
                    "projects TEXT," +
                    "photoPath TEXT" +
                    ");";

    private static final String INSERT_SQL =
            "INSERT INTO cvs (fullName, email, phone, address, education, skills, workExperience, projects, photoPath) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_ALL_SQL =
            "SELECT * FROM cvs ORDER BY id ASC";

    private static final String UPDATE_SQL =
            "UPDATE cvs SET fullName=?, email=?, phone=?, address=?, education=?, skills=?, workExperience=?, projects=?, photoPath=? WHERE id=?;";

    private static final String DELETE_SQL =
            "DELETE FROM cvs WHERE id=?;";

    // Initialize database connection
    public static void initializeDatabase() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL);
                createTable();
                System.out.println("Database created successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error initializing database.");
        }
    }

    // Create table
    private static void createTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_TABLE_SQL)) {
            statement.executeUpdate();
        }
    }

    // Insert CV
    public static CV insertCV(CV cv) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cv.getFullName());
            ps.setString(2, cv.getEmail());
            ps.setString(3, cv.getPhone());
            ps.setString(4, cv.getAddress());
            ps.setString(5, cv.getEducation());
            ps.setString(6, cv.getSkills());
            ps.setString(7, cv.getWorkExperience());
            ps.setString(8, cv.getProjects());
            ps.setString(9, cv.getPhotoPath());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    cv.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cv;
    }

    // Get all CVs
    public static List<CV> getAllCVs() {
        List<CV> cvs = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CV cv = new CV(
                        rs.getInt("id"),
                        rs.getString("fullName"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("education"),
                        rs.getString("skills"),
                        rs.getString("workExperience"),
                        rs.getString("projects"),
                        rs.getString("photoPath")
                );
                cvs.add(cv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cvs;
    }

    // Update CV
    public static void updateCV(CV cv) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, cv.getFullName());
            ps.setString(2, cv.getEmail());
            ps.setString(3, cv.getPhone());
            ps.setString(4, cv.getAddress());
            ps.setString(5, cv.getEducation());
            ps.setString(6, cv.getSkills());
            ps.setString(7, cv.getWorkExperience());
            ps.setString(8, cv.getProjects());
            ps.setString(9, cv.getPhotoPath());
            ps.setInt(10, cv.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete CV
    public static void deleteCV(int id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Close connection
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
