package com.codecool.getpartner.inputhandler;

import com.codecool.getpartner.sql.ConnectingDB;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


public class UserAccountHandler {

    public void userAccountChanges(Map<String, String> userInput, String userId){
        try {
            if(!isUserIdInProfile(userId)) {
                ConnectingDB.executeQuery("INSERT INTO profile (userid) VALUES ('" + userId + "') ;"); // IDe a session userId kell
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (String input: userInput.keySet()) {
            if(userInput.get(input) != null){
                ConnectingDB.executeQuery("UPDATE profile SET " + input +" = '"+ userInput.get(input) + "' WHERE userid = " + userId +" ;" /*Ide kell leirni a userID*/);
            }
        }
    }

    public boolean isUserIdInProfile(String userId) throws SQLException {
        ResultSet result = ConnectingDB.executeQuery("SELECT userid FROM profile WHERE userid = " + userId);
        while(result.next()){
            if(result.getString("id") != null){
                return true;
            }
        }
        return false;
    }

    public void fileUploader(Part filePart) throws IOException {
        String fileName = filePart.getSubmittedFileName();
        InputStream is = filePart.getInputStream();
        System.out.println(fileName);

        Files.copy(is, Paths.get("/Users/danielszakacs/Documents/OOP/getPartner/src/main/webapp/static/img/" + fileName),
                StandardCopyOption.REPLACE_EXISTING);
    }

    public boolean isUserUploadFileAImage(Part file){
        if(file.getContentType().contains("image")){
            return true;
        }
        return false;
    }
}
