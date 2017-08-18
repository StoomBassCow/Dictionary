/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

/**
 *
 * @author StoomBassCow
 */
public class Methods {

    String finalWord = "";
    String separateWord[];

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

        return " ";
    }

    public String sintagmaNominal() {

        /*
        String sSQL = "SELECT nombre, edad, profesión, ciudad "
                    + "FROM personas "
                    + "WHERE ciudad = '" + sCiudad 
                    + "'AND edad BETWEEN " + sEdad1 + " AND " + sEdad1 + ";"
        
         */
        // = conn.prepareStatement("SELECT nombre, edad, profesion, ciudad FROM Personas WHERE edad > ? AND ciudad = ?");
        return "";
    }

}
