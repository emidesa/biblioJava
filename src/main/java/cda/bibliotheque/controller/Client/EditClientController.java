package cda.bibliotheque.controller.Client;

import java.io.IOException;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.ClientDAO;
import cda.bibliotheque.dao.DatabaseConnection;
import cda.bibliotheque.model.Client;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditClientController {
     public ObjectProperty<Client> client = new SimpleObjectProperty<>();
    public final ClientDAO clientDAO = new ClientDAO(DatabaseConnection.getConnection());
      @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputFirstName;

    @FXML
    private TextField inputLastName;

    @FXML
    void submit(ActionEvent event) throws IOException {
        Client newClient = client.get();
        newClient.setEmail(inputEmail.getText());
        newClient.setFirstname(inputFirstName.getText());
        newClient.setLastname(inputLastName.getText());
        clientDAO.updateClient(newClient);
            App.setRoot("clients/clients");
        
    }

   

    @FXML
    public void initialize() {
        client.addListener((obs, oldClient, newClient) -> {;
        if (newClient != null) {
        inputEmail.setText(newClient.getEmail());
        inputFirstName.setText(newClient.getFirstname());
        inputLastName.setText(newClient.getLastname());
        }
    });
    }

    public EditClientController(){

    }

    public void setClient(Client client) {
        this.client.set(client);
    }
}
