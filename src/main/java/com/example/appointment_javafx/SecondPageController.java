package com.example.appointment_javafx;
/*
 * Name:Saicharan Gnanapiragasam
 * ID:20210655
 * */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;

public class SecondPageController {

    //Setting up Buttons AND views in UI
    @FXML
    protected ListView<String> myListView;
    @FXML
    protected CheckBox dateSearch;
    @FXML
    protected CheckBox nameSearch;
    @FXML
    protected CheckBox typeSearch;
    @FXML
    protected CheckBox numberSearch;
    @FXML
    protected CheckBox timeSearch;
    @FXML
    protected TextField searchItem;




    public void searchByFilter(ActionEvent event){
        //clears at start
        myListView.getItems().clear();

        //Database Connection
        String jdbcURL ="jdbc:mysql://localhost:3306/assignment_final";
        String userName="root";
        String password="";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL,userName,password);


            //Checking Connection
            if (connection!=null){
                System.out.println("Database Connected successfully");
            }else {
                System.out.println("DataBase Connection Failed");
            }


            if (dateSearch.isSelected()){

                //Prevent tic of Multiple check box
                if (nameSearch.isSelected() || typeSearch.isSelected() || numberSearch.isSelected() || timeSearch.isSelected() ){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Please tik Only one Check Box!!");
                    alert.setHeaderText("TIK one Check Box");
                    alert.setHeaderText("Please Click only 1 check Box");
                    alert.showAndWait();

                }


                String query ="SELECT * FROM client WHERE Date=?";
                PreparedStatement preparedStatement= connection.prepareStatement(query);

                String searchVal=searchItem.getText();
                preparedStatement.setString(1,searchVal);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    String nameInp=resultSet.getString("Name");
                    String emailInp=resultSet.getString("Email");
                    String numberInp=resultSet.getString("Contact_Number");
                    String typeInp=resultSet.getString("Type");
                    String idInp=resultSet.getString("Client_ID");
                    String timeInp=resultSet.getString("Time");
                    String dateInp=resultSet.getString("Date");

                    //Concatenate Strings

                    StringBuilder  stringBuilder = new StringBuilder();
                    stringBuilder.append("Client ID :").append(idInp).append(", ").append("Name :").append(nameInp).append(", ").append("Contact :").append(numberInp).append(", ").append("Type :").append(typeInp).append(", ").append("Time :").append(timeInp).append(", ").append("date :").append(dateInp).append(", ").append("email :").append(emailInp);




                    //Sets Text to List View
                    myListView.getItems().addAll(stringBuilder.toString());

                }


            } else if (nameSearch.isSelected()) {
                //Prevent tic of Multiple check box
                if (dateSearch.isSelected() || typeSearch.isSelected() || numberSearch.isSelected() || timeSearch.isSelected() ){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Please tik Only one Check Box!!");
                    alert.setHeaderText("TIK one Check Box");
                    alert.setHeaderText("Please Click only 1 check Box");
                    alert.showAndWait();

                }


                //SQL statement
                String query = "SELECT * FROM client WHERE Name=?";
                //Prepared statement to prevent SQL injection attack
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                String searchVal=searchItem.getText();
                //Setting parameter
                preparedStatement.setString(1,searchVal);

                ResultSet resultSet = preparedStatement.executeQuery();
                //Getting database contents
                while (resultSet.next()){



                    String nameInp=resultSet.getString("Name");
                    String emailInp=resultSet.getString("Email");
                    String numberInp=resultSet.getString("Contact_Number");
                    String typeInp=resultSet.getString("Type");
                    String idInp=resultSet.getString("Client_ID");
                    String timeInp=resultSet.getString("Time");
                    String dateInp=resultSet.getString("Date");

                    StringBuilder  stringBuilder = new StringBuilder();
                    stringBuilder.append("Client ID :").append(idInp).append(", ").append("Name :").append(nameInp).append(", ").append("Contact :").append(numberInp).append(", ").append("Type :").append(typeInp).append(", ").append("Time :").append(timeInp).append(", ").append("date :").append(dateInp).append(", ").append("email :").append(emailInp);





                    myListView.getItems().addAll(stringBuilder.toString());


                }



            } else if (typeSearch.isSelected()) {
                //Prevent tic of Multiple check box
                if (nameSearch.isSelected() || dateSearch.isSelected() || numberSearch.isSelected() || timeSearch.isSelected() ){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Please tik Only one Check Box!!");
                    alert.setHeaderText("TIK one Check Box");
                    alert.setHeaderText("Please Click only 1 check Box");
                    alert.showAndWait();

                }


                String query = "SELECT * FROM client WHERE Type=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                String searchVal=searchItem.getText();
                preparedStatement.setString(1,searchVal);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){



                    String nameInp=resultSet.getString("Name");
                    String emailInp=resultSet.getString("Email");
                    String numberInp=resultSet.getString("Contact_Number");
                    String typeInp=resultSet.getString("Type");
                    String idInp=resultSet.getString("Client_ID");
                    String timeInp=resultSet.getString("Time");
                    String dateInp=resultSet.getString("Date");

                    StringBuilder  stringBuilder = new StringBuilder();
                    stringBuilder.append("Client ID :").append(idInp).append(", ").append("Name :").append(nameInp).append(", ").append("Contact :").append(numberInp).append(", ").append("Type :").append(typeInp).append(", ").append("Time :").append(timeInp).append(", ").append("date :").append(dateInp).append(", ").append("email :").append(emailInp);





                    myListView.getItems().addAll(stringBuilder.toString());


                }








            } else if (numberSearch.isSelected()){
                //Prevent tic of Multiple check box
                if (nameSearch.isSelected() || typeSearch.isSelected() || dateSearch.isSelected() || timeSearch.isSelected() ){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Please tik Only one Check Box!!");
                    alert.setHeaderText("TIK one Check Box");
                    alert.setHeaderText("Please Click only 1 check Box");
                    alert.showAndWait();

                }


                String query ="SELECT * FROM client WHERE Contact_Number = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(query);

                String searchVal=searchItem.getText();
                preparedStatement.setString(1,searchVal);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){



                    String nameInp=resultSet.getString("Name");
                    String emailInp=resultSet.getString("Email");
                    String numberInp=resultSet.getString("Contact_Number");
                    String typeInp=resultSet.getString("Type");
                    String idInp=resultSet.getString("Client_ID");
                    String timeInp=resultSet.getString("Time");
                    String dateInp=resultSet.getString("Date");

                    StringBuilder  stringBuilder = new StringBuilder();
                    stringBuilder.append("Client ID :").append(idInp).append(", ").append("Name :").append(nameInp).append(", ").append("Contact :").append(numberInp).append(", ").append("Type :").append(typeInp).append(", ").append("Time :").append(timeInp).append(", ").append("date :").append(dateInp).append(", ").append("email :").append(emailInp);





                    myListView.getItems().addAll(stringBuilder.toString());


                }





            } else if (timeSearch.isSelected()) {

                //Prevent tic of Multiple check box
                if (nameSearch.isSelected() || typeSearch.isSelected() || numberSearch.isSelected() || dateSearch.isSelected() ){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Please tik Only one Check Box!!");
                    alert.setHeaderText("TIK one Check Box");
                    alert.setHeaderText("Please Click only 1 check Box");
                    alert.showAndWait();

                }


                String query= "SELECT * FROM client WHERE Time = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                String searchVal=searchItem.getText();
                preparedStatement.setString(1,searchVal);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){



                    String nameInp=resultSet.getString("Name");
                    String emailInp=resultSet.getString("Email");
                    String numberInp=resultSet.getString("Contact_Number");
                    String typeInp=resultSet.getString("Type");
                    String idInp=resultSet.getString("Client_ID");
                    String timeInp=resultSet.getString("Time");
                    String dateInp=resultSet.getString("Date");

                    StringBuilder  stringBuilder = new StringBuilder();
                    stringBuilder.append("Client ID :").append(idInp).append(", ").append("Name :").append(nameInp).append(", ").append("Contact :").append(numberInp).append(", ").append("Type :").append(typeInp).append(", ").append("Time :").append(timeInp).append(", ").append("date :").append(dateInp).append(", ").append("email :").append(emailInp);





                    myListView.getItems().addAll(stringBuilder.toString());


                }









            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Please tik one Check Box!!");
                alert.setHeaderText("TIK one Check Box");
                alert.setHeaderText("Before clicking button tik one search criteria");
                alert.showAndWait();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void viewAllBtn(ActionEvent event){
        //clears at start
        myListView.getItems().clear();

        //Database
        String jdbcURL ="jdbc:mysql://localhost:3306/assignment_final";
        String userName="root";
        String password="";

        Connection connection;
        try {

            //Database Connection
            connection = DriverManager.getConnection(jdbcURL,userName,password);

            //Checking Connection
            if (connection!=null){
                System.out.println("Database Connected successfully");
            }else {
                System.out.println("DataBase Connection Failed");
            }
            //SQL Statement
            String insertQuery = "SELECT * FROM client";
            PreparedStatement preparedStatement= connection.prepareStatement(insertQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            //iterate over row
            while (resultSet.next()){
                StringBuilder rowStringBuilder = new StringBuilder();
                //iterate over column
                for (int i=1;i<=columnCount;i++){
                    String columnName = metaData.getColumnName(i);
                    String columnValue = resultSet.getString(columnName);
                    rowStringBuilder.append(columnName).append(": ").append(columnValue).append(", ");

                }
                String rowString = rowStringBuilder.toString();
                //Remove the trailing comma and space
                rowString = rowString.substring(0,rowString.length() -2);
                System.out.println(rowString);
                //printing to the List View
                myListView.getItems().add(rowString);





            }


            resultSet.close();
            preparedStatement.close();
            connection.close();






        } catch (SQLException e) {
            //Displays error incase
            throw new RuntimeException(e);
        }



    }














}
