module com.example.lab08_vrundpatel {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires opencsv;


    opens com.example.lab08_vrundpatel to javafx.fxml;
    exports com.example.lab08_vrundpatel;
}