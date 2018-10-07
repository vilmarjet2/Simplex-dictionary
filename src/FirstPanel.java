import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FirstPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FirstPanel() 
	{
		this.setLayout(new FlowLayout());
	}

	public FirstPanel(int larg, int comp) 
	{

		this.setLocation(larg, comp);
		this.setLayout(new FlowLayout());
	}

	public void creatButton_ok(JFrame myframe,JLabel msg) 
	{

		JButton okButton = new JButton("Opem Conf. Frame");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myframe.setVisible(true);
				msg.setText("There is a windows open");
			}
		});

		this.add(okButton);
	}

}// end of classe
