/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author Cyber Micro
 */
//Contorller ve denetleyiciler için Initializable kullanılırız.
public class MainPageController implements Initializable {

    //Sayfamızda bulunan temel bileşenleri
    @FXML
    private Button button_logout;

    @FXML
    private Label label_welcome;
    @FXML
    private Label label_fav_channel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Buton tetiklenmesinde neler olacağı
        button_logout.setOnAction(new EventHandler < ActionEvent > () {
            @Override
            public void handle(ActionEvent event) {
                Util.DbUtils.changeScene(event, "MainPage.fxml", "Log in",  null, null);
            }
        });

    }
    // DB bağlantısı ile Main ekranda isim bilgisi ve favori kanal gösterme
    public void setUserİnformation(String username, String favChannel){
       label_welcome.setText("Welcome" + username + "!");
       label_fav_channel.setText("Your favorite Youtube Channel: " + favChannel);
    }
  
}