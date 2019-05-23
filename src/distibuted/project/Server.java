/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distibuted.project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Nader
 */
public class Server {
    static ServerSocket socket;
    
    static DataInputStream is;
    static DataOutputStream os;
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        socket = new ServerSocket(9090); //specifing the port 9090 
        while(true){
            Socket clientsocket=socket.accept();
            ClientThread thread=new ClientThread(clientsocket);
            thread.start();
        }
        //clientsocket =socket.accept(); //let any client to be connected to server
        
    }
}
