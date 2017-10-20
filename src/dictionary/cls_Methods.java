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
                        Type[i + 2] = "SN";
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
                                Type[i + 4] = "SN";
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
                    /* el nucleo del advebial es un adverbio*/
                    if (myList.get(i).toString().equals("complemento")) {
                        /* el complemento del adverbial completa el significado del Sin Adver*/
                        System.out.println("Este es un sintagma adverbial");
                        Type[i + 2] = "SN";
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
                        /* el nucleo del adverbial puede ser un adverbio, sujeto o sustantivo*/
                        System.out.println("aqui el determinante y el nucleo funcionan como SN");
                        i++;
                        if (myList.get(i).toString().equals("cuantificador")) {

                            System.out.println("este cuantificador da inicio señala el inicio de SAdv");
                            i++;
                            if (myList.get(i).toString().equals("adverbio")) {
                                /* el nucleo de nominal puede ser un sustantivo, pronombre,
                    o una palabra sustantivada*/
                                System.out.println("el automata se podria considerar un sintagma verbal");
                                Type[i + 4] = "SV";
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
                        if (myList.get(i).toString().equals("Sprepo")) {

                            System.out.println("se puede concatenar un sintagma prepocicional");
                            Type[i + 3] = "SAdver";
                            break;
                        }
                    }
                }
            }

            //-------------------------Sintagma adjetival --------------------------------------------------
            if (myList.get(i).toString().equals("cuantificador")) {
                /* el cuantificador es un adberbio de cantidad que modifica el sintagma adjetival*/
                i++;
                if (myList.get(i).toString().equals("nucleo" /* adjetivo*/)) {
                    /* el nucleo del adjetival es un adjetivo*/
                    i++;
                    if (myList.get(i).toString().equals("complemento del adjetivo")) {
                        /* complementa el significado del nucleo*/
                        System.out.println("Este es un sintagma adjetival");
                        Type[i + 2] = "SAdj";
                        break;
                    }
                }
            }
            // ---------------------------automata con Sintagmas adjetivales
            if (myList.get(i).toString().equals("sujeto")) {
                /*el sujeto es el nucleo de un sintagma nominal que esta antes 
                de llegar al adjetival, esta unica palabra puede considerarse
                sintagma nominal*/
                i++;
                if (myList.get(i).toString().equals("verbo")) {
                    // a partir del verbo se convierte en un sintagma verbal
                    //con otros mas dentro de si mismo
                    System.out.println("a partir del verbo es un sintagma verbal");
                    i++;
                    if (myList.get(i).toString().equals("determinante")) {
                        //este determinante nos indica otro sintagma nominal de complemento directo
                        System.out.println("y a partir de el determinante funcionan como SN");
                        i++;
                        if (myList.get(i).toString().equals("sustantivo")) {
                            i++;
                            if (myList.get(i).toString().equals("nucleo")) {
                                //el nucleo en este caso es un adjetivo y por lo tanto se considera como sintajma adjetival
                                System.out.println("la ultima palabra en este caso lo conbierte a SAdj");
                                Type[i + 4] = "SV";
                                break;
                            }
                        }
                    }
                }
            }

            // ---------------------------otro automata con Sintagmas adjetivales
            if (myList.get(i).toString().equals("sujeto")) {
                i++;
                if (myList.get(i).toString().equals("verbo")) {
                    // a partir del verbo se convierte en un sintagma verbal
                    //con otros mas dentro de si mismo
                    System.out.println("a partir del verbo es un sintagma verbal");
                    i++;
                    if (myList.get(i).toString().equals("cuantificador")) {
                        //a partir del cuantificador se considera un sintagma adjetival
                        System.out.println("y a partir de el determinante funcionan como SN");
                        i++;
                        if (myList.get(i).toString().equals("nucleo")) {
                            //el nucleo es un adjetivo

                            System.out.println("la ultima palabra en este caso lo determina como Sintagma Adj");
                            Type[i + 3] = "SAdj";
                            break;
                        }
                    }
                }
            }

            //-------------------------Sintagma preposicional --------------------------------------------------
            if (myList.get(i).toString().equals("preposicion")) {
                /* la preposicion es el enlace a un termino que complemente la oracion*/
                i++;
                if (myList.get(i).toString().equals("termino")) {
                    /* es el complemento de la preposicion y puede utilizar cualquira de los 
                    principales sintagmas que hemos visto*/

                    System.out.println("Este es un sintagma preposicional");
                    Type[i + 2] = "SAdj";
                    break;
                }
            }

            // ---------------------------automata con Sintagma preposicional
            if (myList.get(i).toString().equals("verbo")) {
                i++;
                if (myList.get(i).toString().equals("preposicion")) {
                    //la prepocision es un enlace
                    System.out.println("a partir del verbo es un sintagma verbal");
                    i++;
                    if (myList.get(i).toString().equals("termino")) {
                        //el termino = sustantivo, sujeto, ect osea que puede ser algun otro sintagma el que continue la oracion
                        Type[i + 2] = "SPrepo";
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
                        Type[i + 2] = "SN";
                        break;
                    }
                }
            }
        } while (true);
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
