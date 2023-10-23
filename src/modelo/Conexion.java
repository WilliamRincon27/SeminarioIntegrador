package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexion {
    Connection con;
    String url = "jdbc:mysql://localhost:3306/variedadesyoli";
    String user = "root";
    String pass = "william27valenata";
    
    public Connection conectar(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
        return con;
    }
}