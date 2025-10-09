package cda.bibliotheque.controller.Book;

import java.io.IOException;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.BookDAO;
import cda.bibliotheque.dao.DatabaseConnection;
import cda.bibliotheque.model.Book;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class EditBookController {

    public ObjectProperty<Book> book = new SimpleObjectProperty<>();
    public final BookDAO bookDAO = new BookDAO(DatabaseConnection.getConnection());
    
    @FXML
    private DatePicker inputReleaseDate;

    @FXML
    private TextField inputTitle;

    @FXML
    private CheckBox inputAvailable;

    @FXML
    void submit(ActionEvent event) throws IOException {
        Book newBook = book.get();
        newBook.setRelease_date(inputReleaseDate.getValue());
        newBook.setTitle(inputTitle.getText());
        newBook.setAvailable(inputAvailable.isSelected());
        bookDAO.updateBook(newBook);
        App.setRoot("books/books");
    }

    @FXML
    public void initialize() {
        book.addListener((obs, oldBook, newBook) -> {
            if (newBook != null) {
                inputReleaseDate.setValue(newBook.getRelease_date());
                inputTitle.setText(newBook.getTitle());
                inputAvailable.setSelected(newBook.isAvailable());
            }
        });
    }

    public EditBookController() {
      
    }

    public void setBook(Book book) {
        this.book.set(book);
    }
}
