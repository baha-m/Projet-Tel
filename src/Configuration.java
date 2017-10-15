import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Configuration extends JFrame {

	private JPanel contentPane;
	
	private JLabel loginLabel, passLabel, pass2Label;
	private JPasswordField login;
	private JPasswordField  pass, pass2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Configuration frame = new Configuration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Configuration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize( 400, 300);
		setLocationRelativeTo(null);
		this.setTitle("Configuration de la base de donnée");
		this.setResizable(false);
		
		//Le login
		JPanel panLogin = new JPanel();
		panLogin.setBackground(Color.white);
		panLogin.setPreferredSize(new Dimension(220, 60));
		panLogin.setBorder(BorderFactory.createTitledBorder("Nom de la base de donnée"));
		loginLabel = new JLabel("Base de donnée : ");
		login = new JPasswordField("");
		login.setPreferredSize(new Dimension(90, 25));
		panLogin.add(loginLabel);
		panLogin.add(login);
		
		//Le pass
		JPanel panPass = new JPanel();
		panPass.setBackground(Color.white);
		panPass.setPreferredSize(new Dimension(220, 60));
		panPass.setBorder(BorderFactory.createTitledBorder("Nom de l'utilisateur"));
		passLabel = new JLabel("L'utilisateur : ");
		pass = new JPasswordField("");
		pass.setPreferredSize(new Dimension(90, 25));
		panPass.add(passLabel);
		panPass.add(pass);
		
		//Le pass 2
		JPanel panPass2 = new JPanel();
		panPass2.setBackground(Color.white);
		panPass2.setPreferredSize(new Dimension(220, 60));
		panPass2.setBorder(BorderFactory.createTitledBorder("Le mot de passe"));
		pass2Label = new JLabel("Passe : ");
		pass2 = new JPasswordField("");
		pass2.setPreferredSize(new Dimension(90, 25));
		panPass2.add(pass2Label);
		panPass2.add(pass2);
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panLogin);
		content.add(panPass);
		content.add(panPass2);
		
		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("Valider");
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {					
			//Connect v = new Connect(login.getText(),pass.getText(), pass2.getText());
			
				JOptionPane confirmation = new JOptionPane();
			confirmation.showMessageDialog(null, "Informations enregistrès ", "Confirmation", JOptionPane.INFORMATION_MESSAGE, null);
			setVisible(false);
		
			}
		}
		);
		
		JButton cancelBouton = new JButton("Annuler");
		cancelBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
					System.exit(0);	
			}			
		});
		
		control.add(okBouton);
		control.add(cancelBouton);
		JLabel icon = new JLabel(new ImageIcon("admin.png"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(icon); 

		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(panIcon, BorderLayout.EAST);
		contentPane.add(content, BorderLayout.CENTER);
		contentPane.add(control, BorderLayout.SOUTH);
		setContentPane(contentPane);
}}
