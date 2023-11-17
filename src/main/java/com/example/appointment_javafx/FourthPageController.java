package com.example.appointment_javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Name:Saicharan Gnanapiragasam
 * ID:20210655
 * */

public class FourthPageController {
    static int globalCountVariableF=0;

    //Regular expression
    private String regex = "[a-zA-Z][a-zA-Z0-9\\-.]+@[a-z]+[.][a-z]{2,3}";
    private String regexTime = "[0-2][0-9]:[0-5][0-9]";
    //Setting UI Components and array list for choice BOX
    private static ArrayList<String> choiceInputs = new ArrayList<>();
    static String NameINGF;
    static int contactNumberF;
    static String dateF;
    static String choiceF;
    static  String emailMatchF;
    static String timeMatchF;
    @FXML
    protected TextField idSearch;
    @FXML
    protected TextField name;
    @FXML
    protected TextField email;
    @FXML
    protected ChoiceBox<String> type;
    @FXML
    protected TextField contact;
    @FXML
    protected DatePicker date;
    @FXML
    protected TextField time;

    public void initialize(){
        //Default method called
        choiceInputs.add("HR Meeting");
        choiceInputs.add("Software Meeting");
        choiceInputs.add("Enquiry Meeting");
        type.getItems().addAll(choiceInputs);

    }

    public void searchAction(ActionEvent event) {
        //Database
        String jdbcURL ="jdbc:mysql://localhost:3306/assignment_final";
        String userName="root";
        String password="";

        String query="SELECT * FROM client WHERE Client_ID=?";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL,userName,password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            String searchVal = idSearch.getText();
            preparedStatement.setString(1,searchVal);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                String nameInp=resultSet.getString("Name");
                name.setText(nameInp);
                String emailInp=resultSet.getString("Email");
                email.setText(emailInp);
                String numberInp=resultSet.getString("Contact_Number");
                contact.setText(numberInp);
                String typeInp=resultSet.getString("Type");
                type.setValue(typeInp);
                String idInp=resultSet.getString("Client_ID");
                //No need
                String timeInp=resultSet.getString("Time");
                time.setText(timeInp);
                String dateInp=resultSet.getString("Date");
                //Convert the String to Date Format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dateG = LocalDate.parse(dateInp,formatter);
                date.setValue(dateG);




            }
        } catch (SQLException e) {
            //Error message
            throw new RuntimeException(e);
        }
    }

    public void updateAction(ActionEvent event) {

        if (name.getText() != "") {
            NameINGF = name.getText();
            System.out.println(NameINGF);
            globalCountVariableF++;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Name Not Entered");
            alert.setHeaderText("Name Not Entered");
            alert.setContentText("Information Alert");
            alert.showAndWait();
        }

        if (contact.getText() != "") {
            try {
                contactNumberF = Integer.parseInt(contact.getText());
                globalCountVariableF++;

            } catch (Exception e) {
                System.out.println("Enter a Integer");

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Contact Not Entered");
            alert.setHeaderText("Contact Not Entered");
            alert.setContentText("Information Alert");
            alert.showAndWait();

        }

        if (date.getValue() != null) {
            dateF = date.getValue().toString();
            globalCountVariableF++;


            System.out.println(date.getValue());

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Date Not Entered");
            alert.setHeaderText("Date Not Entered");
            alert.setContentText("Information Alert");
            alert.showAndWait();
        }

        if (type.getValue() != null) {
            System.out.println("TYPE HAS BEEN ENTERED");
            choiceF = type.getValue();
            globalCountVariableF++;


        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("TYPE Not Entered");
            alert.setHeaderText("TYPE Not Entered");
            alert.setContentText("Information Alert");
            alert.showAndWait();

        }

        if (email.getText() != "") {
            String emailMatchFO = email.getText();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(emailMatchFO);
            if (matcher.matches()) {
                System.out.println("Email Matched");
                emailMatchF = emailMatchFO;
                globalCountVariableF++;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Email did Not match");
                alert.setHeaderText("Email did Not match");
                alert.setContentText("Information Alert");
                alert.showAndWait();

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Email Not Entered");
            alert.setHeaderText("Email Not Entered");
            alert.setContentText("Information Alert");
            alert.showAndWait();

        }

        if (time.getText() != "") {
            String timesJo = time.getText();
            Pattern pattern = Pattern.compile(regexTime);
            Matcher matcher = pattern.matcher(timesJo);
            if (matcher.matches()) {
                timeMatchF = timesJo;
                System.out.println("Time entered Correctly");
                globalCountVariableF++;

            } else {
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

        if (globalCountVariableF==6 && (idSearch.getText()!="")){



            //Database
            String jdbcURL ="jdbc:mysql://localhost:3306/assignment_final";
            String userName="root";
            String password="";


            try {
                Connection connection = DriverManager.getConnection(jdbcURL,userName,password);
                String queryF = "UPDATE client SET Name=?, Contact_Number=?, Type=?, Time=?, Date=?, Email=? WHERE Client_ID=?";

                PreparedStatement preparedStatement = connection.prepareStatement(queryF);
                preparedStatement.setString(1,NameINGF);
                preparedStatement.setInt(2,contactNumberF);
                preparedStatement.setString(3,choiceF);
                preparedStatement.setString(4,timeMatchF);
                preparedStatement.setString(5,dateF);
                preparedStatement.setString(6,emailMatchF);
                preparedStatement.setString(7, idSearch.getText());

                int rowsAffected = preparedStatement.executeUpdate();
                preparedStatement.close();
                //Checks for rows affected and displays appropriate prompts
                if (rowsAffected==0){
                    Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert1.setTitle("No records ");
                    alert1.setHeaderText("Nothing Found");
                    alert1.setContentText("No record Matched");
                    alert1.showAndWait();
                    globalCountVariableF=0;

                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Information received");
                    alert.setContentText("Meeting has been Updated");
                    alert.showAndWait();
                    globalCountVariableF=0;
                }

                globalCountVariableF=0;




            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }else {
            //Error Message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Failed Registration");
            alert.setContentText("Step 1 : Client ID " +
                    "Step 2 :Search " +
                    "Step 3: press Update Button");
            alert.showAndWait();
           globalCountVariableF=0;

        }


    }
}
