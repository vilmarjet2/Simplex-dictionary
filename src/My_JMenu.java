import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class My_JMenu extends JMenu {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public My_JMenu(String s)
	{
		super(s);
		
		setMnemonic(KeyEvent.VK_A);
		getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		
		// Create and add simple menu item to one of the drop down menu
        JMenuItem newAction = new JMenuItem("New");
        newAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frameDic = new frameDictionaire(5, 5);
				frameDic.setVisible(true);
			}
		});
        
        
        
        JMenuItem restart = new JMenuItem("Restart");
        JMenuItem openAction = new JMenuItem("Open");
        JMenuItem exitAction = new JMenuItem("Exit");
        
        add(newAction);
        add(restart);
        add(openAction);
        add(exitAction);
        
	}

}
