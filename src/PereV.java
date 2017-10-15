
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class PereV extends JFrame {
	private User u;
	private JToolBar tool = new JToolBar();
	private JButton load = new JButton(new ImageIcon("img/load.png"));
	private JSplitPane split;
	private JPanel result = new JPanel();
	
	public PereV(User u){
		this.u=u;
		this.setSize(600, 300);
		this.setTitle("Liste des Perepherique");
		setLocationRelativeTo(null);
		
		result.setLayout(new BorderLayout());
		JLabel jlb = new JLabel(new ImageIcon("images/600x120.jpg"));
		jlb.setPreferredSize(new Dimension(600, 120));
		JScrollPane dd = new JScrollPane(jlb);
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT,jlb , result);
		split.setDividerLocation(100);
		getContentPane().add(split, BorderLayout.CENTER);
		
		long start = System.currentTimeMillis();
		
		
		result.removeAll();
		
		JTable tab =new JTable(){
	        public boolean isCellEditable(int row, int column) {                
                return false;               
        };
    };
		conBDD b=new conBDD();
		b.exec_aff("select REF,TYPE,LABELLE from PEREPH", tab);
		
		String[] columnNames = {
	            "Reference",
	            "Type",
	            "Spécification"
	    };
		//mes test
		JTableHeader th = tab.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for(int i=0;i<tab.getModel().getColumnCount();i++)
		{
		TableColumn tc = tcm.getColumn(i);
		tc.setHeaderValue( columnNames[i] );
		}
		th.repaint();
		
		result.add(new JScrollPane(tab), BorderLayout.CENTER);
		long totalTime = System.currentTimeMillis() - start;
		result.add(new JLabel("La demande à été exécuter en " + totalTime + " ms"), BorderLayout.SOUTH);
		result.revalidate();
	}
}
