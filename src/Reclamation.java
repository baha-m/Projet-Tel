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

public class Reclamation extends JFrame {

	private User u;
	private JPanel contentPane;
	private JLabel locleLabel, posteLabel, descLabel;
	private JComboBox loc, Poste;
	private TextArea desc;

	
	/**
	 * Create the frame.
	 */
	public Reclamation(User u) {
		this.u=u;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(700, 270);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//Creation du JFrame
		
		
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		
		//locale
				JPanel panloc = new JPanel();
				panloc.setBackground(Color.white);
				panloc.setPreferredSize(new Dimension(220, 60));
				panloc.setBorder(BorderFactory.createTitledBorder("Zone"));
				loc = new JComboBox();
				//requette sql
					conBDD b=new conBDD();
					String[] tab =new String[b.exec_aff_NB("Select count(distinct LABELLE) from locale")];
					b.exec_aff("select LABELLE from locale", tab);
				loc.addItem("Choisir");
				for(int i=0;i<tab.length;i++)
				{
					loc.addItem(tab[i]);
				}
				locleLabel = new JLabel("Locale : ");
				panloc.add(locleLabel);
				panloc.add(loc);
		//Poste
				JPanel panPoste = new JPanel();
				panPoste.setBackground(Color.white);
				panPoste.setPreferredSize(new Dimension(220, 60));
				panPoste.setBorder(BorderFactory.createTitledBorder("Poste"));
				Poste = new JComboBox();
				Poste.addItem("Choisir");
				//requette sql
					tab =new String[b.exec_aff_NB("Select count(distinct ref_pc) from poste")];
					if(tab.length!=0)
					b.exec_aff("select ref_pc from poste", tab);
					for(int i=0;i<tab.length;i++)
					{
						Poste.addItem(tab[i]);
					}
				posteLabel = new JLabel("Machine : ");
				panPoste.add(posteLabel);
				panPoste.add(Poste);
		//Desc
				JPanel panDesc = new JPanel();
				panDesc.setBackground(Color.white);
				panDesc.setPreferredSize(new Dimension(450, 120));
				panDesc.setBorder(BorderFactory.createTitledBorder("Descrpition"));
				desc = new TextArea("",3,55,1);
				descLabel = new JLabel("Descrpition : ");
				panDesc.add(descLabel);
				panDesc.add(desc);
			
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panloc);
		content.add(panPoste);
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
				b.exec("insert into reclamation(ID_USER,LOCALE,MACHINE,DETAILS) values('"+u.getLog()+"','"+loc.getSelectedItem()+"','"+Poste.getSelectedItem()+"','"+desc.getText()+"')");
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
