/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author Cyber Micro
 */
// yardımcı sınıftır olmakla beraber yapı taşları olan Conn,ResultSet gibi elemanları içerir.
public class DbUtils {
    public static void changeScene(ActionEvent event, String fxmlfile, String title, String username, String favChannel) {
        Parent root = null;
        if (username != null && favChannel != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DbUtils.class.getResource(fxmlfile));
                root = loader.load();
                Controllers.MainPageController mainPageController = loader.getController();
                mainPageController.setUserİnformation(username, favChannel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DbUtils.class.getResource(fxmlfile));
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 480));
        stage.show();

    }

    public static void SignUpUser(ActionEvent event, String password, String username, String favChannel) {
        //Db conn öznesini oluşturduk. Bağlatı öznemiz budur.
        Connection connection = null;
        PreparedStatement psInstert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;
        try {
            //GetConnection Database ile bağlantı kurulmasını sağlar.
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/infocusdb", "root", "1234");
            psCheckUserExist = connection.prepareStatement("Select * from users WHERE username = ?");
            psCheckUserExist.setString(1, username);
            resultSet = psCheckUserExist.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User ALready Exist!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("you cannot use this username");
                alert.show();
            } else {
                psInstert = connection.prepareStatement(("Insert INTO users(username,password,favChannel) Values (?,?,?) "));
                psInstert.setString(1, username);
                psInstert.setString(2, password);
                psInstert.setString(3, favChannel);
                psInstert.executeUpdate();

                changeScene(event, "MainPage.fxml", "Welcome", username, favChannel);


            }

            //
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {

                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (psCheckUserExist != null) {

                try {
                    psCheckUserExist.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (psInstert != null) {

                try {
                    psInstert.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (connection != null) {

                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
    public static void LogInUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/infocusdb", "root", "1234");
            preparedStatement = connection.prepareStatement("SELECT password,favchannel FROM users WHERE username = ? ");
            preparedStatement.setString(1,username);
            resultset = preparedStatement.executeQuery();
            if(!resultset.isBeforeFirst()){
                System.out.println("USer not found in this database! ");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrerct!");
                alert.show();
            }else{
                while(resultset.next()){
                    String retrievedPassword = resultset.getString("password");
                    String retrievedChannel = resultset.getString("favChannel");
                     if(retrievedPassword.equals(password)){
                         changeScene(event,"MainPage.fxml","Welcome!",username,retrievedChannel); 
                }else{
                         System.out.println("Password did not match! ");
                         Alert alert=  new Alert(Alert.AlertType.ERROR);
                         alert.setContentText("the provided credential are incorrect! ");
                         alert.show();
                         
                     }
                }   
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if (resultset != null) {

                try {
                    resultset.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (preparedStatement != null) {

                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (connection != null) {

                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }

}