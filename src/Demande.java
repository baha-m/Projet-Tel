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

 
	 public class Demande extends JPanel {
		 	private User u;
			private  int row1=0;
			private JTextField text1;
	 	public Demande(User u){
	 		this.u=u;
			try {
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
				 
	
	
				JLabel lib = new JLabel("Nombre de Demande:");
			
				conBDD b=new conBDD();
				row1=b.exec_aff_NB("Select count(NOMCOM)from Demande where ID_USER='"+u.getLog()+"'");
		
		
			JButton contra = new JButton("Nouvelle Demande");
			contra.setPreferredSize(new Dimension(200, 20));
			contra.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					Demander d=new Demander(u);
					d.setVisible(true);
				}			
			});
			JButton annule = new JButton("Annuler une Demande");
			annule.setPreferredSize(new Dimension(200, 20));
			annule.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					DemandeA d=new DemandeA(u);
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
			boutonlib.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					DemandeV r=new DemandeV(u);
					r.setVisible(true);
				}
			});
			
			actualiser.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					conBDD b=new conBDD();
					row1=b.exec_aff_NB("Select count(NOMCOM)from Demande where ID_USER='"+u.getLog()+"'");
					text1.setText(""+row1);
					if(row1>0) boutonlib.setEnabled(true);
					System.out.println(row1);
				}			
			});
			
			panlib.add(iconlib);
			panlib.add(lib);
			panlib.add(text1);
			panlib.add(boutonlib);
			panlib.setBackground(Color.white);
			
			
	
		
			
			JPanel premiere_couche = new JPanel();
			premiere_couche.setPreferredSize(new Dimension(350, 320));
			premiere_couche.setBackground(Color.white);

		
			JLabel vide = new JLabel("                                    ");
			vide.setPreferredSize(new Dimension(600, 20));
			premiere_couche.add(vide);
			premiere_couche.add(contra);
			premiere_couche.add(annule);
			premiere_couche.add(actualiser);
			premiere_couche.add(panlib);
				
		

		

			
			JLabel jlb = new JLabel(new ImageIcon("icon/dem.jpg"));
			jlb.setPreferredSize(new Dimension(300, 320));
			this.setBackground(Color.white);
			JPanel doubl = new JPanel();
			doubl.add(jlb);
			doubl.add(premiere_couche);
			doubl.setBackground(Color.white);

			this.add(doubl,  BorderLayout.CENTER);
			this.setBackground(Color.white);

	 	  }catch(Exception e){}
	 	}
	 	  public void paintComponent(Graphics g){
	            try {
	                    Image img = ImageIO.read(new File("images/fond.jpg"));
	                     g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	            } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	            }
	            
	    } 

	 }