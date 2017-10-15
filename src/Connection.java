import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;

import javax.swing.Action;

public class Connection extends JFrame {

	private JPanel contentPane;
	private JLabel loginLabel, passLabel;
	private JTextField login;
	private JPasswordField  pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connection frame = new Connection();
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
	public Connection() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Informations utilisateur");
		
		//Le login
		JPanel panLogin = new JPanel();
		panLogin.setBackground(Color.white);
		panLogin.setPreferredSize(new Dimension(220, 60));
		panLogin.setBorder(BorderFactory.createTitledBorder("Identifiant"));
		loginLabel = new JLabel("Login : ");
		login = new JTextField();
		login.setPreferredSize(new Dimension(90, 25));
		panLogin.add(loginLabel);
		panLogin.add(login);
		
		//Le login
		JPanel panPass = new JPanel();
		panPass.setBackground(Color.white);
		panPass.setPreferredSize(new Dimension(220, 60));
		panPass.setBorder(BorderFactory.createTitledBorder("Mot de passe"));
		passLabel = new JLabel("Passe : ");
		pass = new JPasswordField();
		pass.setPreferredSize(new Dimension(90, 25));
		panPass.add(passLabel);
		panPass.add(pass);

		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panLogin);
		content.add(panPass);

		JPanel control = new JPanel();
		JButton okBouton = new JButton("Valider");
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					conBDD c=new conBDD();
					String o=c.verif(login.getText(), pass.getText().toString());
					if(o.equals("ok"))
					{
						User u=new User(login.getText());
						chargement INTRO = new chargement(u);
						setVisible(false);
					}
					else
					{
						JFrame f1 = new JFrame();
						JOptionPane.showMessageDialog(f1,
							    o,
							    "Probleme d'identification",
							    JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
				//main frame to set here ****************************************************
			}			
		});
		JButton cancelBouton = new JButton("Annuler");
		cancelBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
					System.exit(0);	
			}			
		});
		
		control.add(okBouton);
		control.add(cancelBouton);
		
		JLabel icon = new JLabel(new ImageIcon("images/green.png"));
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
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
