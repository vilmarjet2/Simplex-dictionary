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
import javax.swing.JTextField;

public class FrameConfProbrlem extends JFrame {
	
	int nbVar = 0;
	int nbConst = 0;
	MainFrame frame;
	
	public FrameConfProbrlem (MainFrame frameOrig)
	{
		this.frame = frameOrig;
		set_FrameConfProbrlem();
		addingPanelsBF( );
	}
	
	public void set_FrameConfProbrlem()
	{

        //myFrame.dispose();
        setName("Problem options");
        setSize(400,200);
        setLayout(new GridLayout(3, 1));
        addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            dispose();
         }        
      });
       
       setVisible(true);
       
    }
	
	public void addingPanelsBF() 
	{

		JLabel msglabel = new JLabel("Data of your problem", JLabel.CENTER);
		

		// First Panel
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.add(new JLabel(" Number of variables:"));
		JTextField text1 = new JTextField(3);
		text1.setText("2");
		panel1.add(text1);

		// Second Panel
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.add(new JLabel(" Number of constrants:"));
		JTextField text2 = new JTextField(3);
		text2.setText("3");
		panel2.add(text2);

		// Panel (Button)
		JButton butt = new JButton("Start");
		butt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Taking the contrait that activated by button
				// text1.getText();
				try {
					nbVar = Integer.parseInt(text1.getText());
				} catch (Exception ex) {
					nbVar = 0;
				}

				try {
					nbConst = Integer.parseInt(text2.getText());
				} catch (Exception ex) {
					nbConst = 0;
				}

				if ((nbVar <= 0) || (nbConst <= 0)) {
					new My_WarningFrame("Number of Variables and/or Constrants is forbidden !");
				} else {
					// Do myframe
					dispose();
					frame.settingDicFrame(nbConst, nbVar);   
				}
			} // end actionPerformed smallButt
		});

		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		panel3.add(butt);

		add(panel1);
		add(panel2);
		add(panel3);

	}

}
