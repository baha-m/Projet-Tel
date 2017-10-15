import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

public class InterUser extends JFrame {
	
	private User u;

	private JPanel contentPane;
	private static JSplitPane split;
	private static JPanel menu = new JPanel();
	private JTabbedPane p;
	private JToolBar toolBar = new JToolBar();
	
	private JMenuBar menuBar = new JMenuBar();
	//menu bar et les items de menus declaration
	private JMenu param = new JMenu("Parametre");
	private JMenuItem bdd = new JMenuItem("Base de donnée");
	private JMenuItem fermer = new JMenuItem("fermer");
	
	//position du JTabbedPane
	public static int onglet = JTabbedPane.LEFT;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				User u;
				try {
					u = new User("1");
					InterUser frame = new InterUser(u);
					frame.setVisible(true);
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}
	
	
	
	
	/**
	 * Create the frame.
	 */
	public InterUser(User u1) {
		u=u1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		//*** monMenu***
		menu.setBackground(Color.white);
			//Toolbar
				//Creation des bouttons
		toolBar.setBackground(Color.white);
		JButton new_machine = new JButton(new ImageIcon("icon/man1.png"));
		new_machine.setToolTipText("Mon Compte");
		new_machine.setBackground(Color.WHITE);
		JButton sortir = new JButton(new ImageIcon("icon/dec.png"));
		sortir.setToolTipText("Deconnecter");
		sortir.setBackground(Color.WHITE);
		sortir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				conBDD b=new conBDD();
				b.deco(u.getLog());
				Connection c=new Connection();
				c.setVisible(true);
				setVisible(false);
			}
			
		});
				//JMenu action listeners
		
		new_machine.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Compte com=new Compte(u);
				
			}			
		});
		
		
				//ajout des bouttons et separateurs
		toolBar.add(new_machine);
		
		toolBar.addSeparator();
		toolBar.addSeparator();
		toolBar.add(sortir);
		
		
		menu.add(toolBar, BorderLayout.CENTER);
    	menu.setPreferredSize(new Dimension(600, 50));
	
    	//***Menu bar et les items de menus ajout***
    	param.add(bdd);
    	param.add(fermer);
    	param.setBackground(Color.WHITE);
    	param.setMnemonic('P');
    	
    	
    	menuBar.add(param);
    	setJMenuBar(menuBar);
    	// Menu Bar listeners
    	bdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				InfoBDD bd=new InfoBDD(u);
			}			
		});
    	fermer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}			
		});
    	
    	//***Menu couche 2***
    	p = new JTabbedPane(onglet);
    	
    	p.add("",new Reclame(u));
    	p.setIconAt(0, new ImageIcon("icon/reclame.png"));
    	p.add("",new Demande(u));
    	p.setIconAt(1, new ImageIcon("icon/Demande.png"));
    	p.add("",new Reserve(u));
    	p.setIconAt(2, new ImageIcon("icon/reserver.png"));
    	
    	p.setBackground(Color.white);
		//***content pane set***
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, menu,p);
		split.setOneTouchExpandable(true);
		split.setBackground(Color.white);
		this.setContentPane(split);
	}

}
