/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import conectionDB.cls_Conection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author StoomBassCow
 */
public class cls_Methods {

    String separateWord[];
    int tam = 0;

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
        try {

            separateWord = Word.split(" ");

            for (int i = 0; i < separateWord.length; i++) {
                System.out.println("Posicion " + i + " la palabra es: " + separateWord[i]);
            }
            tam = separateWord.length;

        } catch (Exception e) {

            System.out.println("El arrary esta vacio: " + e.getMessage());
        }

        return "";
    }

    public int count() {
        tam = separateWord.length;
        return tam;
    }

    public String consultaSQL() {
        try {
            for (int i = 0; i < count(); i++) {
                String SQL_SELECT = "SELECT * FROM FullWords WHERE Palabra='" + separateWord[i] + "'";
                PS = CN.getConnection().prepareStatement(SQL_SELECT);

                RS = PS.executeQuery();

                while (RS.next()) {

                    System.out.println(i + "\n"
                            + "La palabra es: " + RS.getString("Palabra") + "\n"
                            + "Tipo: " + RS.getString("SINGPLU") + "\n"
                            + "El genero es: " + RS.getString("Genero") + "\n"
                            + "Es un: " + RS.getString("Tipo"));

                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta " + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return "";
    }

    public String cleanArray() {

        for (int i = 0; i < count(); i++) {
            System.out.println(separateWord[i]);
        }

        return "";
    }

    public String sintagmaNominal() {

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
