package com.codecool.getpartner.inputhandler;

import com.codecool.getpartner.sql.ConnectingDB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Registration {

    public Registration() {
    }

    private boolean isUserRegistered(String email) throws SQLException {

        ResultSet result = ConnectingDB.executeQuery("SELECT * FROM users");
        while(result.next()){
            if (result.getString("email").equals(email)){
                return false;
            }
        }
        return true;
    }

    public void registing(String email,String password) throws SQLException {
        if (isUserRegistered(email)){
            ConnectingDB.executeQuery("INSERT INTO  users (email,password) VALUES ('" + email + "', '" + password + "')");
        }else {
            System.out.print("This email already registered");
        }

    }

}
