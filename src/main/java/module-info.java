module com.example.helb_electro {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.helb_electro to javafx.fxml;
    exports com.example.helb_electro;
    exports com.example.helb_electro.products;
    opens com.example.helb_electro.products to javafx.fxml;
    exports com.example.helb_electro.components;
    opens com.example.helb_electro.components to javafx.fxml;
    exports com.example.helb_electro.Box;
    opens com.example.helb_electro.Box to javafx.fxml;
}