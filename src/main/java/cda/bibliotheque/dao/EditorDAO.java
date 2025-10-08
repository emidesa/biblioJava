package cda.bibliotheque.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cda.bibliotheque.model.Editor;

public class EditorDAO {
    private Connection connection;

    public EditorDAO(Connection connection) {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Editor> getAllEditors() {
        List<Editor> editors = new ArrayList<>();
        String sql = "SELECT id, label, created_at FROM editor";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                editors.add(new Editor(
                    rs.getInt("id"),
                    rs.getString("label"),
                    rs.getDate("created_at")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching editors: " + e.getMessage());
        }
        return editors;
    }

    public void addEditor(Editor editor) {
        String sql = "INSERT INTO editor(label, created_at) VALUES (?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, editor.getLabel());
            pstmt.setDate(2, editor.getCreated_at_Date());
            pstmt.executeUpdate();
            System.out.println("Editor added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding editor: " + e.getMessage());
        }
    }

    public void updateEditor(Editor editor) {
        String sql = "UPDATE editor SET label = ?, created_at = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, editor.getLabel());
            pstmt.setDate(2, editor.getCreated_at_Date());
            pstmt.setInt(3, editor.getId());
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Editor updated successfully.");
            } else {
                System.out.println("No editor found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating editor: " + e.getMessage());
        }
    }

    public void deleteEditor(int id) {
        String sql = "DELETE FROM editor WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Editor deleted successfully.");
            } else {
                System.out.println("No editor found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting editor: " + e.getMessage());
        }
    }
}