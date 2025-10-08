package cda.bibliotheque.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import cda.bibliotheque.model.Author;

public class AuthorDAO {
    private Connection connection;

    public AuthorDAO(Connection connection) {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT id, lastname, firstname, born_at FROM author";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                authors.add(new Author(
                    rs.getInt("id"),
                    rs.getString("lastname"),
                    rs.getString("firstname"),
                    rs.getDate("born_at")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching authors: " + e.getMessage());
        }
        return authors;
    }

    public void addAuthor(Author author) {
        String sql = "INSERT INTO author(lastname, firstname, born_at) VALUES (?,?,?)";
        try (PreparedStatement pstmt= connection.prepareStatement(sql)) {
            pstmt.setString(1, author.getLastname());
            pstmt.setString(2, author.getFirstname());
            pstmt.setDate(3, author.getBorn_at_Date());
            pstmt.executeUpdate();
            System.out.println("Author added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding author: " + e.getMessage());
        }
    }

    public void updateAuthor(Author author) {
        String sql = "UPDATE author SET lastname = ?, firstname = ?, born_at = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, author.getLastname());
            pstmt.setString(2, author.getFirstname());
            pstmt.setDate(3, author.getBorn_at_Date());
            pstmt.setInt(4, author.getId());
           int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Author updated successfully.");
            }else {
                System.out.println("No author found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating author: " + e.getMessage());
        }
    }

    public void deleteAuthor(int id) {
        String sql = "DELETE FROM author WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Author deleted successfully.");
            } else {
                System.out.println("No author found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting author: " + e.getMessage());
        }
    }
}
