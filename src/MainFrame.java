import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class MainFrame extends JFrame  {
	
	/**
	 * 
	 */
	
	int nbVar = 0;
	int nbConst = 0;
//	private static final long serialVersionUID = 1L;
	JPanel mainPanel;
	JMenuBar menuBar;
	JFrame framePropConf;
	MyMenuHandler menuhandler;
	public PlotGraph  graph;
	public  Contraint mainCont;
		
	public MainFrame ()
	{
		
		
		ParametersFrame();
		Initializations();
		
//		setContentPane( new JPanel( new BorderLayout() ) );
		
		
		// Add the menubar to the frame
        setJMenuBar(menuBar);
		
		
		setVisible(false);
	}
	
	void Initializations()
	{
		menuhandler = new MyMenuHandler(this);
		mainPanel = new JPanel();
		menuBar = new My_JMenuBar(menuhandler);
		framePropConf = new FrameConfProbrlem(this);
		mainCont = new Contraint(0,0);
		
		
		// Add the menubar to the frame
        setJMenuBar(menuBar);
        add(mainPanel);
        mainPanel.setVisible(true);
        
	}
	void ParametersFrame()
	{
		
		setTitle("My Simplex Programm");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)screenSize.getWidth() , (int)screenSize.getHeight()); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
	}
	
	public void rePaint(){
	     paintComponents(this.getGraphics());
	     mainCont.LeftPanel.setVisible(true);
	}
	
	public void settingDicFrame(int m, int n){
//		getContentPane().removeAll();
		mainPanel.removeAll();
        settingsFrame(nbConst,nbVar);
        
        
        //Declaration
        mainCont = new Contraint(m,n);
        addContraints(m,n);
        mainPanel.setVisible(true);
    }
	

	private void settingsFrame(int m, int n)
	{
	     if (n != 2)
	     {   
	        mainPanel.setLayout(new GridLayout(1, 0)); // amount of panels in same collumn
	     }else
	     { // we have to add the size of Grafic
	    	 mainPanel.setLayout(new GridLayout(0, 2)); // amount of panels in same collumn

	     }
	}
	
	private void addContraints(int m,int n)
	{ 
		
		mainPanel.add(mainCont.LeftPanel);
		
        if (n==2) 
        {
        	mainPanel.add(mainCont.graphPanel.dPanel);
        }
          
    }
	
	
}
