package test_connections.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    public static Connection getConnection( String properties_path) throws ClassNotFoundException{
        Connection connection = null;
        InputStream inputStream = null;
        try {
            Properties properties = new Properties();
            inputStream = new FileInputStream(properties_path);
            properties.load(inputStream);
            System.out.println(properties.getProperty("url"));
            System.out.println(properties.getProperty("user"));
            System.out.println(properties.getProperty("password"));

            //Class.forName("com.mysql.test_connections.jdbc.Driver"); ///?useSSL=false
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        Connection temp = connection;
        if (connection != null) {
            try {
                temp.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
