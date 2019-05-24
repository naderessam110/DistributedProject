/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distibuted.project;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Statement;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
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
public class ClientThread extends Thread{
    Socket socket;
    DataOutputStream os;
    DataInputStream is;
    Registry r;
    
    
    public ClientThread(Socket s,Registry r){
        this.socket=s;
        this.r=r;
    }
    @Override
    public void run() {  
        try {  
            Connection conn=DB.getconnection();
            PreparedStatement stmt;
            ResultSet rs;
           
            
            try {
                while (true){
                    is=new DataInputStream(socket.getInputStream());
                    os=new DataOutputStream(socket.getOutputStream());
                    
                    //Registry r= java.rmi.registry.LocateRegistry.getRegistry(1089);
                    r.bind("nader", new Functions(is,os));
                    /*String ch=is.readUTF();
                    if(ch.equals("1")){
                        String sql ="select * from patent where id="+is.readUTF()+";";
                        stmt=conn.prepareCall(sql);
                        rs = stmt.executeQuery();
                        String output="";
                        while(rs.next()){
                            //Retrieve by column name
                            output+="Name: "+rs.getString("name");
                            output+="\nAge: "+rs.getInt("age");
                            output+="\nPhone Number: "+rs.getString("phnumber");
                            output+="\nAddress: "+rs.getString("address");
                            output+="\nStatus :"+rs.getString("Status");
                            output+="\nSupervisor Dr. :"+rs.getString("supervisorDr");
                            output+="\nBills :"+rs.getString("Bills");
                         }
                        os.writeUTF(output);
                    }
                    if(ch.equals("2")){
                        conn=DB.getconnection();
                        String sql="INSERT INTO `patent`(`name`,`address`,`age`,`phnumber`,`Status`,`supervisorDr`,`Bills`) values(?,?,?,?,?,?,?);";    
                        try {
                            stmt=conn.prepareCall(sql);
                            stmt.setString(1,is.readUTF());
                            stmt.setString(2,is.readUTF());
                            stmt.setString(3,is.readUTF());
                            stmt.setString(4,is.readUTF());
                            stmt.setString(5,is.readUTF());
                            stmt.setString(6,is.readUTF());
                            stmt.setString(7,is.readUTF());
                            stmt.execute();
                        } catch (SQLException ex) {
                            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                         
                    }
                    else if(ch.equals("3")){
                       
                        String choice=is.readUTF();
                        if(choice.equals("1")){
                            conn=DB.getconnection();
                            String sql ="select * from patent where id="+is.readUTF()+";";
                            stmt=conn.prepareCall(sql);
                            rs = stmt.executeQuery();
                            while(rs.next()){
                                //Retrieve by column name
                                os.writeUTF(rs.getString("name"));
                                os.writeUTF(rs.getString("age"));
                                os.writeUTF(rs.getString("phnumber"));
                                os.writeUTF(rs.getString("address"));
                                os.writeUTF(rs.getString("Status"));
                                os.writeUTF(rs.getString("supervisorDr"));
                                os.writeUTF(rs.getString("Bills"));
                            }
                        }
                        else if(choice.equals("2")){
                            conn=DB.getconnection();
                            String sql="update `patent`set `name`=? ,`address`=? ,`age`=? ,`phnumber`=? ,`Status`=? ,`supervisorDr`=?,`Bills`=? where id="+is.readUTF()+";";    
                            try {
                                stmt=conn.prepareCall(sql);
                                stmt.setString(1,is.readUTF());
                                stmt.setString(2,is.readUTF());
                                stmt.setString(3,is.readUTF());
                                stmt.setString(4,is.readUTF());
                                stmt.setString(5,is.readUTF());
                                stmt.setString(6,is.readUTF());
                                stmt.setString(7,is.readUTF());
                                stmt.execute();
                            } catch (SQLException ex) {
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        
                        
                    }*/
                    
                }
                
                
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AlreadyBoundException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
