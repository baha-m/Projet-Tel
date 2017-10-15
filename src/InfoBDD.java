import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.*;


public class InfoBDD extends JFrame {
	
	private JLabel loginLabel, passLabel, pass2Label, mailLabel;
	private JTextField login,pass,pas;
	private JPasswordField pass2;
	private int visible=0;
	private conBDD c;

	public InfoBDD(User u){
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);//change to do nothing
			this.setVisible(true);
		conBDD c=new conBDD();

		//Shema
		JPanel panlogin = new JPanel();
		panlogin.setBackground(Color.white);
		panlogin.setPreferredSize(new Dimension(220, 60));
		panlogin.setBorder(BorderFactory.createTitledBorder("Shema"));
		loginLabel = new JLabel("SID : ");
		login = new JTextField(c.getShema());
		login.disable();
		login.setPreferredSize(new Dimension(90, 25));
		panlogin.add(loginLabel);
		panlogin.add(login);
		
		//login
		JPanel panpass = new JPanel();
		panpass.setBackground(Color.white);
		panpass.setPreferredSize(new Dimension(220, 60));
		panpass.setBorder(BorderFactory.createTitledBorder("Information de Connection"));
		passLabel = new JLabel("Log in : ");
		pass = new JTextField(c.getLog());
		pass.disable();
		pass.setPreferredSize(new Dimension(90, 25));
		panpass.add(passLabel);
		panpass.add(pass);
		
		//pass
		JPanel panpass2 = new JPanel();
		panpass2.setBackground(Color.white);
		panpass2.setPreferredSize(new Dimension(220, 60));
		panpass2.setBorder(BorderFactory.createTitledBorder("Information de Connection"));
		pass2Label = new JLabel("Mot de passe : ");
		pass2 = new JPasswordField(c.getMdp());
		pass2.setEchoChar('*');
		pass2.disable();
		pass2.setPreferredSize(new Dimension(90, 25));
		panpass2.add(pass2Label);
		panpass2.add(pass2);
		
		//1er pass v2
		JTextField pas =new JTextField();
		pas.setPreferredSize(new Dimension(90, 25));
		panpass.add(pas);
		pas.setVisible(false);
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panlogin);
		content.add(panpass);
		content.add(panpass2);
		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("Valider");
		JButton mod = new JButton("Editer");
		mod.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				okBouton.setEnabled(true);
				pass.setVisible(false);
				panpass.remove(1);
				panpass.revalidate();
				panpass.repaint();
				pass2.enable();
				pas.setText(u.getLog());
				pas.setVisible(true);
				mod.setEnabled(false);
				panpass2.revalidate();
				panpass2.repaint();
			}			
		});
		okBouton.setEnabled(false);
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {	
				System.out.println(pas.getText());
				System.out.println(pass2.getText());
				if(pas.getText().equals(pass2.getText() )&& pas.getText()!="") {		
				c.exec("update utilisateurs set passe='"+pas.getText()+"' where log='"+u.getLog()+"'");
			 setVisible(false);
				} 
								else {
					JOptionPane jop3 = new JOptionPane();
					jop3.showMessageDialog(null, "Les deux mots de passes ne correspondent pas !", "Erreur pass", JOptionPane.ERROR_MESSAGE);

				}
									
			}}
		
			
		);
		
		JButton cancelBouton = new JButton("Annuler");
		cancelBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					setVisible(false);
				}catch(NullPointerException n) {
					System.exit(0);	
					
				}
			}			
		});
		
		// cette partie concerne la difference entre l'admin et le novice
		if(u.getPriv().equals("user"))
		{
			mod.setEnabled(false);
		}
		
		control.add(mod);
		control.add(okBouton);
		control.add(cancelBouton);
		
		JLabel icon = new JLabel(new ImageIcon("icon/Bdd.png"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(icon);
		
		this.getContentPane().add(panIcon, BorderLayout.EAST);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
		
	
	}

}
