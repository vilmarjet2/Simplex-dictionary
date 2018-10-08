import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jefte
 */

public class JPaneIntroduction 
{
	private JFrame mainFrame ;
	private JLabel headerLabel;
	private JLabel statusLabel;
	int sizeVec = 3;

	public JPaneIntroduction() 
	{

		mainFrame = new JFrame("Java Swing Examples Edited");
		mainFrame.setSize(400, 700);
		mainFrame.setLayout(new GridLayout(5, 1));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		headerLabel = new JLabel("Header Label", JLabel.CENTER);
		statusLabel = new JLabel("Click in button to start", JLabel.CENTER);
		statusLabel.setSize(100, 100);

//    Frame of Conf. of problem 
//		JFrame frameDic = new frameDictionaire(5, 5); // Most important Frame
		JFrame frameDic = new MainFrame();

//      Set ControlPane to add Button
		JPanel PanelButton = new FirstPanel(400, 400);
		((FirstPanel) PanelButton).creatButton_ok(frameDic, statusLabel);

//      Add in main frame
		mainFrame.add(headerLabel);
		mainFrame.add(PanelButton);
		mainFrame.add(statusLabel);

//      set visible 
		mainFrame.setVisible(true);
	}

}
