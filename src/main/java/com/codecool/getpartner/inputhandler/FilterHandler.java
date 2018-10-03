package com.codecool.getpartner.inputhandler;

import com.codecool.getpartner.sql.ConnectingDB;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterHandler {

    public List getAlluserDataFromDB() throws SQLException {
        List userData = new ArrayList();
        ResultSet result = ConnectingDB.executeQuery("SELECT * FROM profile" );
        castResultsetToList(result,userData);
        return userData;
    }

    public List getUsersByRoom(String room) throws SQLException {
        List usersByRoom = new ArrayList();
        ResultSet result = ConnectingDB.executeQuery("SELECT * FROM profile WHERE room = " + room);
        castResultsetToList(result,usersByRoom);
        return usersByRoom;
    }

    public List getUsersByGender(String gender) throws SQLException {
        List usersByGender = new ArrayList();
        ResultSet result = ConnectingDB.executeQuery("SELECT * FROM profile WHERE gender = " + gender);
        castResultsetToList(result,usersByGender);
        return usersByGender;
    }

    public List getUsersByAge(int minimumAge,int maximumAge) throws SQLException {
        List usersByAge = new ArrayList();
        ResultSet result = ConnectingDB.executeQuery("SELECT * FROM profile WHERE age BETWEEN " + minimumAge + " AND " + maximumAge);
        castResultsetToList(result,usersByAge);
        return usersByAge;
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
