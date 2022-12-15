package com.example.kursova.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.kursova.db.TaxsTable;
import com.example.kursova.Main;
import com.example.kursova.Tax;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class OperatingInTaxController implements Initializable {

    private boolean isSearch = false;
    private int x1;
    private int x2;

    public OperatingInTaxController() {

    }

    public OperatingInTaxController(int x1, int x2) {
        this.isSearch = true;
        this.x1 = x1;
        this.x2 = x2;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Tax, Integer> idtax;

    @FXML
    private TableColumn<Tax, Integer> Participant;

    @FXML
    private TableColumn<Tax, Integer> StartBalance;

    @FXML
    private TableColumn<Tax, Integer> Tax;

    @FXML
    private TableColumn<Tax, String> TypeTax;

    @FXML
    private TableColumn<Tax, String> User;

    @FXML
    private TableView<Tax> table;

    @FXML
    private Button butAddTax;

    @FXML
    private Button butDeleteTax;

    @FXML
    private Button butSearchSumma;

    @FXML
    private Button butTaxSceneBack;

    @FXML
    private Button butSearchTax;

    @FXML
    private TextField tfSumma;

    @FXML
    private Button butSwitchAccountTaxs;

    @FXML
    private Button butRefresh;

    ObservableList<Tax> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TaxsTable db = new TaxsTable();
        ResultSet result = db.vievTax(getLog());


        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                list.add(new Tax(result.getInt(1), result.getString(2), result.getString(3),
                        result.getInt(4), result.getInt(5), result.getInt(6)));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



        idtax.setCellValueFactory(new PropertyValueFactory<Tax, Integer>("idtax"));
        User.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser()));
        TypeTax.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTypeTax()));
        StartBalance.setCellValueFactory(new PropertyValueFactory<Tax, Integer>("StartBalance"));
        Participant.setCellValueFactory(new PropertyValueFactory<Tax, Integer>("Participant"));
        Tax.setCellValueFactory(new PropertyValueFactory<Tax, Integer>("Tax"));



        table.setItems(list);

        butAddTax.setOnAction(Event-> {
            Main.SwitchScene("AddTaxScene.fxml", butAddTax);
        });

        butSearchSumma.setOnAction(Event-> {
            Integer sum = 0;
            for (int i = 0; i < list.size(); i++){
                sum+=list.get(i).getTax();
            }

            tfSumma.setText(sum.toString());
        });

        butTaxSceneBack.setOnAction(Event -> {
            Main.SwitchScene("MainScene.fxml", butTaxSceneBack);
        });

        butDeleteTax.setOnAction(Event -> {
            int i = table.getSelectionModel().getSelectedItem().getIdtax();

            db.delTax(i);

            OperatingInTaxController controller = new OperatingInTaxController();
            butDeleteTax.getScene().getWindow().hide();
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

        butSwitchAccountTaxs.setOnAction(Event -> {
            Main.SwitchScene("LogScene.fxml", butSwitchAccountTaxs);
        });

        butRefresh.setOnAction(Event -> {
            OperatingInTaxController controller = new OperatingInTaxController();
            butRefresh.getScene().getWindow().hide();
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

}
