package com.example.kursova.db;

import com.example.kursova.Main;
import com.example.kursova.Tax;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class TaxsTable {
    public void delTax(int id){
        String delete = "DELETE FROM taxs WHERE idtax = " + id;

        try {
            Connection myCon = Main.getConnection();
            Statement myStmt = myCon.createStatement();
            myStmt.executeUpdate(delete);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearProfile(){

        String delete = "DELETE FROM taxs WHERE login_user = '" + getLog() + "'" ;

        try {
            Connection myCon = Main.getConnection();
            Statement myStmt = myCon.createStatement();
            myStmt.executeUpdate(delete);

        }catch (SQLSyntaxErrorException e){

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet vievTax(String login) {
        ResultSet resSet = null;

        String select = "SELECT * FROM taxs WHERE login_user =?";
        try {
            Connection myCon = Main.getConnection();

            PreparedStatement prSt = myCon.prepareStatement(select);
            prSt.setString(1, login);

            resSet = prSt.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resSet;

    }

    public void addTax(Tax tax){
        String insert = "INSERT INTO taxs " +
                " (login_user, typetax, summa, participant, tax)"
                + " VALUES ('" + tax.getUser() + "', '" + tax.getTypeTax() + "', '" + tax.getStartBalance() + "', " +
                tax.getParticipant() + ", " + tax.getTax() + ")";

        try {
            Connection myCon = Main.getConnection();

            Statement myStmt = myCon.createStatement();

            myStmt.executeUpdate(insert);

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
