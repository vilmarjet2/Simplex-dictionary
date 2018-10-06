
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jefte
 */
public class Variable {
    int contr,  //Start contraint
        column; // Start position in contraint
    String  name;
    String  label;
    boolean act;
    double  value;
    JTextField textVal ;
    
    long     numerat;
    long     denominat;
    boolean frac;
    boolean editable;
    
    
    public Variable (int m, String nm){
        contr = m;
        column = m;
        name = nm;
        label = nm;
        textVal = new JTextField(5);
        textVal.setText("0");
        value = 0.0;
        frac = false;
        textVal.setEditable(true); 
        numerat = 0;
        denominat = 1;
    }
    
    public Variable (){
        contr = 0;
        name = "";
        label = "";
        textVal = new JTextField(3);
        textVal.setText(" 0 ");
        frac = false;
        textVal.setEditable(false);
    }
    
    public void copyFromVariable(Variable var){
        
        contr = var.contr;  //Start contraint
        column = var.column;// start column

        name = var.name;
        label = var.name;
        act = var.act;
        value = var.value;

        setValueFrac(var.numerat, var.denominat);

        numerat = var.numerat;
        denominat = var.denominat;
        frac = var.frac;
        editable= var.editable;
    }
    
    public void setPosStart (int col, int con){
        column = col;
        contr = con;
    }
    public void setLabelName (String mn){
        name = mn;
        label = mn;
    }
    
    public void set_LabelNamePosition (Variable var){
        setPosStart (var.column, var.contr);
        setLabelName(var.name);
    }
    
    public int getColumn(){
        return column;
    }
    
    public int getContrant (){
        return contr;
    }
    
    public void setColumn(int i ){
        column = i;
    }
    
    public void setEditableText (boolean t){
        textVal.setEditable(t); 
    }
    
    public void setOptionFrac (boolean t){
        this.frac = t;
        setText();
    }
    public void setValue (int v){
        value =  v;
        numerat = v;
        denominat = 1;
        setText();
    }
    
    public void setValueD (double v){
        value =  v;
        toFraction(v);
        simplificationPrimes();
        setText();
    }
    
    public void setValueFrac (long num, long den){
        if (den <0){
            this.numerat = -num;
            this.denominat = -den;
        }else{
            this.numerat = num;
            this.denominat = den;
        }
      
        this.value =  (double)numerat/(double)denominat; 
        simplificationPrimes();
        setText();
    }
    
    public String getLabel (){
        return this.label;
    }
    
    public double getValue (){
        return value;
    }
    
    public long getValueNum(){
        return numerat;
    }
    public long getValueDen(){
        return denominat;
    }
    
    public void setName(String nm){
        this.name = nm;
    }
   
    public void setLabel(String nm){
        this.label = nm;
    }
    
    public void setText(){
        if (this.denominat < 0){
            System.out.println("Erro denominat NEG  = "+ this.denominat + "... num = "+this.numerat);
        }
        
        if (frac == true){
            if (this.denominat == 1){
                textVal.setText(String.valueOf(numerat));
            }else{
                textVal.setText(String.valueOf(this.numerat + "/" + this.denominat));
            } 
        }else {
            double val = (double)numerat/(double)denominat;
            //textVal.setText(String.format("%.2f", val)); 
            textVal.setText( new DecimalFormat("0.00",DecimalFormatSymbols.getInstance(Locale.US)).format(val)); 
            
            //textVal.setText(String.valueOf(val));
        }
    }
    
    public void simplificationPrimes(){
        if (denominat > 1){
            //try to divide 
            if ((numerat % denominat) == 0){
                 numerat = numerat/denominat;
                 denominat = 1;
            }else if((denominat % numerat) == 0){
                if (numerat > 0){
                    denominat = denominat/numerat;
                    numerat = 1;
                }else{
                    denominat = -denominat/numerat;
                    numerat = -1;
                }
                
            }   
            
            Vector<Integer> primes = new Vector<Integer>(15);
            primes.addElement(47);primes.addElement(43);primes.addElement(41);primes.addElement(37);primes.addElement(31);
            primes.addElement(29);primes.addElement(23);primes.addElement(19);primes.addElement(17);primes.addElement(13);
            primes.addElement(11);primes.addElement(7);primes.addElement(5);primes.addElement(3);primes.addElement(2);
            
            //System.out.println("Tamanho:"+primes.size());
            for (int i=0; i<primes.size();i++){
                if (((numerat % primes.get(i)) == 0)&&((denominat % primes.get(i)) == 0)){
                    //System.out.println(numerat+"/"+denominat+"... prime="+primes.get(i));
                    numerat = (int) Math.round((double)numerat/(double)primes.get(i));
                    denominat = (int) Math.round((double)denominat/(double)primes.get(i));
                    i--;
                }
            }
        }
    }
            
   //from site http://stackoverflow.com/questions/14014158/double-to-fraction-in-java
    
    public String getText (){
        return textVal.getText();
    }
    public void toFraction (double val){   
        double  x;
        int     sig = +1;
        if (val<0){
            x = -val;
            sig = -1;
        }else{
            x = val;
        }
                
        double  tolerance = 1.0E-6;
        double h1=1; double h2=0;
        double k1=0; double k2=1;
        double b = x;
        do {
            double a = Math.floor(b);
            double aux = h1; h1 = a*h1+h2; h2 = aux;
            aux = k1; k1 = a*k1+k2; k2 = aux;
            b = 1/(b-a);
        } while (Math.abs(x-h1/k1) > x*tolerance);

         this.numerat = (int)sig*(long)h1;
         this.denominat = (long)k1;

    }
    
    
    
}
