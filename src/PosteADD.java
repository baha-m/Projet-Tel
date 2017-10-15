import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class PosteADD extends JFrame {

	private User u;
	private JPanel contentPane;
	private JLabel refLabel,prosLabel,ramLabel;
	private JTextField type;
	private JFormattedTextField ramt;
	private JComboBox pros,ref;
	
	/**
	 * Create the frame.
	 */
	public PosteADD(User u) {
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
				ref = new JComboBox();
				//requette SQL
				conBDD c=new conBDD();
				int row=c.exec_aff_NB("select count(ref) from pc where ref NOT IN(Select ref_pc from poste)");
				if(row!=0)
				{
					String[] t =new String[row];
					c.exec_aff("select ref from pc where ref NOT IN(Select ref_pc from poste)", t);
					for(int i=0;i<row;i++)
						ref.addItem(t[i]);
				}
				else{
					ref.addItem("None");
				}	
				ref.setPreferredSize(new Dimension(90, 25));
				refLabel = new JLabel("code : ");
				panref.add(refLabel);
				panref.add(ref);
		//Adresse phisique
				JPanel panpros = new JPanel();
				panpros.setBackground(Color.white);
				panpros.setPreferredSize(new Dimension(220, 60));
				panpros.setBorder(BorderFactory.createTitledBorder("Batiment"));
				pros = new JComboBox();
				pros.setSelectedItem("");
				//requette sql du type form TABLE
				conBDD b=new conBDD();
				String[] tab =new String[b.exec_aff_NB("Select distinct count(Batiment) from locale")];
				if(tab.length!=0)
					b.exec_aff("select Batiment from locale", tab);
			for(int i=0;i<tab.length;i++)
			{
				pros.addItem(tab[i]);
			}
				pros.addItem("Autre");
				pros.setPreferredSize(new Dimension(90, 25));
				prosLabel = new JLabel("Adresse : ");
				
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
						
						type.setVisible(false);
						pros.setVisible(true);boolean ok=true;
						String[] table =new String[pros.getItemCount()];
						for(int i=0;i<pros.getItemCount();i++)	table[i]=pros.getItemAt(i).toString();
						DefaultComboBoxModel model=new DefaultComboBoxModel(table);
							if(model.getIndexOf(type.getText())>-1)
							{
								pros.setSelectedItem(type.getText());
							}
							else{
								pros.removeItemAt(pros.getItemCount()-1);
								pros.addItem(type.getText());
								pros.addItem("Autre");
								System.out.println("erd");
								conBDD a=new conBDD();
								a.exec("insert into locale(Batiment) values('"+type.getText()+"')");
							}
							System.out.println("erf");
						
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
		        	prosLabel.setText("Nom : ");
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
		//Adresse ip
				JPanel panram = new JPanel();
				panram.setBackground(Color.white);
				panram.setPreferredSize(new Dimension(220, 60));
				panram.setBorder(BorderFactory.createTitledBorder("Adresse IP"));
				MaskFormatter mf1;
				try {
					mf1 = new MaskFormatter("###.###.###.###");
					mf1.setPlaceholderCharacter('0');
					ramt = new JFormattedTextField(mf1);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
				ramt.setPreferredSize(new Dimension(90, 25));
				ramLabel = new JLabel("IPV4 : ");
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
		if(ref.getSelectedItem()=="None") okBouton.setEnabled(false);
		control.add(okBouton);
			//action listener du OK
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				conBDD b=new conBDD();
				b.exec("insert into poste values('"+ref.getSelectedItem()+"','"+ramt.getText()+"','"+pros.getSelectedItem()+"','"+u.getLog()+"')");
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
