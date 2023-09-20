package com.wipro.raemisclient.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOConnection {
    private static final String CONNECTION_URL =
            "jdbc:sqlserver://miq.database.windows.net:1433;"
                    + "database=VAMMS;"
                    + "user=vamuser;"
                    + "password=aug@2023;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;"
                    + "loginTimeout=30;";

    private static Connection connection = null;

    public static Connection create_connection()  {
        try {
        	synchronized (DAOConnection.class) {
        		if (connection == null) {
                    connection = DriverManager.getConnection(CONNECTION_URL);
                    System.out.println("VAAMS Database connection created successfully");

                }
			}
        } catch (SQLException e) {
            System.out.println("Can't create database connection");
            throw new RuntimeException(e);
        }
        return connection;
    }
}
