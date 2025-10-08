package cda.bibliotheque.controller.Author;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.AuthorDAO;
import cda.bibliotheque.dao.DatabaseConnection;
import cda.bibliotheque.model.Author;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.io.IOException;

public class CreateAuthorController {

    @FXML
    private DatePicker InputBornDate;

    @FXML
    private TextField inputFirstName;

    @FXML
    private TextField inputLastName;

    private final AuthorDAO authorDAO = new AuthorDAO(DatabaseConnection.getConnection());

    @FXML
    void submit(ActionEvent event) {
        Author author = new Author();
        author.setBorn_at(InputBornDate.getValue());
        author.setFirstname(inputFirstName.getText());
        author.setLastname(inputLastName.getText());
        authorDAO.addAuthor(author);
        try {
            App.setRoot("authors/authors");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
