/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conectionDB;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author StoomBassCow
 */
public class cls_Conection {

    private static Connection conn;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "admin12345";
    private static final String URL = "jdbc:mysql://localhost:3306/Palabras";

    public cls_Conection() {
        conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            if (conn != null) {

                System.out.println("Conexion exitosa");

            }

        } catch (ClassNotFoundException | SQLException e) {

            System.out.println("Conexion fallida" 
                    + "\n" + "El error de la conexion es: " + e.getMessage());

        }
    }
//Este metodo nos retorna la conexion

    public Connection getConnection() {

        return conn;
    }
//Con este metodo nos desconectamos de la base de datos

    public void desconectar() {
        conn = null;
        if (conn == null) {

            System.out.println("Conexion terminada");

        }
    }

}
