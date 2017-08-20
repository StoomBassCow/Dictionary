/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;

import conectionDB.cls_Conection;
import java.sql.PreparedStatement;

/**
 *
 * @author StoomBassCow
 */
public class cls_Querys {
    
    private final String SELECT_SQL = "";
    private PreparedStatement PS;
    private cls_Conection CN;
    
    public cls_Querys (){
        PS = null;
        CN = new cls_Conection();
    }
}
