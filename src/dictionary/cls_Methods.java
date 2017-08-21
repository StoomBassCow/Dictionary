/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import Consultas.cls_Querys;
import conectionDB.cls_Conection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author StoomBassCow
 */
public class cls_Methods {

    String finalWord = "";
    String separateWord[];

    private ResultSet RS;
    private PreparedStatement PS;
    private cls_Conection CN;

    public cls_Methods() {
        PS = null;
        CN = new cls_Conection();

    }

    /*Con este metodo separamos las palabras mediante un espacio " " y luego, 
    pasamos a agregar cada una de las palabras previamente separadas en un
    arreglo de tipo cadema, el cual nos servira para apilar cada una de estas
    palabras y poder compararlas con la base de datos.
     */
    public String getWord(String Word) {

        separateWord = Word.split(" ");

        for (int i = 0; i < separateWord.length; i++) {
            System.out.println("Posicion " + i + " la palabra es: " + separateWord[i]);
        }
        System.out.println(separateWord[0]);
        return " ";
    }
    private final String SELECT_SQL = "SELECT * FROM palabras WHERE Palabra ='"+separateWord+"'";

    public String sintagmaNominal() {

        try {
            //String SQL_SELECT = "SELECT * FROM palabras WHERE Palabra='" + separateWord[0]+"'";
            PS = CN.getConnection().prepareStatement(SELECT_SQL);
            RS = PS.executeQuery();

            int i = 1;
            while (RS.next()) {

                System.out.println(i + "\n"
                        + "La palabra es: " + RS.getString("Palabra") + "\n"
                        + "Tipo: " + RS.getString("SINGPLU") + "\n"
                        + "El genero es: " + RS.getString("Genero") + "\n"
                        + "Es un: " + RS.getString("Tipo"));
                i++;
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta " + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }

        return "";
    }

    public String sintagmaPrepocicional() {

        return "";
    }

    public String sintagmaAdjetival() {

        return "";
    }

    public String sintagmaAdverbial() {

        return "";
    }

    public String sintagmaVerbal() {

        return "";
    }

}
