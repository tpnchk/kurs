package com.example.kursova.db;


import com.example.kursova.Main;
import com.example.kursova.User;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class UserTable {

    public int regUser(User user) {

        String insert = "INSERT INTO USERS " +
                " (login, password)"
                + " VALUES ('" + user.getLogin() + "', '" + user.getPassword() + "')";

        Connection myCon = null;
        try {
            myCon = Main.getConnection();
            Statement myStmt = myCon.createStatement();

            myStmt.executeUpdate(insert);

        }catch (SQLIntegrityConstraintViolationException e){
            return 1;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ResultSet getUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM users WHERE login =? AND password =?";
        try {
            Connection myCon = Main.getConnection();

            PreparedStatement prSt = myCon.prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resSet;

    }

    public void SwitchLogin(String loginNow, String loginNew){
        String UPDATE = "UPDATE USERS " +
                " SET login = '" + loginNew +
                "' WHERE login = '" + loginNow + "'";

        try {
            Connection myCon = Main.getConnection();

            Statement myStmt = myCon.createStatement();

            myStmt.executeUpdate(UPDATE);

        } catch (SQLIntegrityConstraintViolationException q){

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SwitchPassword(String login, String passwordNew){
        String UPDATE = "UPDATE USERS " +
                " SET password = '" + passwordNew +
                "' WHERE login = '" + login + "'";

        try {
            Connection myCon = Main.getConnection();
            Statement myStmt = myCon.createStatement();
            myStmt.executeUpdate(UPDATE);

        } catch (SQLIntegrityConstraintViolationException q){

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet CountUserLog(String newLog){
        ResultSet resSet = null;

        String select = "SELECT COUNT(*) FROM users WHERE login =?";
        try {
            Connection myCon =Main.getConnection();

            PreparedStatement prSt = myCon.prepareStatement(select);
            prSt.setString(1, newLog);

            resSet = prSt.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resSet;
    }

}




