module x.passwordgenerator {
    requires javafx.controls;
    requires javafx.fxml;


    opens x.passwordgenerator to javafx.fxml;
    exports x.passwordgenerator;
}