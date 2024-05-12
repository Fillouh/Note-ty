module org.fillouh.notety {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.fillouh.notety to javafx.fxml;
    exports org.fillouh.notety;
    exports org.fillouh.notety.controllers;
    opens org.fillouh.notety.controllers to javafx.fxml;
}