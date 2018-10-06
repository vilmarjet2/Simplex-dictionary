
import java.awt.FlowLayout;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jefte
 */
public class ControlPanel {
    
    public int cont;
    public JPanel controlPanel;
    
    public ControlPanel (int n){
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        cont = n;
    }
    
    public JPanel vecPanel (){
        return controlPanel;
    }
    
}
