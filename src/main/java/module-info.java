module cda.bibliotheque {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens cda.bibliotheque.controller.Author to javafx.fxml;
    opens cda.bibliotheque.controller.Book to javafx.fxml;

    opens cda.bibliotheque.controller to javafx.fxml;
    exports cda.bibliotheque;
}
