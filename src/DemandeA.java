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

public class DemandeA {
	private User u;
	public DemandeA(User u)
	{
		this.u=u;
		conBDD b=new conBDD();
		Object[] data = new Object[b.exec_aff_NB("Select count(TYPE) from Demande where ID_USER='"+u.getLog()+"'")];
		if(data.length!=0)
		b.exec_aff("Select Type,NOMCOM from DEMANDE where ID_USER='"+u.getLog()+"'",data );
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
		jop.setPreferredSize(new Dimension(500, 200));
		ImageIcon img = new ImageIcon("images/annule.jpg");

		String id = (String)jop.showInputDialog(null, 
										"Veuillez choisir la reclamation a annuler !",
										"Annulation d'une reclamation !",
										JOptionPane.QUESTION_MESSAGE,
										img,
										 data,
										 null);
		try{ 
			String str[] = id.split("\\ - ");
				String Type=str[0];
				String NOMCOM=str[1];
				if(id !=null) {
			
			jop2 = new JOptionPane();			
			int option = jop.showConfirmDialog(null, "êtes-vous sûr de vouloir annuler cette demande ?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(option == JOptionPane.OK_OPTION)
			{
			try{
			
			b.exec("delete from demande where type='"+Type+"' and nomcom='"+NOMCOM+"' and ID_USER='"+u.getLog()+"'");
			}
			catch(Exception e ){
			 JOptionPane confirmation = new JOptionPane();
				confirmation.showMessageDialog(null, "La demande a été annuler ", "Annulation demande", JOptionPane.INFORMATION_MESSAGE, null);

			}
			}}
			}catch(NullPointerException e) {}
	}
}
