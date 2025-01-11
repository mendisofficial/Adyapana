/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Chathusha Mendis
 */
public class MySQL {
    private static final Dotenv dotenv = Dotenv.load();

    private static final String DB_NAME = dotenv.get("DB_NAME");;
    private static final String DB_URL = dotenv.get("DB_URL");
    private static final String DB_USERNAME =  dotenv.get("DB_USERNAME");
    private static final String DB_PASWORD = dotenv.get("DB_PASSWORD");

    public static Connection connection = null;

    public static void createConnection() throws Exception {
        if (connection == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_USERNAME, DB_PASWORD);
        }
    }

    public static ResultSet executeSearch(String query) throws Exception {
        createConnection();
        return connection.createStatement().executeQuery(query);
    }

    public static Integer executeIUD(String query) throws Exception {
        createConnection();
        return connection.createStatement().executeUpdate(query);
    }
    
    public static boolean validateUser(String username, String password) throws Exception {
        createConnection();
        String query = "SELECT * FROM Admin WHERE Username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String storedHash = resultSet.getString("Password");
            if (BCrypt.checkpw(password, storedHash)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            createConnection();
            if (connection != null && !connection.isClosed()) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
