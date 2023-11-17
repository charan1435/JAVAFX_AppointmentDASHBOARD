package com.example.appointment_javafx;

/*
* Name:Saicharan Gnanapiragasam
* ID:20210655
* */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.appointment_javafx.ThirdPageController.NameING;

public class AppointmentController {

    @FXML
    private Label welcomeText;

    //Changing scenes according to button Click

    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SecondPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = new Stage();
        stage.setTitle("View");
        stage.setScene(scene);
        stage.show();



    }
    @FXML
    protected void onScheduleClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoaderS = new FXMLLoader(getClass().getResource("thirdPage.fxml"));
        Scene scene = new Scene(fxmlLoaderS.load(), 320, 240);
        Stage stageS = new Stage();
        stageS.setTitle("Schedule");
        stageS.setScene(scene);
        stageS.show();

    }
    @FXML
    protected void onUpdateClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoaderU = new FXMLLoader(getClass().getResource("fourthPage.fxml"));
        Scene scene = new Scene(fxmlLoaderU.load(), 320, 240);
        Stage stageU = new Stage();
        stageU.setTitle("Update");
        stageU.setScene(scene);
        stageU.show();

    }

    @FXML
    protected void  onCancelClick(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoaderC = new FXMLLoader(getClass().getResource("fifthPage.fxml"));
        Scene scene = new Scene(fxmlLoaderC.load(),320,240);
        Stage stageC = new Stage();
        stageC.setTitle("Cancel");
        stageC.setScene(scene);
        stageC.show();
    }

}