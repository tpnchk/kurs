package com.example.kursova.controller;

import java.net.URL;
import java.util.ResourceBundle;
import com.example.kursova.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class SettingsSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button butSetingsBack;

    @FXML
    private Button butSwitchLogin;

    @FXML
    private Button butSwitchPassword;

    @FXML
    void initialize() {

       butSwitchLogin.setOnAction(Event -> {
           Main.SwitchScene("SwitchLogScene.fxml", butSwitchLogin);
       });

       butSwitchPassword.setOnAction(Event -> {
           Main.SwitchScene("SwitchPassScene.fxml", butSwitchPassword);
       });

       butSetingsBack.setOnAction(Event -> {
           Main.SwitchScene("MainScene.fxml", butSetingsBack);
       });

    }
}
