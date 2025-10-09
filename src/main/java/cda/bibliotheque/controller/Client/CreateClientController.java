package cda.bibliotheque.controller.Client;

import java.io.IOException;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.ClientDAO;
import cda.bibliotheque.dao.DatabaseConnection;
import cda.bibliotheque.model.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateClientController {
    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputFirstName;

    @FXML
    private TextField inputLastName;

    private final ClientDAO clientDAO = new ClientDAO(DatabaseConnection.getConnection());

    @FXML
    void submit(ActionEvent event) {
        Client client = new Client();
        client.setEmail(inputEmail.getText());
        client.setFirstname(inputFirstName.getText());
        client.setLastname(inputLastName.getText());
        clientDAO.addClient(client);
        try {
            App.setRoot("clients/clients");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
