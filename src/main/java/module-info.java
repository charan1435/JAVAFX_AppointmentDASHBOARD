module com.example.appointment_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.appointment_javafx to javafx.fxml;
    exports com.example.appointment_javafx;
}