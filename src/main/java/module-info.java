module com.example.helb_electro {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.helb_electro to javafx.fxml;
    exports com.example.helb_electro;
}