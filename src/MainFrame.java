import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class MainFrame extends JFrame  {
	
	/**
	 * 
	 */
	
	int nbVar = 0;//Must start with 0 
	int nbConst = 0; //Must start with 0
	int lg = 0;
//	private static final long serialVersionUID = 1L;
	public static Vocabulary vocabulary;
	JPanel mainPanel;
	JMenuBar menuBar;
	JFrame framePropConf;
	MyMenuHandler menuhandler;
	public PlotGraph  graph;
	public  Contraint mainCont;
	JPanel problem;
	
		
	public MainFrame ()
	{
		ParametersFrame();
		Initializations();
		
		// Add to the frame
        setJMenuBar(menuBar);
        add(mainPanel);
		
		setVisible(false);
		framePropConf.setVisible(false);
	}
	
	void Initializations()
	{
		vocabulary = new Vocabulary(lg);
		menuhandler = new MyMenuHandler(this);
		mainPanel = new My_MainPanel();
		menuBar = new My_JMenuBar(menuhandler);
		framePropConf = new FrameConfProbrlem(this);
		problem = new ProblemModel();
		
		
		mainPanel.setVisible(true);
		
	}
	void ParametersFrame()
	{
		
		setTitle("My Simplex Program");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)screenSize.getWidth() , (int)screenSize.getHeight()); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
	}
	
	
	public void setPanelProblem(int m, int n)
	{
		
		((My_MainPanel) mainPanel).setPanelProblem(m, n,this);
	}
	
	
	
	public static void changeFont ( Component component, Font font )
	{
	    component.setFont ( font );
	    if ( component instanceof Container )
	    {
	        for ( Component child : ( ( Container ) component ).getComponents () )
	        {
	            changeFont ( child, font );
	        }
	    }
	}
	
}
