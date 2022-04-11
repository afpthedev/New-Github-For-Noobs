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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;


public class SignUpController implements Initializable {
    @FXML
    private Button button_signup;
    @FXML
    private Button button_login;
    @FXML
    private RadioButton AFP_1;
    @FXML
    private RadioButton AFP_2;
    @FXML
    private TextField tf_password;
    @FXML
    private TextField tf_username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup togglegroup = new ToggleGroup();
        AFP_1.setToggleGroup(togglegroup);
        AFP_2.setToggleGroup(togglegroup);

        AFP_1.setSelected(true);
        button_signup.setOnAction(new EventHandler < ActionEvent > () {
            @Override
            public void handle(ActionEvent event) {
                String toggleName = ((RadioButton) togglegroup.getSelectedToggle()).getText();
                if (tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()) {} else {
                    System.out.println("Please fill in all information !");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information in this sign up!");
                    alert.show();
                }
            }
        });
        
        button_login.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                DbUtils.changeScene(event, "MainPage.fxml", "Log İn!", null,null);
                
            }
            
            
            
        });


    }

}