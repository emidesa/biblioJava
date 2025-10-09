package cda.bibliotheque.controller.Client;

import java.io.IOException;
import cda.bibliotheque.App;
import cda.bibliotheque.dao.ClientDAO;
import cda.bibliotheque.dao.DatabaseConnection;
import cda.bibliotheque.model.Client;
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

public class ClientController {
     @FXML
    private TableView<Client> ClientTable;

    @FXML
    private TableColumn<Client, String> colEmail;

    @FXML
    private TableColumn<Client, String> colFirstName;

    @FXML
    private TableColumn<Client, String> colLastName;

    @FXML
    private TableColumn<Client, Void> colActions;

    private final ObservableList<Client> clientList = FXCollections.observableArrayList();
    private final ClientDAO clientDAO = new ClientDAO(DatabaseConnection.getConnection());

    @FXML
    public void initialize() {
        colLastName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getLastname()));
        colFirstName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFirstname()));
        colEmail.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEmail()));

        colActions.setCellFactory(cell -> new TableCell<>(){
            private final Button buttonEdit = new Button ("modifier");
            private final Button buttonDelete = new Button ("Supprimer");
            private final HBox box = new HBox(10, buttonEdit, buttonDelete);
            {
                buttonEdit.setOnAction(event -> {
                    int index = getIndex();
                    Client clientToEdit = ClientTable.getItems().get(index);
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("clients/edit-client.fxml"));
                        Parent parent = loader.load();

                        EditClientController editClientController = loader.getController();
                        editClientController.setClient(clientToEdit);

                        App.getScene().setRoot(parent);
                    } catch (IOException e) {
                        System.out.println("erreur lors de la création du bouton edit ->" + e.getMessage());
                     }
                });

                buttonDelete.setOnAction(event -> {
                    int index = getIndex();
                    Client clientToDelete = ClientTable.getItems().get(index);
                    clientDAO.deleteClient(clientToDelete.getId());
                    loadClients();
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
        loadClients();
    }

    private void loadClients(){
        clientList.setAll(clientDAO.getAllClients());
        ClientTable.setItems(clientList);
    }

    @FXML
    private void switchToCreate() throws IOException {
        App.setRoot("clients/create-client");
    }
}
