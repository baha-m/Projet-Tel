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

public class MaterielADD extends JFrame {

	private User u;
	private JPanel contentPane;
	private JLabel refLabel,prosLabel,ramLabel,graphLabel,ddLabel,descLabel;
	private JTextField ref,ramt,pros,graph,dd,desc;
	private JComboBox ram,ddt;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public MaterielADD(User u) {
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
				refLabel = new JLabel("Nom Commercial : ");
				panref.add(refLabel);
				panref.add(ref);
		//Processeur
				JPanel panpros = new JPanel();
				panpros.setBackground(Color.white);
				panpros.setPreferredSize(new Dimension(220, 60));
				panpros.setBorder(BorderFactory.createTitledBorder("Processeur"));
				pros = new JTextField();
				pros.setPreferredSize(new Dimension(90, 25));
				prosLabel = new JLabel("Caractéristique : ");
				panpros.add(prosLabel);
				panpros.add(pros);
		//Ram
				JPanel panram = new JPanel();
				panram.setBackground(Color.white);
				panram.setPreferredSize(new Dimension(220, 60));
				panram.setBorder(BorderFactory.createTitledBorder("Ram"));
				ram = new JComboBox();
				ram.addItem("1");
				ram.addItem("2");
				ram.addItem("3");
				ram.addItem("4");
				ram.addItem("6");
				ram.addItem("8");
				ram.addItem("12");
				ram.addItem("16");
				ram.addItem("32");
				ramt =new JTextField();
				ramt.setPreferredSize(new Dimension(90, 25));
				ramLabel = new JLabel("Mémoire : ");
				panram.add(ramLabel);
				panram.add(ram);
				panram.add(ramt);
		//Graph
				JPanel pangraph = new JPanel();
				pangraph.setBackground(Color.white);
				pangraph.setPreferredSize(new Dimension(220, 60));
				pangraph.setBorder(BorderFactory.createTitledBorder("Carte Graphique"));
				graph = new JTextField();
				graph.setPreferredSize(new Dimension(90, 25));
				graphLabel = new JLabel("Caractéristique : ");
				pangraph.add(graphLabel);
				pangraph.add(graph);
		//Disque Dur
				JPanel pandd = new JPanel();
				pandd.setBackground(Color.white);
				pandd.setPreferredSize(new Dimension(220, 60));
				pandd.setBorder(BorderFactory.createTitledBorder("Disque Dur"));
				dd = new JTextField();
				dd.setPreferredSize(new Dimension(90, 25));
				ddt = new JComboBox();
				ddt.addItem("1To");
				ddt.addItem("2To");
				ddt.addItem("3To");
				ddt.addItem("500");
				ddt.addItem("250");
				ddt.addItem("320");
				ddLabel = new JLabel("Marque : ");
				pandd.add(ddLabel);
				pandd.add(dd);
				pandd.add(ddt);
		//details
				JPanel panDesc = new JPanel();
				panDesc.setBackground(Color.white);
				panDesc.setPreferredSize(new Dimension(220, 60));
				panDesc.setBorder(BorderFactory.createTitledBorder("Autre Caractéristique"));
				desc = new JTextField();
				desc.setPreferredSize(new Dimension(90, 25));
				descLabel = new JLabel("Details : ");
				panDesc.add(descLabel);
				panDesc.add(desc);
			
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panref);
		content.add(panpros);
		content.add(panram);
		content.add(pangraph);
		content.add(pandd);
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
				b.exec("insert into pc values('"+ref.getText()+"','"+pros.getText()+"','"+(ram.getSelectedItem()+ramt.getText())+"','"+graph.getText()+"','"+(dd.getText()+ddt.getSelectedItem())+"','"+desc.getText()+"','"+u.getLog()+"' )");
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
