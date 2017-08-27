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
                    myList.add(RS.getObject(4).toString());
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
           
            //Sintagma Nominal
                if (myList.get(i).toString().equals("de")) {
                /* el de = determinante de nominal puede ser un articulo, 
                demostraditvo, posesivo, numeral, Indefinidoo exclamativo*/
                i++;
                if (myList.get(i).toString().equals("nucleo")) {
                    i++;
                     /* el nucleo de nominal puede ser un sustantivo, pronombre,
                     o una palabra sustantivada*/
                    if (myList.get(i).toString().equals("complemento")) {
                        /* el complemento de nominal puede ser un adjetivo 
                        calificativo, color, etc incluso otro sintagma nominal*/
                        System.out.println("Este es un sintagma nominal");
                        Type[i+2] = "SN";
                         break;
                    }  
                }
            }
                
                 // automata con 2 Sintagmas Nominal Nominales
                if (myList.get(i).toString().equals("de")) {
                /* el de = determinante de nominal puede ser un articulo, 
                demostraditvo, posesivo, numeral, Indefinidoo exclamativo*/
                i++;
                if (myList.get(i).toString().equals("nu")) {
                    /* el nu = nucleo de nominal puede ser un sustantivo, pronombre,
                    o una palabra sustantivada*/
                     System.out.println("hasta aqui es un sintagma nominal");
                    i++;
                    if (myList.get(i).toString().equals("verbo")) {
                    
                    i++;
                    if (myList.get(i).toString().equals("cuantificador")) {
                        /* el cuantificador de nominal puede ser un adjetivo 
                        calificativo, color, etc incluso puede comenzar otro 
                        sintagma nominal*/
                     System.out.println("este cuantificador da inicio a otro SN");
                    i++;
                    if (myList.get(i).toString().equals("nucleo")) {
                        /* el nucleo de nominal puede ser un sustantivo, pronombre,
                    o una palabra sustantivada*/
                        System.out.println("y a partir del vervo tenemos un sintagma verbal");
                        Type[i+4] = "SN";
                         break;
                    }  
                }
               }
             }
            }
              
                
//-------------------------Sintagma adverbial --------------------------------------------------
                if (myList.get(i).toString().equals("cuantificador")) {
                /* el cuantificador es un adberbio de cantidad que modifica el nucleo*/
                i++;
                if (myList.get(i).toString().equals("nucleo")) {
                    i++;
                     /* el nucleo del adjetival es un adverbio*/
                    if (myList.get(i).toString().equals("complemento")) {
                        /* el complemento del adverbial completa el significado del Sin Adver*/
                        System.out.println("Este es un sintagma adverbial");
                        Type[i+2] = "SN";
                         break;
                    }  
                }
            }
                
                 // automata con Sintagmas adverbiales
                if (myList.get(i).toString().equals("verbo")) {
                i++;
                if (myList.get(i).toString().equals("Determinante")) {
                     
                    i++;
                    if (myList.get(i).toString().equals("nucleo")) {
                        /* el nucleo del adjetival puede ser un adverbio o un sujeto*/
                      System.out.println("aqui el determinante y el nucleo funcionan como SN");
                    i++;
                    if (myList.get(i).toString().equals("cuantificador")) {
                       
                     System.out.println("este cuantificador da inicio señala el inicio de SAdv");
                    i++;
                    if (myList.get(i).toString().equals("adverbio")) {
                        /* el nucleo de nominal puede ser un sustantivo, pronombre,
                    o una palabra sustantivada*/
                        System.out.println("el automata se podria considerar un sintagma verbal");
                        Type[i+4] = "SV";
                         break;
                    }  
                }
               }
             }
            }
            
                //otro automata con adverbial
                if (myList.get(i).toString().equals("sujeto")) {
                /* eno se porque pero la primera palabra lo toma como SN*/
                i++;
                if (myList.get(i).toString().equals("Verbo")) {
                    System.out.println("a partir del verbo es un sintagma verbañ");
                    i++;
                    if (myList.get(i).toString().equals("adverbio")) {
                    System.out.println("a partir del verbo es un sintagma verbañ");
                    /* dentro del sintagma verbal entra otro sintagma adverbial*/
                    i++;
                    if (myList.get(i).toString().equals("Sprepocicional")) {
                        
                        System.out.println("se puede concatenar un sintagma prepocicional");
                        Type[i+2] = "SAdver";
                         break;
                    }  
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
