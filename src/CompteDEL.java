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

public class CompteDEL {
	private User u;
	public CompteDEL(User u)
	{
		this.u=u;
		conBDD b=new conBDD();
		Object[] data = new Object[b.exec_aff_NB("Select count(log) from UTILISATEURS")];
		b.exec_aff("Select log,ip,priv,ajpar from UTILISATEURS",data );
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
		jop.setPreferredSize(new Dimension(500, 200));
		ImageIcon img = new ImageIcon("images/annule.jpg");

		String id = (String)jop.showInputDialog(null, 
										"Veuillez choisir le compte a supprimer !",
										"Suppression d'un compte !",
										JOptionPane.QUESTION_MESSAGE,
										img,
										 data,
										 null);
		try{ 
			String str[] = id.split("\\ - ");
				String Type=str[0];
				String ajpar=str[3];
				String t;
			if(ajpar.equals("None"))
			{
				JFrame f1 = new JFrame();
				JOptionPane.showMessageDialog(f1,
					    "Impossible de supprimer un supper admin.",
					    "Permissions Insuffisantes",
					    JOptionPane.ERROR_MESSAGE);
			}
			else{
				if(ajpar.equals(u.getLog()))
				{
					t="";
				}
				else{
					t="ajouter par l'admin "+ajpar;
				}
				if(id !=null) {
			
			jop2 = new JOptionPane();			
			int option = jop.showConfirmDialog(null, "êtes-vous sûr de vouloir supprimer ce compte "+t+"?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(option == JOptionPane.OK_OPTION)
			{
			try{
			
			b.exec("delete from UTILISATEURS where log='"+Type+"'");
			}
			catch(Exception e ){
			 JOptionPane confirmation = new JOptionPane();
				confirmation.showMessageDialog(null, "Le compte a été supprimer ", "Suppression de compte", JOptionPane.INFORMATION_MESSAGE, null);

			}
			}}}
			}catch(NullPointerException e) {}
	}
}
