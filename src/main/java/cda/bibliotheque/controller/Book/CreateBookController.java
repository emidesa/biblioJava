package cda.bibliotheque.controller.Book;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.BookDAO;
import cda.bibliotheque.dao.DatabaseConnection;
import cda.bibliotheque.model.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.io.IOException;

public class CreateBookController {

    @FXML
    private DatePicker inputReleaseDate;

    @FXML
    private TextField inputTitle;

    @FXML
    private CheckBox inputAvailable;

    private final BookDAO bookDAO = new BookDAO(DatabaseConnection.getConnection());

    @FXML
    void submit(ActionEvent event) {
        Book book = new Book();
        book.setRelease_date(inputReleaseDate.getValue());
        book.setTitle(inputTitle.getText());
        book.setAvailable(inputAvailable.isSelected());
        bookDAO.addBook(book);
        try {
            App.setRoot("books/books");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
