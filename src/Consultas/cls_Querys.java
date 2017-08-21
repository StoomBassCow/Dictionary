/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;

import conectionDB.cls_Conection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author StoomBassCow
 */
public class cls_Querys {
    //private final String SELECT_SQL = "SELECT * FROM palabras WHERE palabra LIKE'%\"+huyen+\"%'"; 
    private final String SELECT_SQL = "SELECT * FROM palabras"; 
    private ResultSet RS;
    private PreparedStatement PS;
    private cls_Conection CN;
    
    public cls_Querys (){
        PS = null;
        CN = new cls_Conection();
    }
    
    
    
    public void getDatos(){
        
        try {
            
            PS = CN.getConnection().prepareStatement(SELECT_SQL);
            RS = PS.executeQuery();
            int i = 1;
            while(RS.next()){
         
                System.out.println("\n" + i +" "+ RS.getString("palabra"));
                i++;
            }
            
            
            
        } catch (SQLException e) {
            System.out.println("Error en la consulta " + e.getMessage());
        }finally{
            PS = null;
            CN.desconectar();
        }
        
        
    }
    
            
}
