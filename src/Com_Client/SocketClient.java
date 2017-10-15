package Com_Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class SocketClient {
	private InetAddress host;
	private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
    private Object obj;
    
    public SocketClient(){
    	/*try {
			InetAddress host = InetAddress.getLocalHost();
			Socket socket = new Socket(host, 9876);
			oos = new ObjectOutputStream(socket.getOutputStream());
	        String[] s=new String[2];
	        s[0]=com;s[1]=req;
	        s[1].toLowerCase();
	        oos.writeObject(s);
	        ois = new ObjectInputStream(socket.getInputStream());
	        obj= ois.readObject();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
    	
    }
    public Object getObject() {
    	return obj;
    }
 
    public Object Sync(String com,String req){
    	try {
    		InetAddress host = InetAddress.getLocalHost();
			Socket socket = new Socket(host, 9876);
			oos = new ObjectOutputStream(socket.getOutputStream());
	        String[] s=new String[2];
	        s[0]=com;s[1]=req;
	        oos.writeObject(s);
	        ois = new ObjectInputStream(socket.getInputStream());
	        obj= ois.readObject();
	        
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return obj;
        
    }
    
}