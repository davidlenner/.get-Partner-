package com.codecool.getpartner.inputhandler;

import com.codecool.getpartner.sql.ConnectingDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterHandler {

    public List getAlluserDataFromDB() throws SQLException {
        List userData = new ArrayList();
        ResultSet restult = ConnectingDB.executeQuery("SELECT * FROM profile" );
        while(restult.next()){
            Map<String, String> user = new HashMap<>();
            user.put("userid", restult.getString("userid"));
            user.put("username", restult.getString("username"));
            user.put("gender", restult.getString("gender"));
            user.put("age", restult.getString("age"));
            user.put("room", restult.getString("room"));
            user.put("picture", restult.getString("picture"));
            user.put("favoritelanguage", restult.getString("favoritelanguage"));
            user.put("bio", restult.getString("bio"));
            userData.add(user);
        }
        return userData;
    }
}
