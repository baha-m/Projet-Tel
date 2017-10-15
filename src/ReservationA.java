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

public class ReservationA {
	private User u;
	public ReservationA(User u)
	{
		this.u=u;
		conBDD b=new conBDD();
		Object[] data = new Object[b.exec_aff_NB("Select count(ID_USER) from reservation where ID_USER='"+u.getLog()+"'")];
		if(data.length!=0)
		b.exec_aff("Select REF_PREPH,to_char(DD, 'DD-MM-YYYY HH24:MI:SS'),to_char(DF, 'DD-MM-YYYY HH24:MI:SS') from reservation where ID_USER='"+u.getLog()+"'",data );
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
		jop.setPreferredSize(new Dimension(500, 200));
		ImageIcon img = new ImageIcon("images/annule.jpg");

		String id = (String)jop.showInputDialog(null, 
										"Veuillez choisir la reservation a annuler !",
										"Annulation d'une reservation !",
										JOptionPane.QUESTION_MESSAGE,
										img,
										 data,
										 null);
		try{ 
			String str[] = id.split("\\ - ");
				String ref=str[0];
				String dd=str[1];
				String df=str[2];
				if(id !=null) {
			
			jop2 = new JOptionPane();			
			int option = jop.showConfirmDialog(null, "êtes-vous sûr de vouloir annuler cette reservation ?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(option == JOptionPane.OK_OPTION)
			{
			try{
			
			b.exec("delete from reservation where REF_PREPH='"+ref+"' and DD=TO_Date('"+dd+"','DD-MM-YYYY HH24:MI:SS') and DF=TO_Date('"+df+"','DD-MM-YYYY HH24:MI:SS') and ID_USER='"+u.getLog()+"'");
			}
			catch(Exception e ){
			 JOptionPane confirmation = new JOptionPane();
				confirmation.showMessageDialog(null, "La reservation a été annuler ", "Annulation reservation", JOptionPane.INFORMATION_MESSAGE, null);

			}
			}}
			}catch(NullPointerException e) {}
	}
}
