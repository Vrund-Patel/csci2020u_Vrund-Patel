module com.example.assignment2_vrundpatel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens com.example.assignment2_vrundpatel to javafx.fxml;
    exports com.example.assignment2_vrundpatel;
}