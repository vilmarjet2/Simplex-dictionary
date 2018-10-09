import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class My_MainPanel extends JPanel {
	
	public  MainFrame mainframe;
	private int nbVar = 0;
	JLabel msg;
	Vocabulary voc ;

	public My_MainPanel()
	{
		setParameters();
		
		FirstMsg();
	}
	
	public void setParameters()
	{
		Border blackline;
		blackline = BorderFactory.createLineBorder(Color.black);
		
		BorderFactory.createTitledBorder(blackline, "Title of my Panel");
	}
	
	
	public void setPanelProblem(int m, int n, MainFrame frame )
	{
		this.mainframe = frame;
		nbVar = n;
		//Clean Panel
		removeAll();
		repaint();
		
		//Size depends on number of Variables and Constraints
        settingsFrame();
        
        
        //Declaration (Build problem)
        mainframe.mainCont = new Contraint(m,n);
        
        //adding Panels from Problem 
        addPanel_Problem();
        
        //Change graphc representation of panel
        setVisible(true);
        revalidate(); // Applying changes 
        
    }
	

	private void settingsFrame()
	{
	     if (nbVar != 2)
	     {   
	        setLayout(new GridLayout(1, 0)); // amount of panels in same collumn
	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        setSize((int)screenSize.getWidth() , (int)screenSize.getHeight());
	     }
	     else
	     { // we have to add the size of Grafic
	    	 setLayout(new GridLayout(0, 2)); // amount of panels in same collumn

	     }
	}
	
	private void addPanel_Problem( )
	{ 
		add(mainframe.mainCont.LeftPanel);
		
        if (nbVar==2) 
        {
        	add(mainframe.mainCont.graphPanel.dPanel);
        }
    }
	
	
	private void FirstMsg()
	{
/*		msg = new JLabel( mainframe.vocabulary.getText_index(1) + "\n" + 
						  mainframe.vocabulary.getText_index(2) + "\n" +
						  mainframe.vocabulary.getText_index(3));*/
		msg = new JLabel("");
		
//		System.out.println("Size da crianca = " + MainFrame.vocabulary.size() + " set=" + MainFrame.vocabulary.getText_index(1));
		
		msg.setText( "<html><center>" + MainFrame.vocabulary.getText_index(1) + "<br>" + 
				MainFrame.vocabulary.getText_index(2) + "<br>" +
				MainFrame.vocabulary.getText_index(3) + "</center></html>");
		msg.setFont(new Font("Serif", Font.PLAIN, 25));
		add(msg);
	}

	
}
