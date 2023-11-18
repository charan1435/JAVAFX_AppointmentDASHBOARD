package com.example.appointment_javafx;
/*
 * Name:Saicharan Gnanapiragasam
 * ID:20210655
 * */

/**
 * DataBase should be seperated  **/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.*;

public class FifthPageController {
    //Setting UI unique IDS
    @FXML
    protected TextField idCheck;

    @FXML
    protected ListView<String> idListView;
    public  void  searchID(ActionEvent event){
        //Clear BTN
        idListView.getItems().clear();

        //Database
        String jdbcURL ="jdbc:mysql://localhost:3306/assignment_final";
        String userName="root";
        String password="";

        String query="SELECT * FROM client WHERE Client_ID=?";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL,userName,password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            String valCheck= idCheck.getText();
            preparedStatement.setString(1,valCheck);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                //Search through database
                String nameInp=resultSet.getString("Name");
                String emailInp=resultSet.getString("Email");
                String numberInp=resultSet.getString("Contact_Number");
                String typeInp=resultSet.getString("Type");
                String idInp=resultSet.getString("Client_ID");
                String timeInp=resultSet.getString("Time");
                String dateInp=resultSet.getString("Date");

                StringBuilder  stringBuilder = new StringBuilder();
                stringBuilder.append("Client ID :").append(idInp).append(", ").append("Name :").append(nameInp).append(", ").append("Contact :").append(numberInp).append(", ").append("Type :").append(typeInp).append(", ").append("Time :").append(timeInp).append(", ").append("date :").append(dateInp).append(", ").append("email :").append(emailInp);


                System.out.println(stringBuilder.toString());
                idListView.getItems().addAll(stringBuilder.toString());

            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

    }

    public void  confirmDelete(ActionEvent event){
        //Clear BTN
        idListView.getItems().clear();

        //Database
        String jdbcURL ="jdbc:mysql://localhost:3306/assignment_final";
        String userName="root";
        String password="";

        //SQL query statement
        String query="DELETE FROM client WHERE Client_ID=?";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL,userName,password);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             String valIn = idCheck.getText();
             preparedStatement.setString(1,valIn);
             //Check if any rows got affected if not display error
             int rowsAffected= preparedStatement.executeUpdate();
             if (rowsAffected>0){
                 System.out.println("Successfully Deleted");
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                 alert.setTitle("Success!!");
                 alert.setHeaderText("Deleted");
                 alert.setHeaderText("Record Has been removed from database with ID : "+valIn);
                 alert.showAndWait();

             }else {
                 System.out.println("No record found");
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Not Found");
                 alert.setHeaderText("Not Found");
                 alert.setHeaderText("No record was found with the ID :"+valIn);
                 alert.showAndWait();
             }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
