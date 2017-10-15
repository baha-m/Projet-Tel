import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class Postes extends JPanel {

	private User u;
	private  int row1=0,row2=0;
	private JTextField text1,text2;
	
	public Postes(User u)
	{
		this.u=u;

			Border[] listBorder = {	
					BorderFactory.createEtchedBorder(Color.green, Color.GRAY),
					BorderFactory.createLineBorder(Color.green),
					BorderFactory.createLineBorder(Color.orange),
					BorderFactory.createLineBorder(Color.red),
					BorderFactory.createMatteBorder(5, 2, 5, 2, Color.black),
					BorderFactory.createRaisedBevelBorder(),
					BorderFactory.createTitledBorder("Titre")
					
						
				};
			JButton actualiser = new JButton("Actualiser");
			 actualiser.setPreferredSize(new Dimension(200, 20));
			 
			 JLabel lib = new JLabel("Nombre de Postes :");
			 JLabel lib1 = new JLabel("Machine en Stock :");
			 
			 conBDD b=new conBDD();
			row1=b.exec_aff_NB("Select count(ref_pc)from POSTE");
			row2=b.exec_aff_NB("Select count(ref)from pc");
			row2-=row1;
			
			JButton contra = new JButton("Association PC/Poste");
			contra.setPreferredSize(new Dimension(200, 20));
			JButton pca = new JButton("Libiration de Poste");
			pca.setPreferredSize(new Dimension(200, 20));
			contra.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					PosteADD pad=new PosteADD(u);
					pad.setVisible(true);
				}
			});
			pca.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					PosteDEL pdel=new PosteDEL(u);
				}
			});
			
			JPanel panlib = new JPanel();
			lib.setPreferredSize(new Dimension(160, 20));
			panlib.setBorder(listBorder[0]);
			lib.setAlignmentX(JLabel.CENTER);
			text1 =  new JTextField(""+row1);
			text1.setPreferredSize(new Dimension(50, 20));
			JLabel iconlib = new JLabel(new ImageIcon("images/connecte.jpg"));
			JButton boutonlib = new JButton("Voir");
			if(row1==0) boutonlib.setEnabled(false);
			panlib.setBackground(Color.white);
			 
			JPanel panlib1 = new JPanel();
			lib1.setPreferredSize(new Dimension(160, 20));
			panlib1.setBorder(listBorder[2]);
			lib1.setAlignmentX(JLabel.CENTER);
			text2 =  new JTextField(""+row2);
			text2.setPreferredSize(new Dimension(50, 20));
			JLabel iconlib1 = new JLabel(new ImageIcon("images/disponible.jpg"));
			JButton boutonlib1 = new JButton("Voir");
			if(row2==0) boutonlib1.setEnabled(false);
			panlib1.setBackground(Color.white);
			
			boutonlib.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					PostesV pv=new PostesV(u);
					pv.setVisible(true);
				}
			});
			boutonlib1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					StockV sv=new StockV(u);
					sv.setVisible(true);
				}
			});
			
			actualiser.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					conBDD b=new conBDD();
					row1=b.exec_aff_NB("Select count(ref_pc)from POSTE");
					row2=b.exec_aff_NB("Select count(ref)from pc");
					row2-=row1;
					text1.setText(""+row1);
					text2.setText(""+row2);
					if(row1>0) boutonlib.setEnabled(true);
					if(row2>0) boutonlib1.setEnabled(true);
				}			
			});
		
			panlib1.add(iconlib1);
			panlib1.add(lib1);
			panlib1.add(text2);
			panlib1.add(boutonlib1);
			panlib.add(iconlib);
			panlib.add(lib);
			panlib.add(text1);
			panlib.add(boutonlib);
			
			
			JPanel premiere_couche = new JPanel();
			premiere_couche.setPreferredSize(new Dimension(350, 320));
			premiere_couche.setBackground(Color.white);
			
			JLabel vide = new JLabel("                                    ");
			vide.setPreferredSize(new Dimension(600, 20));
			premiere_couche.add(vide);
			premiere_couche.add(contra);
			premiere_couche.add(pca);
			premiere_couche.add(actualiser);
			premiere_couche.add(panlib);
			premiere_couche.add(panlib1);
			
			JLabel jlb = new JLabel(new ImageIcon("icon/rec.jpg"));
			jlb.setPreferredSize(new Dimension(300, 320));
			this.setBackground(Color.white);
			JPanel doubl = new JPanel();
			doubl.add(jlb);
			doubl.add(premiere_couche);
			doubl.setBackground(Color.white);
			this.add(doubl,  BorderLayout.CENTER);
			this.setBackground(Color.white);
	}
}
