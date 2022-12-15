package com.example.kursova.controller;

import com.example.kursova.db.UserTable;
import com.example.kursova.Main;
import com.example.kursova.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegController {

    @FXML
    private Button butReg;

    @FXML
    private Text textError2;

    @FXML
    private TextField tfLogin;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private PasswordField tfPasword2;

    @FXML
    void initialize() {
        textError2.setText(" ");

        butReg.setOnAction(Event -> {
            regUser();
        });
    }

    private void regUser() {

        UserTable db = new UserTable();
        User user = new User(tfLogin.getText(), tfPassword.getText());
        if (tfLogin.getText().length() == 0) {
            textError2.setText("Ви не ввели логін!");
            return;
        }
        if (tfPassword.getText().length() == 0) {
            textError2.setText("Ви не ввели пароль!");
            return;
        }
        if (tfPasword2.getText().length() == 0) {
            textError2.setText("Ви не підтвердили свій пароль");
            return;
        }
        if (!tfPasword2.getText().equals(tfPassword.getText())) {
            textError2.setText("Паролі не співпадають!");
            tfPassword.clear();
            tfPasword2.clear();
            return;
        }
        if (db.regUser(user) == 1) {
            textError2.setText("Даний логін вже зареєстрований");
            return;
        }
        Main.SwitchScene("LogScene.fxml", butReg);
    }


}
