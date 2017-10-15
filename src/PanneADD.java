import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PanneADD extends JFrame {

	private User u;
	private JPanel contentPane;
	private JLabel refLabel,prosLabel,ramLabel;
	private JTextField ref,type;
	private JComboBox pros;
	private TextArea ramt;
	
	/**
	 * Create the frame.
	 */
	public PanneADD(User u) {
		this.u=u;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(700, 270);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//Creation du JFrame
		
		
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		
		//REF
				JPanel panref = new JPanel();
				panref.setBackground(Color.white);
				panref.setPreferredSize(new Dimension(220, 60));
				panref.setBorder(BorderFactory.createTitledBorder("Réference"));
				ref = new JTextField();
				ref.setPreferredSize(new Dimension(90, 25));
				refLabel = new JLabel("code : ");
				panref.add(refLabel);
				panref.add(ref);
		//Type
				JPanel panpros = new JPanel();
				panpros.setBackground(Color.white);
				panpros.setPreferredSize(new Dimension(220, 60));
				panpros.setBorder(BorderFactory.createTitledBorder("Panne"));
				pros = new JComboBox();
				pros.setSelectedItem("");
				//requette sql du type form TABLE
				conBDD b=new conBDD();
				String[] tab =new String[b.exec_aff_NB("Select distinct count(Nature) from panne")];
				b.exec_aff("select Nature from panne", tab);
			for(int i=0;i<tab.length;i++)
			{
				pros.addItem(tab[i]);
			}
				pros.addItem("Autre");
				pros.setPreferredSize(new Dimension(90, 25));
				prosLabel = new JLabel("Nature : ");
				
				//JTextField du type
				type =new JTextField();
				type.setPreferredSize(new Dimension(90, 25));
				type.setVisible(false);
				JButton newtype = new JButton("ok");
				newtype.setPreferredSize(new Dimension(50, 25));
				newtype.setVisible(false);
				newtype.setEnabled(false);
				newtype.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						pros.removeItemAt(pros.getItemCount()-1);
						pros.addItem(type.getText());
						pros.addItem("Autre");
						type.setVisible(false);
						pros.setVisible(true);
						pros.setSelectedItem(type.getText());
						newtype.setVisible(false);
						panpros.revalidate();
			        	panpros.repaint();
					}			
				});
				//*******Item Listener*******
				if((pros.getSelectedItem()=="Autre")) 
		        {
		        	pros.setVisible(false);
		        	type.setVisible(true);
		        	prosLabel.setText("Type : ");
		        	newtype.setVisible(true);
		        	if(type.getText()!="") newtype.setEnabled(true);
		        	panpros.revalidate();
		        	panpros.repaint();
		        };
				pros.addItemListener(new ItemListener() {
				      public void itemStateChanged(ItemEvent e) {
				        if((pros.getSelectedItem()=="Autre")) 
				        {
				        	pros.setVisible(false);
				        	type.setVisible(true);
				        	prosLabel.setText("Type : ");
				        	newtype.setVisible(true);
				        	if(type.getText()!="") newtype.setEnabled(true);
				        	panpros.revalidate();
				        	panpros.repaint();
				        };
				        
				      }
				    });
				
				panpros.add(prosLabel);
				panpros.add(pros);
				panpros.add(type);
				panpros.add(newtype);
		//Labelle
				JPanel panram = new JPanel();
				panram.setBackground(Color.white);
				panram.setPreferredSize(new Dimension(450, 120));
				panram.setBorder(BorderFactory.createTitledBorder("Descrpition"));
				ramt = new TextArea("",3,55,1);
				ramLabel = new JLabel("Details : ");
				panram.add(ramLabel);
				panram.add(ramt);
		
				
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panref);
		content.add(panpros);
		content.add(panram);
		
		
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
				b.exec("insert into panne values('"+ref.getText()+"','"+pros.getSelectedItem()+"','"+s+"','"+u.getLog()+"')");
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
