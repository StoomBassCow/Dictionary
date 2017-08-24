/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjemploSintagma;

/**
 *
 * @author Gera Rodriguez
 */
public class AutomatasExample {

    int cont;
    char [] car;
    public static void main(String[] args) {
       AutomatasExample aut = new AutomatasExample();
       String cadena = "123";//1= Articulo,Demos,Pos 2= Preposicion 3=AdverbioCantidad
       aut.car = cadena.toCharArray();
       aut.inicio();
    }
    public void inicio(){
        cont = 0;
        if(cont<car.length){
            if(car[cont]=='1'){
            System.out.println("Sintagma Nominal");    
            cont++;
            SinNom();
        } else if (car[cont]=='2'){
        cont ++;
                System.out.println("Sintagma Preposicional");
        SinPre();        
        }
            else if (car[cont]=='3'){
        cont ++;
                System.out.println("Sintagma Adjetival");
        SinAdj();        
        }
            
         
        }
        
    }
    
    /*---------------------inicio sintagma nominal----------------------------*/
    
    public void SinNom(){
        System.out.println("Este sintagma es un nominal");
         if(cont<car.length){
            System.out.println("determinante"); //determinante = articulo, demostartivo, posesivo,
            if(car[cont]=='1'){                 //numeral, indefinido
            cont++;
            SiNom1();
        } else if (car[cont]=='0'){
        cont ++;
                System.out.println("Aqui redirecciona a otro sintagma");
        }
        }
    }
    public void SiNom1(){
        if(cont<car.length){
            if(car[cont]!='2'){
            cont++;
            qAdvError();
        } else if (car[cont]=='2'){
        cont ++;
        SiNom2();
        System.out.println("pronombre o sustantivo");
        }
        }
        
    }
    public void SiNom2(){
        if(cont<car.length){
            if(car[cont]!='3'){
            cont++;
            qAdvError();
        } else if (car[cont]=='3'){
        cont ++;
         System.out.println("Preposicion \n Sintagma Adjetival");
        }
        }
        
        
    }
    
    /*-----------------------fin sintagma nominal-----------------------------*/
    
    /*---------------------inicio sintagma prepocisional----------------------*/
    
    public void SinPre(){
        System.out.println("Esta en un prepocisional");
    }
    
    
    /*-----------------------fin sintagma prepocisional-----------------------*/
    
     /*--------------------inicio sintagma adjetival--------------------------*/
    public void SinAdj(){
        if(cont<car.length){
            System.out.println("Adverbio de Cantidad");
            if(car[cont]=='1'){
            cont++;
            qAdv1();
        } else if (car[cont]=='0'){
        cont ++;
                System.out.println("Aqui redirecciona a otro sintagma");
        }
        }
    }
    public void qAdv1(){
        if(cont<car.length){
            if(car[cont]!='2'){
            cont++;
            qAdvError();
        } else if (car[cont]=='2'){
        cont ++;
        qAdv2();
        System.out.println("Adjetivo");
        }
        }
        
    }
    public void qAdv2(){
        if(cont<car.length){
            if(car[cont]!='3'){
            cont++;
            qAdvError();
        } else if (car[cont]=='3'){
        cont ++;
         System.out.println("Preposicion \n Sintagma Adjetival");
        }
        }
    }
    
     /*--------------------fin sintagma adjetival--------------------------*/
    
    
    public void qAdvError(){
        System.out.println("Error en la oración");
    }
    
    public void SinAdver(){
    }

}
