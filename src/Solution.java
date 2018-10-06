
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JTextField;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jefte
 */
public class Solution {
    Vector<JTextField>     vecTex;
    Vector<Double>         d_val;
    Vector<Integer>        numerat;
    Vector<Integer>        denominat;
    int                    nb_var;
    
    public Solution (){
       //do nothing 
    }
    
    public Solution (int n){ // m = nb constrants n=nb variables 
        nb_var = n;
        initialization ();
    }
    public void initialization (){        
        vecTex = new  Vector<JTextField>(nb_var+1);
        d_val = new Vector<Double>(nb_var+1);
        numerat = new Vector<Integer>(nb_var+1);
        denominat = new Vector<Integer>(nb_var+1); 
        
        for (int i=0; i <=nb_var; i++) {
             JTextField textVal = new JTextField(3);
             textVal.setText(" 0 ");
            
            vecTex.addElement(textVal);
            d_val.addElement(0.0);
            numerat.addElement(0);
            denominat.addElement(0);       
        }
        
        
        
    }
}//end class Solution
