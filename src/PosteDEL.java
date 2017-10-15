import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class PosteDEL {
	private User u;
	public PosteDEL(User u)
	{
		this.u=u;
		conBDD b=new conBDD();
		
		Object[] data = new Object[b.exec_aff_NB("Select count(ref_pc) from POSTE")];
		if(data.length!=0)
		b.exec_aff("Select ref_pc,adresse_ph,ajpar from POSTE",data );
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
		jop.setPreferredSize(new Dimension(500, 200));
		ImageIcon img = new ImageIcon("images/annule.jpg");

		String id = (String)jop.showInputDialog(null, 
										"Veuillez choisir le Poste a Libirer !",
										"Libération d'un Poste !",
										JOptionPane.QUESTION_MESSAGE,
										img,
										 data,
										 null);
		try{ 
			String str[] = id.split("\\ - ");
				String ref=str[0];
				String ajpar=str[2];
				System.out.println(id.length());
				
				if(id !=null) {
					String s=new String();
					if(u.getLog().equals(ajpar))
					{	s="";}
					else
					{s=" Ajouter par l'admin "+ajpar;}
			jop2 = new JOptionPane();			
			int option = jop.showConfirmDialog(null, "êtes-vous sûr de vouloir liberer ce poste"+s+" ?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(option == JOptionPane.OK_OPTION)
			{
			try{
			
			b.exec("delete from poste where ref_pc='"+ref+"'");
			}
			catch(Exception e ){
			 JOptionPane confirmation = new JOptionPane();
				confirmation.showMessageDialog(null, "Le périphérique a été supprimer ", "Supression périphérique", JOptionPane.INFORMATION_MESSAGE, null);

			}
			}}
			}catch(NullPointerException e) {}
	}
}
