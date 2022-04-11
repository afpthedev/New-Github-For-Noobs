/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Util.DbUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author Cyber Micro
 */
public class LoginPageController implements Initializable{
    // değişkenler(variables)
    @FXML
    private Button button_login;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;
    @FXML 
    private Button button_sign_up; 
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       button_login.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        // button da aktif  olduğu zaman login usera bağlanarak oradan user kontrolu yapar.
        public void handle(ActionEvent event){
            DbUtils.LogInUser(event, tf_username.getText(), tf_password.getText());
        }
    });
       //sign up ekranına yönlendirir.
        button_sign_up.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
            DbUtils.changeScene(event, "sign-up-FXML.fxml","Sign UP!",null,null);
        }
    });
}
}