package cda.bibliotheque.controller.Book;

import java.io.IOException;
import java.time.LocalDate;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.BookDAO;
import cda.bibliotheque.dao.DatabaseConnection;
import cda.bibliotheque.model.Book;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.HBox;

public class BookController {
    
    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> colTitle;

    @FXML
    private TableColumn<Book, LocalDate> colReleaseDate;

    @FXML
    private TableColumn<Book, Boolean> colAvailable;

    @FXML
    private TableColumn<Book, Void> colActions;

    private final ObservableList<Book> bookList = FXCollections.observableArrayList();
    private final BookDAO bookDAO = new BookDAO(DatabaseConnection.getConnection());

    @FXML
    public void initialize() {
        colTitle.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTitle()));
        colReleaseDate.setCellValueFactory(cell -> new SimpleObjectProperty<LocalDate>(cell.getValue().getRelease_date()));
        colAvailable.setCellValueFactory(cell -> new SimpleBooleanProperty(cell.getValue().isAvailable()).asObject());
        
        colAvailable.setCellFactory(CheckBoxTableCell.forTableColumn(colAvailable));

        colActions.setCellFactory(cell -> new TableCell<>(){
            private final Button buttonEdit = new Button("Modifier");
            private final Button buttonDelete = new Button("Supprimer");
            private final HBox box = new HBox(10, buttonEdit, buttonDelete);
            
            {
                buttonEdit.setOnAction(event -> {
                    int index = getIndex();
                    Book bookToEdit = bookTable.getItems().get(index);
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("books/edit-book.fxml"));
                        Parent parent = loader.load();

                        EditBookController editBookController = loader.getController();
                        editBookController.setBook(bookToEdit);

                        App.getScene().setRoot(parent);
                    } catch (IOException e) {
                        System.out.println("Erreur lors de la création du bouton edit -> " + e.getMessage());
                    }
                });

                buttonDelete.setOnAction(event -> {
                    int index = getIndex();
                    Book bookToDelete = bookTable.getItems().get(index);
                    bookDAO.deleteBook(bookToDelete.getId());
                    loadBooks();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(box);
                }
            }
        });
        
        loadBooks();
    }

    private void loadBooks(){
        bookList.setAll(bookDAO.getAllBooks());
        bookTable.setItems(bookList);
    }

    @FXML
    private void switchToCreate() throws IOException {
        App.setRoot("books/create-book");
    }
}