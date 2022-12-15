package com.example.kursova.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.kursova.db.TaxsTable;
import com.example.kursova.Main;
import com.example.kursova.Tax;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AddTaxController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button butAceptAddTax;

    @FXML
    private Button butCancelAddTax;

    @FXML
    private ComboBox cbTypeTax;

    @FXML
    private TextField tfBalance;

    @FXML
    void initialize() {
        tfBalance.setStyle("-fx-background-color: white;");

        cbTypeTax.getItems().addAll("Податок з основного або додаткового місця роботи",
        "Податок з авторських винагород",
        "Податок з продажу майна",
        "Податок з отримання грошофих сум або майна",
        "Податок з отримання коштів з-за кордону",
        "Податок з пільг на дітей",
        "Податок з матеріальної допомоги");

        butCancelAddTax.setOnAction(Event -> {
            OperatingInTaxController controller = new OperatingInTaxController();
            butCancelAddTax.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("OperationInTaxsScene.fxml"));
            fxmlLoader.setController(controller);

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
        });

        butAceptAddTax.setOnAction(Event -> {

            if(!isNumber(tfBalance.getText())){
                tfBalance.setStyle("-fx-background-color: #EBC7D4;");
                return;
            }

            tfBalance.setStyle("-fx-background-color: whyte;");

            Integer balance = Integer.valueOf(tfBalance.getText());

            String choose = cbTypeTax.getSelectionModel().getSelectedItem().toString();

            Tax tax = searchTax(balance, choose);
            TaxsTable db = new TaxsTable();
            db.addTax(tax);

            OperatingInTaxController controler = new OperatingInTaxController();
            butAceptAddTax.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("OperationInTaxsScene.fxml"));
            fxmlLoader.setController(controler);

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
        });
    }

    private Tax searchTax(int balance, String choose){
        Tax tax = new Tax();
        int participant = 0;

        String str1 = "Податок з основного або додаткового місця роботи";
        String str2 = "Податок з авторських винагород";
        String str3 = "Податок з продажу майна";
        String str4 = "Податок з отримання грошофих сум або майна";
        String str5 = "Податок з отримання коштів з-за кордону";
        String str6 = "Податок з пільг на дітей";
        String str7 = "Податок з матеріальної допомоги";

        if(str1.equals(choose)){
            participant = 20;
        }
        if(str2.equals(choose)){
            participant = 10;
        }
        if(str3.equals(choose)){
            participant = 18;
        }
        if(str4.equals(choose)){
            participant = 15;
        }
        if(str5.equals(choose)){
            participant = 25;
        }
        if(str6.equals(choose)){
            participant = 8;
        }
        if(str7.equals(choose)){
            participant = 5;
        }

        tax.setTypeTax(choose);
        tax.setStartBalance(balance);
        tax.setUser(getLog());
        tax.setParticipant(participant);
        tax.setTax(balance / 100 * participant);

        return tax;
    }

    private String getLog() {
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

    private static boolean isNumber(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
