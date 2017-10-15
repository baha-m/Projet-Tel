import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

public class Reservation extends JFrame {

	private User u;
	private JPanel contentPane;
	private JLabel dateLabel, heurLabel, prefLabel;
	private JComboBox pref;
	private JDateChooser DD,DDf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User u=new User("1");
					Reservation frame = new Reservation(u);
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
	public Reservation(User u) {
		this.u=u;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(700, 270);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//Creation du JFrame
		
		
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		
		//pref
				JPanel panpref = new JPanel();
				panpref.setBackground(Color.white);
				panpref.setPreferredSize(new Dimension(220, 60));
				panpref.setBorder(BorderFactory.createTitledBorder("Zone"));
				pref = new JComboBox();
				//requette sql
					conBDD b=new conBDD();
					String[] tab =new String[b.exec_aff_NB("Select count(ref) from pereph")];
					b.exec_aff("select LABELLE from pereph", tab);
				pref.addItem("Choisir");
				for(int i=0;i<tab.length;i++)
				{
					pref.addItem(tab[i]);
				}
				prefLabel = new JLabel("Ressource : ");
				panpref.add(prefLabel);
				panpref.add(pref);
		//heur debut
				JPanel panHd = new JPanel();
				panHd.setBackground(Color.white);
				panHd.setPreferredSize(new Dimension(220, 80));
				panHd.setBorder(BorderFactory.createTitledBorder("Debut de Reservation"));
				DD = new JDateChooser();
				DD.setPreferredSize(new Dimension(120,20));
				//System.out.println(DD.getDateFormatString());
				DD.setDateFormatString("d  /  MM  /  yyyy");
				Date date =new Date();
				SpinnerDateModel sm= new SpinnerDateModel(date,null,null,Calendar.HOUR_OF_DAY);
				JSpinner temp= new JSpinner(sm);
				JSpinner.DateEditor de = new JSpinner.DateEditor(temp,"HH:mm");
				temp.setEditor(de);
				dateLabel = new JLabel("la Date : ");
				heurLabel = new JLabel("l'Heur :  ");
				panHd.add(dateLabel);
				panHd.add(DD);
				panHd.add(heurLabel);
				panHd.add(temp);
		//heur fin
				JPanel panHf = new JPanel();
				panHf.setBackground(Color.white);
				panHf.setPreferredSize(new Dimension(220, 80));
				panHf.setBorder(BorderFactory.createTitledBorder("Debut de Reservation"));
				DDf = new JDateChooser();
				DDf.setPreferredSize(new Dimension(120,20));
				//System.out.println(DD.getDateFormatString());
				DDf.setDateFormatString("d  /  MM  /  yyyy");
				Date datef =new Date();
				SpinnerDateModel smf= new SpinnerDateModel(datef,null,null,Calendar.HOUR_OF_DAY);
				JSpinner tempf= new JSpinner(smf);
				JSpinner.DateEditor def = new JSpinner.DateEditor(tempf,"HH:mm");
				tempf.setEditor(def);
				dateLabel = new JLabel("la Date : ");
				heurLabel = new JLabel("l'Heur :  ");
				panHf.add(dateLabel);
				panHf.add(DDf);
				panHf.add(heurLabel);
				panHf.add(tempf);
			
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		//content.add(panloc);
		content.add(panHd);
		content.add(panHf);
		content.add(panpref);
		//boutons
		JPanel control = new JPanel();
		JButton okBouton = new JButton("Suivant");
		JButton cancelBouton = new JButton("Annuler");
		control.add(okBouton);
			//action listener du OK
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				conBDD b=new conBDD();
				String querry="insert into reservation values('"+u.getLog()+"','"+pref.getSelectedItem()+"',TO_DATE('"+DD.getDate().getYear()+"/"+(DD.getDate().getMonth()+1)+"/"+DD.getDate().getDate()+" "+temp.getValue().toString().substring(11, 16)+"', 'YYYY/MM/DD HH24:MI'),TO_DATE('"+DDf.getDate().getYear()+"/"+(DDf.getDate().getMonth()+1)+"/"+DDf.getDate().getDate()+" "+tempf.getValue().toString().substring(11, 16)+"', 'YYYY/MM/DD HH24:MI'))";
				b.exec(querry);
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
