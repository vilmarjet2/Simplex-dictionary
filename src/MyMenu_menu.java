import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MyMenu_menu extends JMenu {
	
	private static final long serialVersionUID = 1L;
	
	public MyMenu_menu(String s,MyMenuHandler handler)
	{
		super(s);
		
		setMnemonic(KeyEvent.VK_A);
		getAccessibleContext().setAccessibleDescription(
		        "The menu");
		
		add_Items(handler);
		
		setVisible(true);
	}
	
	public void add_Items(MyMenuHandler handler)
	{
		
//		items 
		JMenuItem newAction = new JMenuItem("New");
        JMenuItem restart = new JMenuItem("Restart");
        JMenuItem exitAction = new JMenuItem("Exit");
  
//        actions 
        newAction.addActionListener(handler);
        exitAction.addActionListener(handler);

//        addtion
        add(newAction);
        add(restart);
        add(exitAction);
	}

}
