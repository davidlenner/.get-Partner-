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
        ResultSet result = ConnectingDB.executeQuery("SELECT * FROM profile WHERE username IS NOT NULL" );
        castResultsetToList(result,userData);
        return userData;
    }

    public List getUsersByRoom(String room) throws SQLException {
        List usersByRoom = new ArrayList();
        ResultSet result = ConnectingDB.executeQuery("SELECT * FROM profile WHERE room = " + room);
        castResultsetToList(result,usersByRoom);
        return usersByRoom;
    }

    public void castResultsetToList(ResultSet result,List list) throws SQLException {
        while (result.next()) {
            Map<String, String> user = new HashMap<>();
            user.put("userid", result.getString("userid"));
            user.put("username", result.getString("username"));
            user.put("gender", result.getString("gender"));
            user.put("age", result.getString("age"));
            user.put("room", result.getString("room"));
            user.put("picture", result.getString("picture"));
            user.put("favoritelanguage", result.getString("favoritelanguage"));
            user.put("bio", result.getString("bio"));
            list.add(user);
        }
    }
}
