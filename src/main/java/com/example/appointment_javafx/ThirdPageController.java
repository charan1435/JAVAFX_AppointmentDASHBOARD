package com.example.appointment_javafx;
/*
 * Name:Saicharan Gnanapiragasam
 * ID:20210655
 * */


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ThirdPageController {


    static int globalCountVariable=0;
    static String NameING;
    static int contactNumber;
    static String date;
    static String choice;
    static  String emailMatch;
    static String timeMatch;
    //regular Expression
    private String regex = "[a-zA-Z][a-zA-Z0-9\\-.]+@[a-z]+[.][a-z]{2,3}";
    private String regexTime = "[0-2][0-9]:[0-5][0-9]";

    private  ArrayList<String> BusinessType = new ArrayList<>();


    @FXML
    protected TextField abc;
    @FXML
    protected TextField contactNo;
    @FXML
    protected DatePicker dateLabel;
    @FXML
    protected ChoiceBox<String> choiceEnt;
    @FXML
    protected TextField emailInput;
    @FXML
    protected  TextField timeInput;

    public void initialize(){
        //pre existing method
        BusinessType.add("HR Meeting");
        BusinessType.add("Software Meeting");
        BusinessType.add("Enquiry Meeting");
        choiceEnt.getItems().addAll(BusinessType);

    }





    public void onConfirmClick(ActionEvent event) throws IOException {


       if (abc.getText()!=""){
            NameING=abc.getText();
            System.out.println(NameING);
            globalCountVariable++;


        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Name Not Entered");
            alert.setHeaderText("Name Not Entered");
            alert.setContentText("Information Alert");
            alert.showAndWait();

        }

        if (contactNo.getText()!= ""){
            try {
                contactNumber= Integer.parseInt(contactNo.getText());
                globalCountVariable++;
            }catch (Exception e){
                System.out.println("Enter a Integer");

            }



        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Contact Not Entered");
            alert.setHeaderText("Contact Not Entered");
            alert.setContentText("Information Alert");
            alert.showAndWait();

        }


        if (dateLabel.getValue()!=null){
            date = dateLabel.getValue().toString();


            System.out.println(dateLabel.getValue());
            globalCountVariable++;
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Date Not Entered");
            alert.setHeaderText("Date Not Entered");
            alert.setContentText("Information Alert");
            alert.showAndWait();
        }

        if (choiceEnt.getValue()!=null){
            System.out.println("TYPE HAS BEEN ENTERED");
            choice=choiceEnt.getValue();
            globalCountVariable++;

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("TYPE Not Entered");
            alert.setHeaderText("TYPE Not Entered");
            alert.setContentText("Information Alert");
            alert.showAndWait();

        }

        //Email

        if (emailInput.getText()!=""){
            String email = emailInput.getText();
            Pattern pattern= Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);

            if (matcher.matches()){
                emailMatch=email;
                System.out.println("Email Entered");
                globalCountVariable++;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Email did Not match");
                alert.setHeaderText("Email did Not match");
                alert.setContentText("Information Alert");
                alert.showAndWait();

            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Email Not Entered");
            alert.setHeaderText("Email Not Entered");
            alert.setContentText("Information Alert");
            alert.showAndWait();

        }
        if (timeInput.getText()!=""){
            String time = timeInput.getText();
            Pattern pattern = Pattern.compile(regexTime);
            Matcher matcher = pattern.matcher(time);
            if (matcher.matches()){
                timeMatch= time;
                System.out.println("Time entered Correctly");
                globalCountVariable++;
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR:Time Not Entered Correctly");
                alert.setHeaderText("ERROR:Time not in format");
                alert.setContentText("Please enter time in 24hr format with : in between Hours and Minutes");
                alert.showAndWait();

            }

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR:Time Not Entered");
            alert.setHeaderText("ERROR:Time not found");
            alert.setContentText("Please enter time in 24hr format with : in between Hours and Minutes");
            alert.showAndWait();
        }


        if (globalCountVariable==6){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Information received");
            alert.setContentText("Meeting has been scheduled");
            alert.showAndWait();
            globalCountVariable=0;

            //Database
            String jdbcURL ="jdbc:mysql://localhost:3306/assignment_final";
            String userName="root";
            String password="";


            try {
                Connection connection=DriverManager.getConnection(jdbcURL,userName,password);
                //Checking connection status
                if (connection!=null){
                    System.out.println("Database Connected successfully");
                }else {
                    System.out.println("DataBase Connection Failed");
                }
                // Use prepared statement to avoid SQL injection
                String insertQuery = "INSERT INTO client (Name,Contact_Number,Type,Time,Date,Email) Values (?,?,?,?,?,?)";
                PreparedStatement preparedStatement= connection.prepareStatement(insertQuery);

                //SETTING THE VALUES
                preparedStatement.setString(1,NameING);
                preparedStatement.setInt(2,contactNumber);
                preparedStatement.setString(3,choice);
                preparedStatement.setString(4,timeMatch);
                preparedStatement.setString(5,date);
                preparedStatement.setString(6,emailMatch);

                //Execute the Statement
                preparedStatement.executeUpdate();
                //Close
                preparedStatement.close();






            }catch (Exception e){
                e.printStackTrace();
            }
            //Loads dash Board if successful

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Appointment_view.FXML"));
            Scene scene = new Scene(fxmlLoader.load(),320,240);
            Stage stage = new Stage();
            stage.setTitle("Appointment Dashboard!..");
            stage.setScene(scene);
            stage.show();
        }else {
            //Displays error if unsuccessful
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Failed Registration");
            alert.setContentText("Please Try again");
            alert.showAndWait();
            globalCountVariable=0;
        }












    }
}
