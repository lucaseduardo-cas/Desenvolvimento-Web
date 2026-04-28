/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static Connection connection = null;

    public static Connection getConnection() {
        
        if (connection != null)
            return connection;
        else {
            try {
                
                String user = "postgres";
                String password = "100916";
                
                Class.forName("org.postgresql.Driver");// Para quem for usar Postgres
                
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bdueg202601",user, password);
                
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            return connection;
        }

    }
}