package com.example.kursova;

import com.example.kursova.db.Configs;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LogScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image("https://st2.depositphotos.com/2038163/11363/v/380/depositphotos_113637712-stock-illustration-moderm-minimalis-initial-logo-tc.jpg"));
        stage.setTitle("TaxCalculation");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }

    public static void SwitchScene(String str, Button but){
        but.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(str));

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setTitle("TaxCalculation");
        stage.setScene(new Scene(fxmlLoader.getRoot()));
        stage.getIcons().add(new Image("https://st2.depositphotos.com/2038163/11363/v/380/depositphotos_113637712-stock-illustration-moderm-minimalis-initial-logo-tc.jpg"));
        stage.setResizable(false);
        stage.show();
    };

    public static Connection getConnection() {
        try {
            Configs con = new Configs();
            Connection myCon = DriverManager.getConnection("jdbc:mysql://" + con.getDbHost() + ":" + con.getDbPort() + "/" + con.getDbName(), con.getDbUser(), con.getDbPass());
            return myCon;
        } catch (SQLException e) {
            return null;
        }
    }


}