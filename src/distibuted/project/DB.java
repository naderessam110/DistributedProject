/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distibuted.project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DB {
    static Connection conn = null;
    static String url       = "jdbc:mysql://localhost:3306/patient";
    static String user      = "root";
    static String password  = "";
    public static Connection getconnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, password);
        return conn;
    }  
}
