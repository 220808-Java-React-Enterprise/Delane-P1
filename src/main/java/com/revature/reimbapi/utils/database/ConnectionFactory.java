package com.revature.reimbapi.utils.database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/* Singleton design pattern */
public class ConnectionFactory {
    private static ConnectionFactory connectionFactory;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private final Properties props = new Properties();

    private ConnectionFactory() {
        try {
            System.out.println(System.getProperty("user.dir"));
            //props.load((new FileReader("src/main/resources/db.properties")));
            props.load(new FileReader("C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\db.properties"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) connectionFactory = new ConnectionFactory();
        return connectionFactory;
    }

    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
        if (conn == null) throw new RuntimeException("Could not establish connection with the database!");
        return conn;
    }
}
