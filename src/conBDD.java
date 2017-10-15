import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;
import Com_Client.SocketClient;
public class conBDD {

	protected String url ="jdbc:oracle:thin:@//127.0.0.1:1521/xe";
    String user = "Tel";
    String password = "Tel";
    public conBDD(){
    };
   
    public void con(String log)
    {
    	exec("update UTILISATEURS set STATUT='en ligne' where log='"+log+"'");
    }
    public void deco(String log)
    {
    	exec("update UTILISATEURS set STATUT='hors ligne' where log='"+log+"'");
    }

    public String verif(String log,String pass)
    {
    	String[]s=new String[1];
    	this.exec_aff("Select log,passe from UTILISATEURS where log='"+log+"'", s);
    	try{
    	String str[] = s[0].split("\\ - ");
    	System.out.println(str[0]+"="+log+" . "+str[1]+"="+pass);
    	if(str[0].equals(log) && str[1].equals(pass)) return "ok";
    	else return "Mot de passe ou log invalide";
    	}
    	catch(NullPointerException e)
    	{
    		return "Il n'existe aucun compte avec ce log.";
    	} 	
    	
    }
    public String getPriv(String log)
    {
    	String p="";
    	/*try{
    		Connection conn = DriverManager.getConnection(url, user, password);
    		Statement state = conn.createStatement();
    		ResultSet result = state.executeQuery("Select priv from UTILISATEURS where log='"+log+"'");
    		result.next();
    		p=result.getString(1);
    		result.close();
            state.close();
            conn.close();
    	}catch(Exception e){
    		JOptionPane.showMessageDialog(null,e);}*/
    	SocketClient s=new SocketClient();
    	p=(String)s.Sync("4", log);
    	return p;
    }
    public void exec(String querry)
    {
    	/*
    	try{
    		Connection conn = DriverManager.getConnection(url, user, password);
    		Statement state = conn.createStatement();
    		ResultSet result = state.executeQuery(querry);
    		result.close();
            state.close();
            conn.close();
    	}catch(Exception e){
    		JOptionPane.showMessageDialog(null,e);}*/
    	SocketClient s=new SocketClient();
    	s.Sync("5", querry);
    };
    public void exec_aff(String querry,JTable tab)
    {
    	/*try{
    		Connection conn = DriverManager.getConnection(url, user, password);
    		Statement state = conn.createStatement();
    		ResultSet result = state.executeQuery(querry);
    		tab.setModel(DbUtils.resultSetToTableModel(result));
    		result.close();
            state.close();
            conn.close();
    	}catch(Exception e){
    		JOptionPane.showMessageDialog(null,e);}*/
    	SocketClient s=new SocketClient();
    	tab.setModel(((JTable)s.Sync("6", querry)).getModel());
    	//System.out.print(tab.getModel().getValueAt(0, 0));
    	//tab=(JTable)s.Sync("6", querry);*/
    	};
    	
        public void exec_aff(String querry,Object[] tab)
        {
        	/*try{
        		Connection conn = DriverManager.getConnection(url, user, password);
        		Statement state = conn.createStatement();
        		ResultSet result = state.executeQuery(querry);
        		int i=0;
        		while(result.next())
        		{
        			if(result.getMetaData().getColumnCount()>3)
        			{
        				tab[i]=result.getString(1)+" - "+result.getString(2)+" - "+result.getString(3)+" - "+result.getString(4);
            			i++;
        			}
        			else if(result.getMetaData().getColumnCount()>1&&result.getMetaData().getColumnCount()==3)
        			{
        			tab[i]=result.getString(1)+" - "+result.getString(2)+" - "+result.getString(3);
        			i++;
        			}
        			else if(result.getMetaData().getColumnCount()>1&&result.getMetaData().getColumnCount()<3	)
        			{
        			tab[i]=result.getString(1)+" - "+result.getString(2);
        			i++;
        			}
        			else
        			{
        				tab[i]=result.getString(1);
        				i++;
        			}
        		}
        		result.close();
                state.close();
                conn.close();
        	}catch(Exception e){
        		JOptionPane.showMessageDialog(null,e);}*/
        	SocketClient s=new SocketClient();
        	if (s.Sync("7", querry)!=null)
        	{
        	ArrayList al=new ArrayList((ArrayList)s.Sync("7", querry));
        	System.out.println(al.size());
        	System.out.println(al.get(0));
        	System.out.println("");
        	for(int i=0;i<al.size();i++)
        		tab[i]=al.get(i);
        	}
        	}
        	;
    	
    	public int exec_aff_NB(String querry)
        {	
    		int i=3;
    		/*try{
        		Connection conn = DriverManager.getConnection(url, user, password);
        		Statement state = conn.createStatement();
        		ResultSet result = state.executeQuery(querry);
        		result.next();
        		i=result.getInt(1);
        		result.close();
                state.close();
                conn.close();
        	}catch(Exception e){
        		JOptionPane.showMessageDialog(null,e);}*/
    		SocketClient s=new SocketClient();
    		i=(int) s.Sync("8", querry);
        	return (i);
        	}
        public String getShema(){
        	String s=url.substring((url.length()-2));
        	return s;
        }
        public String getLog(){
        	return user;
        }
        public String getMdp(){
        	return password;
        }
        	
}
    

