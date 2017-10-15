import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Demander extends JFrame {

	private User u;
	private JPanel contentPane;
	private JTextField nom;
	private JLabel typeLabel, nomLabel, descLabel;
	private JComboBox type;
	private TextArea desc;

	/**
	 * Create the frame.
	 */
	public Demander(User u) {
		this.u=u;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(700, 270);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//Creation du JFrame
		
		
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		
		//Type
				JPanel pantype = new JPanel();
				pantype.setBackground(Color.white);
				pantype.setPreferredSize(new Dimension(220, 60));
				pantype.setBorder(BorderFactory.createTitledBorder("Demande"));
				type = new JComboBox();
				
				type.addItem("PC");
				type.addItem("Preph");
				
				typeLabel = new JLabel("Type : ");
				pantype.add(typeLabel);
				pantype.add(type);
		//nom
				JPanel pannom = new JPanel();
				pannom.setBackground(Color.white);
				pannom.setPreferredSize(new Dimension(220, 60));
				pannom.setBorder(BorderFactory.createTitledBorder("Nom Commercial"));
				nom = new JTextField();
				nom.setPreferredSize(new Dimension(90, 25));
				nomLabel = new JLabel("Nom : ");
				pannom.add(nomLabel);
				pannom.add(nom);
		//Desc
				JPanel panDesc = new JPanel();
				panDesc.setBackground(Color.white);
				panDesc.setPreferredSize(new Dimension(450, 120));
				panDesc.setBorder(BorderFactory.createTitledBorder("Specification"));
				desc = new TextArea("",3,55,1);
				descLabel = new JLabel("Détaille : ");
				panDesc.add(descLabel);
				panDesc.add(desc);
			
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(pantype);
		content.add(pannom);
		content.add(panDesc);
		
		//boutons
		JPanel control = new JPanel();
		JButton okBouton = new JButton("Suivant");
		JButton cancelBouton = new JButton("Annuler");
		control.add(okBouton);
			//action listener du OK
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				conBDD b=new conBDD();
				b.exec("insert into Demande (ID_USER,Type,NOMCOM,SPEC) values('"+u.getLog()+"','"+type.getSelectedItem()+"','"+nom.getText()+"','"+desc.getText()+"')");
				setVisible(false);
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
