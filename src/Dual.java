
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jefte
 */
public class Dual {
    
    JFrame      myFrame;
    int         rows;
    int         columns;
    
    public Vector< Vector<Variable>>    vecCont;
    public Vector<Variable>             funcObj;
    public Vector<JPanel>               vecPanel;
    
    public Dual (){
        //faire rien
    }
    
    public Dual (int m, int n){
        rows = m;
        columns = n;
        
        vecPanel = new Vector<JPanel>(rows+3);
        vecCont = new Vector <Vector<Variable>>(rows+1);
        funcObj = new Vector<Variable>(columns+1);
        
         for (int i=0; i<= rows+2; i++){
            JPanel auxPanel = new JPanel();
            Vector<Variable> vecVar = new Vector<Variable>(columns+1);
            Vector<Variable> auxvecVar = new Vector<Variable>(columns+1);
            
            vecCont.addElement(vecVar);
            vecPanel.addElement(auxPanel);
        }
        
        initialize_frame();
        initialize_FuncObject();
        //initialize_Variables();
        
    }
    
    public void initialize_frame(){
        myFrame = new JFrame("Dual problem");
        myFrame.setSize((columns+1)*125,(rows+3)*60);//comprimento vs largura 
        myFrame.setLayout(new GridLayout(rows+4, 1)); // amount of panels in same collumn
        myFrame.addWindowListener(new WindowAdapter() { //defining closure of frame
            public void windowClosing(WindowEvent windowEvent){
                myFrame.dispose();
            }
        });    
    }
    
        public void initialize_FuncObject(){
        Variable var, auxvar;
        for(int j=0; j<columns;j++){
            var = new Variable (rows,"y"+j);
            auxvar = new Variable (); 
            if (j == 0)
                var.setLabelName("z''");
            
             //adding button and var in matrix
             funcObj.addElement(var);
        }//end for j
        
        new Vector<Variable>(columns+1);
    }
    
    
}//end class Dual 
