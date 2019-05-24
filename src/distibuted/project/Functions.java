/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distibuted.project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nader
 */
public class Functions extends UnicastRemoteObject implements FunctionsInterface {
    DataInputStream is;
    DataOutputStream os;
    public Functions(DataInputStream is,DataOutputStream os)throws RemoteException{
        this.is=is;
        this.os=os;
    }
    @Override
    public String select(String s) throws RemoteException {
        try {
            Connection conn = DB.getconnection();
            PreparedStatement stmt;
            ResultSet rs;
            String sql = "select * from patent where id=" + s + ";";
            stmt = conn.prepareCall(sql);
            rs = stmt.executeQuery();
            String output = "";
            while (rs.next()) {
                //Retrieve by column name
                output += "Name: " + rs.getString("name");
                output += "\nAge: " + rs.getInt("age");
                output += "\nPhone Number: " + rs.getString("phnumber");
                output += "\nAddress: " + rs.getString("address");
                output += "\nStatus :" + rs.getString("Status");
                output += "\nSupervisor Dr. :" + rs.getString("supervisorDr");
                output += "\nBills :" + rs.getString("Bills");
            }
            return (output);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insert(String name, String address, String age, String ph, String status, String dr, String bills) throws RemoteException {
        try {
            Connection conn = DB.getconnection();
            PreparedStatement stmt;
            String sql = "INSERT INTO `patent`(`name`,`address`,`age`,`phnumber`,`Status`,`supervisorDr`,`Bills`) values(?,?,?,?,?,?,?);";
            try {
                stmt = conn.prepareCall(sql);
                stmt.setString(1, name);
                stmt.setString(2, address);
                stmt.setString(3, age);
                stmt.setString(4, ph);
                stmt.setString(5, status);
                stmt.setString(6, dr);
                stmt.setString(7, bills);
                stmt.execute();
            } catch (SQLException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String[] update_select(String s) throws RemoteException {
        String output[]=new String[7];
        try {
            Connection conn = DB.getconnection();
            PreparedStatement stmt;
            ResultSet rs;
            String sql = "select * from patent where id=" + s + ";";
            stmt = conn.prepareCall(sql);
            rs = stmt.executeQuery();
           
            while (rs.next()) {
                //Retrieve by column name
                output[0]= rs.getString("name");
                output[1]= String.valueOf(rs.getInt("age"));
                output[2]= rs.getString("phnumber");
                output[3]= rs.getString("address");
                output[4]= rs.getString("Status");
                output[5]= rs.getString("supervisorDr");
                output[6]= rs.getString("Bills");
            }
            return (output);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void update(String id,String name, String address, String age, String ph, String status, String dr, String bills) throws RemoteException {
        try {
            Connection conn = DB.getconnection();
            PreparedStatement stmt;
            String sql = "update `patent`set `name`=? ,`address`=? ,`age`=? ,`phnumber`=? ,`Status`=? ,`supervisorDr`=?,`Bills`=? where id=" + id + ";";
            try {
                stmt = conn.prepareCall(sql);
                stmt.setString(1, name);
                stmt.setString(2, address);
                stmt.setString(3, age);
                stmt.setString(4, ph);
                stmt.setString(5, status);
                stmt.setString(6, dr);
                stmt.setString(7, bills);
                stmt.execute();
            } catch (SQLException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
