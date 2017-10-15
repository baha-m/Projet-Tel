import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class User {
	private String log;
	private String ip;
	
	public User(String log) throws SocketException{
		this.log=log;
		NetworkInterface ni = NetworkInterface.getByIndex(3);
        InetAddress address = ni.getInetAddresses().nextElement();
        ip=address.getHostAddress();
	};
	public String getLog()
	{
		return log;
		
	}
	public String getPriv()
	{
		String[] s=new String[1];
		conBDD c=new conBDD();
		c.exec_aff("Select priv from utilisateurs where log='"+this.getLog()+"'", s);
		return s[0];
	}
}
