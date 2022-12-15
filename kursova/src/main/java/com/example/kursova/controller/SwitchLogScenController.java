package com.example.kursova.controller;

import java.io.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import com.example.kursova.db.UserTable;
import com.example.kursova.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SwitchLogScenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button butAceptSwitchLogin;

    @FXML
    private Button butSwitchLoginBack;

    @FXML
    private TextField tfNewLogin;

    @FXML
    private TextField tfNowLogin;

    @FXML
    private Text textSwitchLogError;

    @FXML
    void initialize() {
        textSwitchLogError.setText("");

        butAceptSwitchLogin.setOnAction(Event -> {
            String log = getLogin();

            if (log.equals(tfNowLogin.getText())) {
                UserTable db = new UserTable();
                ResultSet result = db.CountUserLog(tfNewLogin.getText());
                int count = 1;
                try {
                    result.next();
                    count = result.getInt(1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(count == 0) {
                    db.SwitchLogin(log, tfNewLogin.getText());
                    Main.SwitchScene("LogScene.fxml", butAceptSwitchLogin);
                }
                else{
                    textSwitchLogError.setText("Новий логін вже зайнятий");
                }
            }else {
                textSwitchLogError.setText("Ви ввели хибний логін");
            }
        });

        butSwitchLoginBack.setOnAction(Event -> {
            Main.SwitchScene("SetingsScene.fxml", butSwitchLoginBack);
        });
    }

    private String getLogin() {
        try {
            FileInputStream fstream = new FileInputStream("C:\\data\\userData");
            BufferedReader infile = new BufferedReader(new InputStreamReader(
                    fstream));
            String data = new String();
            if ((data = infile.readLine()) != null) {
                return data;
            }
        } catch (IOException e) {
            // Error
        }
        return "";
    }


}
