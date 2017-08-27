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
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author StoomBassCow
 */
public class cls_Methods {

    String separateWord[];

    int tam = 0;
    String Type[];
    List myList = new ArrayList();

    private ResultSet RS;
    private PreparedStatement PS;
    private cls_Conection CN;

    public cls_Methods() {
        PS = null;
        CN = new cls_Conection();

    }

    /*Con este metodo separamos las palabras mediante un espacio " " y luego, 
    pasamos a agregar cada una de las palabras previamente separadas en un
    arreglo de tipo cadena, el cual nos servira para apilar cada una de estas
    palabras y poder compararlas con la base de datos.
     */
    public String getWord(String Word) {
        separateWord = Word.split(" ");
        /*for (int i = 0; i < separateWord.length; i++) {
            System.out.println("Posicion " + i + " la palabra es: " + separateWord[i]);
        }*/
        tam = separateWord.length;
        return "";
    }

    public String consultaSQL() {
        try {
            for (int i = 0; i < tam; i++) {
                String SQL_SELECT = "SELECT * FROM FullWords WHERE Palabra='" + separateWord[i] + "'";
                PS = CN.getConnection().prepareStatement(SQL_SELECT);
                RS = PS.executeQuery();
                while (RS.next()) {
                    myList.add(RS.getObject(2).toString());
                    //System.out.println(myList.get(i));
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

    public String SintagmaSelect() {
        int i = 0;     
        do {
            
            
            // para poder acceder a cada uno de los indices solo debes de poner 
            //myList.get(i).toString().equals("El tipo de dato que te regresa dependiendo del tipo que sea")
            //Nominal
            if (myList.get(i).toString().equals("Pronombre Posesivo Ateno")) {
                i++;
                if (myList.get(i).toString().equals("Objeto")) {
                    i++;
                    if (myList.get(i).toString().equals("Adjetivo")) {
                        System.out.println("Este es un sintagma nominal");
                        Type[i+2] = "SN";
                         break;
                    }  
                }
            }
            //Verbal
            if (myList.get(i).toString().equals("Pronombre Posesivo Ateno")) {
                i++;
                if (myList.get(i).toString().equals("Objeto")) {
                    i++;
                    if (myList.get(i).toString().equals("Adjetivo")) {
                        System.out.println("Este es un sintagma nominal");
                        Type[i+2] = "SN";
                         break;
                    }  
                }
            }
            //Verbal
            if (myList.get(i).toString().equals("Pronombre Posesivo Ateno")) {
                i++;
                if (myList.get(i).toString().equals("Objeto")) {
                    i++;
                    if (myList.get(i).toString().equals("Adjetivo")) {
                        System.out.println("Este es un sintagma nominal");
                        Type[i+2] = "SN";
                         break;
                    }  
                }
            }
        } while (true);
        return "";
    }
}
