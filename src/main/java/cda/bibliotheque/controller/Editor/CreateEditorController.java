package cda.bibliotheque.controller.Editor;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.EditorDAO;
import cda.bibliotheque.dao.DatabaseConnection;
import cda.bibliotheque.model.Editor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.io.IOException;

public class CreateEditorController {

    @FXML
    private DatePicker inputCreatedAt;

    @FXML
    private TextField inputLabel;

    private final EditorDAO editorDAO = new EditorDAO(DatabaseConnection.getConnection());

    @FXML
    void submit(ActionEvent event) {
        Editor editor = new Editor();
        editor.setCreated_at(inputCreatedAt.getValue());
        editor.setLabel(inputLabel.getText());
        editorDAO.addEditor(editor);
        try {
            App.setRoot("editors/editors");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
