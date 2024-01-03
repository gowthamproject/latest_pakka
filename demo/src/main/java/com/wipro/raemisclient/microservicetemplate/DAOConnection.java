package com.wipro.raemisclient.microservicetemplate;

import java.sql.Connection;

public class DAOConnection {

    private static Connection connection = null;

    public static Connection create_connection()  {
        return connection;
    }
}
