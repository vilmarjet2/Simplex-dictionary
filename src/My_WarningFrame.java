import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class My_WarningFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean rep = false; 

	public My_WarningFrame (String str)
	{
		
//		removeAll();
	    settingWarnningFrame();
	    //First Panel (show message)
	    JPanel panel1 = new JPanel();
	    panel1.setLayout(new FlowLayout());
	    panel1.add(new JLabel (str));
	    
	    
	    //Ok button (to close)
	    JButton butt = new JButton("OK");
	    
	    butt.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                	removeAll();
	                    dispose();
	                    rep = true;
	                } //end actionPerformed smallButt
	               });
	    
	    //Second Panel (to set ok button)
	    JPanel panel2 = new JPanel();
	    panel2.setLayout(new FlowLayout());
	    panel2.add(butt);
	    
	    panel1.setVisible(true);
	    panel2.setVisible(true);
	    
	    add(panel1);
	    add(panel2);
	}
	
	
	public void settingWarnningFrame()
	{
		setTitle("Warnning !!! ");
        setSize(400,100);
        setLayout(new GridLayout(2, 1));
        addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            dispose();
            rep =true;
         }        
      });
        
        setVisible(true);
    }
	
	
	public Boolean getReponse()
	{
		return rep;
	}

}
