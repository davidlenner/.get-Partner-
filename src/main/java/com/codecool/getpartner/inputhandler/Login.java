package com.codecool.getpartner.inputhandler;

import com.codecool.getpartner.sql.ConnectingDB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    public Login() {
    }

    public boolean checkEmailAndPassword(String email, String password) throws SQLException {

        ResultSet result = ConnectingDB.executeQuery("SELECT * FROM users");
        while(result.next()){
            if (result.getString("email").equals(email) && result.getString("password").equals(password)){
                return true;
            }
        }
        return false;
    }

    public String getIdByEmail(String email) throws SQLException {
        ResultSet result = ConnectingDB.executeQuery("SELECT * FROM users");
        while(result.next()){
            if (result.getString("email").equals(email)){
                String id = result.getString("id");
                return id;
            }
        }
        return null;
    }



}
