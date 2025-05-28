package com.ortega.webapp.servlet.swfe.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class  ConnectionBd {

    private static String url = "jdbc:mysql://localhost:3306/swfe?serverTimezone=UTC";
    private static String username = "usuario_administrador";
    private static String password = "j/Hs9=9jjj.aUIDF";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
