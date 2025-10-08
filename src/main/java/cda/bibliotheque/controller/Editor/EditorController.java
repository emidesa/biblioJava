package cda.bibliotheque.controller.Editor;

import java.io.IOException;
import java.time.LocalDate;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.EditorDAO;
import cda.bibliotheque.dao.DatabaseConnection;
import cda.bibliotheque.model.Editor;
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
import javafx.scene.layout.HBox;

public class EditorController {
    
    @FXML
    private TableView<Editor> editorTable;

    @FXML
    private TableColumn<Editor, String> colLabel;

    @FXML
    private TableColumn<Editor, LocalDate> colCreatedAt;

    @FXML
    private TableColumn<Editor, Void> colActions;

    private final ObservableList<Editor> editorList = FXCollections.observableArrayList();
    private final EditorDAO editorDAO = new EditorDAO(DatabaseConnection.getConnection());

    @FXML
    public void initialize() {
       
        colLabel.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getLabel()));
        colCreatedAt.setCellValueFactory(cell -> new SimpleObjectProperty<LocalDate>(cell.getValue().getCreated_at()));

  
        colActions.setCellFactory(cell -> new TableCell<>(){
            private final Button buttonEdit = new Button("Modifier");
            private final Button buttonDelete = new Button("Supprimer");
            private final HBox box = new HBox(10, buttonEdit, buttonDelete);
            
            {
                buttonEdit.setOnAction(event -> {
                    int index = getIndex();
                    Editor editorToEdit = editorTable.getItems().get(index);
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("editors/edit-editor.fxml"));
                        Parent parent = loader.load();

                        EditEditorController editEditorController = loader.getController();
                        editEditorController.setEditor(editorToEdit);

                        App.getScene().setRoot(parent);
                    } catch (IOException e) {
                        System.out.println("Erreur lors de la création du bouton edit -> " + e.getMessage());
                    }
                });

                buttonDelete.setOnAction(event -> {
                    int index = getIndex();
                    Editor editorToDelete = editorTable.getItems().get(index);
                    editorDAO.deleteEditor(editorToDelete.getId());
                    loadEditors();
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
        
        loadEditors();
    }

    private void loadEditors(){
        editorList.setAll(editorDAO.getAllEditors());
        editorTable.setItems(editorList);
    }

    @FXML
    private void switchToCreate() throws IOException {
        App.setRoot("editors/create-editor");
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}