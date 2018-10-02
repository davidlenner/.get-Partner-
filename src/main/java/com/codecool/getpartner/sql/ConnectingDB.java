package com.codecool.getpartner.sql;
import java.sql.*;

public class ConnectingDB {
    private static final String url = "jdbc:postgresql://localhost:5432/blackmarket";
    private static final String user = "";
    private static final String password = "";

    public static ResultSet executeQuery(String query) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            Statement myst = conn.createStatement();
            ResultSet rs = myst.executeQuery(query);

            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();

            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return null;
    }

}