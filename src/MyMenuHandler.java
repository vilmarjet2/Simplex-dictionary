import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MyMenuHandler implements ActionListener, ItemListener 
{
	
	MainFrame menuFrame;
	public MyMenuHandler(MainFrame menuFrame) 
	{
		this.menuFrame = menuFrame;
	}
	// Handle action events.
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		String msg = "You selected ";
		String arg = ae.getActionCommand();
		System.out.println(msg + arg);
		
		if(arg.equals("New"))
		{
			menuFrame.framePropConf.setVisible(true);
		}else if (arg.equals("Exit"))
		{
			menuFrame.dispose();
		}
		
			
	}
	
	// Handle item events.
	@Override
	public void itemStateChanged(ItemEvent ie) 
	{
		menuFrame.repaint();
	}
	
}//end of class
