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

public class ReclamationA {
	private User u;
	public ReclamationA(User u)
	{
		this.u=u;
		conBDD b=new conBDD();
		Object[] data = new Object[b.exec_aff_NB("Select count(Machine) from reclamation where ID_USER='"+u.getLog()+"'")];
		if(data.length!=0)
		b.exec_aff("Select LOCALE,MACHINE from Reclamation where ID_USER='"+u.getLog()+"'",data );
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
				String loc=str[0];
				String mac=str[1];
				if(id !=null) {
			
			jop2 = new JOptionPane();			
			int option = jop.showConfirmDialog(null, "êtes-vous sûr de vouloir annuler cette reclamation ?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(option == JOptionPane.OK_OPTION)
			{
			try{
			
			b.exec("delete from reclamation where locale='"+loc+"' and machine='"+mac+"' and ID_USER='"+u.getLog()+"'");
			}
			catch(Exception e ){
			 JOptionPane confirmation = new JOptionPane();
				confirmation.showMessageDialog(null, "La reclamation a été annuler ", "Annulation reclamation", JOptionPane.INFORMATION_MESSAGE, null);

			}
			}}
			}catch(NullPointerException e) {}
	}
}
