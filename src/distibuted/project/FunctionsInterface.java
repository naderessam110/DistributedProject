/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distibuted.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Nader
 */
public interface FunctionsInterface extends Remote {
    public String select(String s)throws RemoteException;
    public void  insert(String name,String address,String age,String ph,String status,String dr,String bills)throws RemoteException;
    public String[] update_select(String s)throws RemoteException;
    public void  update(String id,String name,String address,String age,String ph,String status,String dr,String bills)throws RemoteException;
}
