package com.example.appointment_javafx;
/*
 * Name:Saicharan Gnanapiragasam
 * ID:20210655
 * */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppointmentApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Loads Screen

        FXMLLoader fxmlLoader = new FXMLLoader(AppointmentApplication.class.getResource("Appointment_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Appointment Dashboard!..");
        stage.setScene(scene);
        stage.show();
    }
//Launches
    public static void main(String[] args) {
        launch();
    }
}