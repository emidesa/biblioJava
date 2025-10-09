package cda.bibliotheque.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cda.bibliotheque.model.Book;

public class BookDAO {
    private Connection connection;

    public BookDAO(Connection connection) {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT id, title, release_date, isAvailable FROM books";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                books.add(new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getDate("release_date"),
                    rs.getBoolean("isAvailable")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching books: " + e.getMessage());
        }
        return books;
    }

    public void addBook(Book book) {
        String sql = "INSERT INTO books(title, release_date, isAvailable) VALUES (?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setDate(2, book.getRelease_date_Date());
            pstmt.setBoolean(3, book.isAvailable());
            pstmt.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding book: " + e.getMessage());
        }
    }

    public void updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, release_date = ?, isAvailable = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setDate(2, book.getRelease_date_Date());
            pstmt.setBoolean(3, book.isAvailable());
            pstmt.setInt(4, book.getId());
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Book updated successfully.");
            } else {
                System.out.println("No book found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating book: " + e.getMessage());
        }
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("No book found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting book: " + e.getMessage());
        }
    }
}
