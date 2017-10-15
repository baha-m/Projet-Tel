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

import com.toedter.calendar.JDateChooser;

public class PanneDel extends JFrame {

	private User u;
	private JPanel contentPane;
	private JLabel prosLabel,ramLabel,dateLabel;
	private JComboBox pros;
	private TextArea ramt;
	private JDateChooser DD;
	
	/**
	 * Create the frame.
	 */
	public PanneDel(User u) {
		this.u=u;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(700, 270);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//Creation du JFrame
		
		
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		
		
		//Ref Panne
				JPanel panpros = new JPanel();
				panpros.setBackground(Color.white);
				panpros.setPreferredSize(new Dimension(220, 60));
				panpros.setBorder(BorderFactory.createTitledBorder("Panne"));
				pros = new JComboBox();
				pros.setSelectedItem("");
				//requette sql du type form TABLE
				conBDD b=new conBDD();
				String[] tab =new String[b.exec_aff_NB("Select count(ref) from panne")];
				b.exec_aff("select ref from panne", tab);
			for(int i=0;i<tab.length;i++)
			{
				pros.addItem(tab[i]);
			}
				pros.setPreferredSize(new Dimension(90, 25));
				prosLabel = new JLabel("Reference : ");
			
				panpros.add(prosLabel);
				panpros.add(pros);
		//date de disponibilié
				JPanel panHd = new JPanel();
				panHd.setBackground(Color.white);
				panHd.setPreferredSize(new Dimension(220, 60));
				panHd.setBorder(BorderFactory.createTitledBorder("Debut de Reservation"));
				DD = new JDateChooser();
				DD.setPreferredSize(new Dimension(120,20));
				DD.setDateFormatString("d  /  MM  /  yyyy");
				dateLabel = new JLabel("la Date : ");
				panHd.add(dateLabel);
				panHd.add(DD);

		//Labelle
				JPanel panram = new JPanel();
				panram.setBackground(Color.white);
				panram.setPreferredSize(new Dimension(450, 120));
				panram.setBorder(BorderFactory.createTitledBorder("Maintenece"));
				ramt = new TextArea("",3,55,1);
				ramLabel = new JLabel("Details : ");
				panram.add(ramLabel);
				panram.add(ramt);
		
				
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panpros);
		content.add(panHd);
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
				b.exec("insert into MAINTENANCE values('"+u.getLog()+"','"+pros.getSelectedItem()+"','"+ramt.getText()+"',TO_DATE('"+DD.getDate().getYear()+"/"+(DD.getDate().getMonth()+1)+"/"+DD.getDate().getDate()+"','YYYY/MM/DD'))");
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
