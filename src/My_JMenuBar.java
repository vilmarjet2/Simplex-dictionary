import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class My_JMenuBar extends JMenuBar {

	/**
	 * 
	 */
	JMenu menu;
	JMenu edit; 
	
	private static final long serialVersionUID = 1L;
	
	public My_JMenuBar(MyMenuHandler handler)
	{
		CreateMenus( handler);
		setVisible(true);
	}
	
	void CreateMenus(MyMenuHandler handler)
	{
		
		//Initialization and Build menus
		menu = new MyMenu_menu("Menu", handler);
		edit = new JMenu("Edit");

		
		//adition
		add(menu);
		add(edit);
	}
	
}
