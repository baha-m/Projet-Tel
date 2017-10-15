import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

import javax.swing.*;

//hethi te3I


public class chargement extends JFrame{
	private Thread t;
	private JProgressBar bar;
	private JPanel contentPane;
	private User u;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					//chargement frame = new chargement();
			}
		});
	}

	
	public chargement(User me){
		
		setSize(490, 370);
		setLocationRelativeTo(null);
		this.setUndecorated(true);
		u=me;
		
		t = new Thread(new Traitement());
		bar  = new JProgressBar();
		bar.setMaximum(300);
		bar .setMinimum(0);
		bar.setStringPainted(true);
		bar.setBackground(Color.white);

		JPanel pan = new JPanel();
		JLabel img = new JLabel(new ImageIcon("images/chargement.jpg"));
		img.setVerticalAlignment(JLabel.CENTER);
		img.setHorizontalAlignment(JLabel.CENTER);
		
		pan.setBorder(BorderFactory.createLineBorder(Color.blue));
		pan.add(img);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(pan, BorderLayout.NORTH);
		contentPane.add(bar, BorderLayout.CENTER);
		contentPane.add(new JLabel("Chargement en cours veuillez patientez un instant..."), BorderLayout.SOUTH);
		setContentPane(contentPane);
		
				t = new Thread(new Traitement());
				t.start();
		this.setVisible(true);
		
	}
	


class Traitement implements Runnable{

	public void run(){
		
		
		for(int val = 0; val <= 300; val++){
			bar.setValue(val);
			try {
				t.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	setVisible(false);
	conBDD c=new conBDD();
	c.con(u.getLog());
	String s="";
	try {
		s=(java.net.InetAddress.getLocalHost().getHostAddress());
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	c.exec("Update UTILISATEURS set ip='"+s+"'");
	if(c.getPriv(u.getLog()).equals("admin"))
	{
		Menu iu=new Menu(u);
		iu.setVisible(true);
	}
	else{
		InterUser iu=new InterUser(u);
		iu.setVisible(true);}
	}	
}
}