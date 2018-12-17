/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquetelogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mañanas
 */
public class AccesoBD {
    static String usuario="root";
    static String password="";
    static String url="jdbc:mysql://localhost:3306/bd_login?serverTimezone=UTC";

    static boolean verificarUsuario(String usr, String pwd) {
        //Forzar SQL injection
        String query="SELECT * FROM t_usuarios WHERE usuario=? AND password=?";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection c=DriverManager.getConnection(url, usuario, password);
            PreparedStatement stmt=c.prepareStatement(query);
            int hash_pwd=pwd.hashCode();
            
            stmt.setString(1, usr);
            stmt.setInt(2, hash_pwd);
            ResultSet datos=stmt.executeQuery();
            if(datos.next())
            {
                return true;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    static int insertarUsuario(String nombre, String usr, String pwd) {
        //0-Todo bien; 1-Usuario repe; 2-Fallo conexion o algo así
         try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c=DriverManager.getConnection(url, usuario, password);
            int hash_pwd=pwd.hashCode();
            String query="SELECT * FROM t_usuarios WHERE usuario=?";
            PreparedStatement stmt=c.prepareStatement(query);
            stmt.setString(1, usr);
            ResultSet datos=stmt.executeQuery();
            if (datos.next())
                    {
                        return 1;
                    }
            //Ahora puedo insertar
            String query_insert="INSERT INTO t_usuarios VALUES(?, ?, ?)";
            stmt=c.prepareStatement(query_insert);
            stmt.setString(1, nombre);
            stmt.setString(2, usr);
            stmt.setInt(3, hash_pwd);
            stmt.execute();
         }
         catch(Exception e)
         {
             return 2;
         }
        return 0;
    }
}
