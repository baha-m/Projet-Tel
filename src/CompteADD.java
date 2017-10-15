import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.SocketException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CompteADD extends JFrame {

	private User u;
	private JPanel contentPane;
	private JLabel refLabel,prosLabel,ramLabel,privLabel;
	private JTextField ref;
	private JPasswordField pros,ramt;
	private JComboBox priv;
	
	/**
	 * Create the frame.
	 */
	public CompteADD(User u) {
		this.u=u;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(700, 270);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//Creation du JFrame
		
		
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		
		//LOG
				JPanel panref = new JPanel();
				panref.setBackground(Color.white);
				panref.setPreferredSize(new Dimension(220, 60));
				panref.setBorder(BorderFactory.createTitledBorder("Information Compte"));
				ref = new JTextField();
				ref.setPreferredSize(new Dimension(90, 25));
				refLabel = new JLabel("Log In : ");
				panref.add(refLabel);
				panref.add(ref);
		//pass
				JPanel panpros = new JPanel();
				panpros.setBackground(Color.white);
				panpros.setPreferredSize(new Dimension(220, 60));
				panpros.setBorder(BorderFactory.createTitledBorder("Information Compte"));
				pros = new JPasswordField();
				pros.setPreferredSize(new Dimension(90, 25));
				prosLabel = new JLabel("Mot de passe : ");
				panpros.add(prosLabel);
				panpros.add(pros);
		//Passe2
				JPanel panram = new JPanel();
				panram.setBackground(Color.white);
				panram.setPreferredSize(new Dimension(220, 60));
				panram.setBorder(BorderFactory.createTitledBorder("Confirmation Mot de passe"));
				ramt = new JPasswordField();
				ramt.setPreferredSize(new Dimension(90, 25));
				ramLabel = new JLabel("Mot de passe : ");
				panram.add(ramLabel);
				panram.add(ramt);
		//privilaige
				JPanel panpriv = new JPanel();
				panpriv.setBackground(Color.white);
				panpriv.setPreferredSize(new Dimension(220, 60));
				panpriv.setBorder(BorderFactory.createTitledBorder("Droit"));
				priv = new JComboBox();
				priv.setPreferredSize(new Dimension(90, 25));
				priv.addItem("admin");
				priv.addItem("user");
				privLabel = new JLabel("Privilege : ");
				panpriv.add(privLabel);
				panpriv.add(priv);
		
				
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panref);
		content.add(panpros);
		content.add(panram);
		content.add(panpriv);
		
		
		//boutons
		JPanel control = new JPanel();
		JButton okBouton = new JButton("Suivant");
		JButton cancelBouton = new JButton("Annuler");
		control.add(okBouton);
			//action listener du OK
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				conBDD b=new conBDD();
				String s=ramt.getText();
				System.out.println(ramt.getText());
				System.out.println(pros.getText());
				if(ramt.getText().equals(pros.getText()))
				{
						b.exec("insert into UTILISATEURS(log,passe,statut,priv,ajpar) values('"+ref.getText()+"','"+pros.getText()+"','hors ligne','"+priv.getSelectedItem()+"','"+u.getLog()+"')");
						setVisible(false);
					
				}
				else
				{
					JFrame f1 = new JFrame();
					
					JOptionPane.showMessageDialog(f1,
						    "les mots de passe ne sont pas identiques.",
						    "Mot de passe erreur",
						    JOptionPane.ERROR_MESSAGE);
				}
				
			}			
		});
		control.add(cancelBouton);
		cancelBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}			
		});
		
		JLabel icon = new JLabel(new ImageIcon("icon/tel.png"));
		JPanel panIcon2 = new JPanel();
		panIcon2.setBackground(Color.white);
		panIcon2.setLayout(new BorderLayout());
		panIcon2.add(icon);
		
		contentPane.add(panIcon2, BorderLayout.WEST);
		contentPane.add(content, BorderLayout.CENTER);
		contentPane.add(control, BorderLayout.SOUTH);
	}

}
