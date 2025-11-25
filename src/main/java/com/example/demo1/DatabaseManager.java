package com.example.demo1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:cvbuilder.db";

    static {
        createTableIfNotExists();
    }

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    private static void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS cv ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "fullName TEXT,"
                + "email TEXT,"
                + "phone TEXT,"
                + "address TEXT,"
                + "education TEXT,"
                + "skills TEXT,"
                + "workExperience TEXT,"
                + "projects TEXT,"
                + "photoPath TEXT"
                + ")";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert and return generated id
    public static int insertCV(CV cv) {
        String sql = "INSERT INTO cv (fullName, email, phone, address, education, skills, workExperience, projects, photoPath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, cv.getFullName());
            pstmt.setString(2, cv.getEmail());
            pstmt.setString(3, cv.getPhone());
            pstmt.setString(4, cv.getAddress());
            pstmt.setString(5, cv.getEducation());
            pstmt.setString(6, cv.getSkills());
            pstmt.setString(7, cv.getWorkExperience());
            pstmt.setString(8, cv.getProjects());
            pstmt.setString(9, cv.getPhotoPath());

            int affected = pstmt.executeUpdate();
            if (affected == 0) return -1;

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static List<CV> getAllCV() {
        List<CV> list = new ArrayList<>();
        String sql = "SELECT * FROM cv ORDER BY id DESC";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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
                list.add(cv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean updateCV(CV cv) {
        String sql = "UPDATE cv SET fullName=?, email=?, phone=?, address=?, education=?, skills=?, workExperience=?, projects=?, photoPath=? WHERE id=?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cv.getFullName());
            pstmt.setString(2, cv.getEmail());
            pstmt.setString(3, cv.getPhone());
            pstmt.setString(4, cv.getAddress());
            pstmt.setString(5, cv.getEducation());
            pstmt.setString(6, cv.getSkills());
            pstmt.setString(7, cv.getWorkExperience());
            pstmt.setString(8, cv.getProjects());
            pstmt.setString(9, cv.getPhotoPath());
            pstmt.setInt(10, cv.getId());

            int affected = pstmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteCV(int id) {
        String sql = "DELETE FROM cv WHERE id=?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int affected = pstmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Utility: load single CV by id
    public static CV getCVById(int id) {
        String sql = "SELECT * FROM cv WHERE id=?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new CV(
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
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
