import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ProblemModel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	boolean         Div0 = false;
	boolean         fraction = true;
	
	int	rows = 0;
    int columns = 0;
    
    String type;
    
    
    public ArrayList<ArrayList<JButton> >   matButt;
    public ArrayList<JButton>         		vecButt;
    public ArrayList<JPanel>               	vecPanel;
    public ArrayList< ArrayList<Variable>>  vecCont;
    public ArrayList<Variable> 				vecVar; // using in Objective fonction
    public ArrayList<Variable>             	funcObj;
    public ArrayList< ArrayList<Variable>>  startvecCont;
    public ArrayList<Variable>             	startfuncObj;
    
    
    
    MyVarButtHandler buttVariablehandler;
    MyStartButtHandler buttStarthandler;
    
    
    public JRadioButton     selcFrac;
    public JRadioButton     selcDec;
    
    
    JButton          lastButt;
    JButton          random;
    
    JLabel msg2;
    JLabel msg3;
	
	public ProblemModel()
	{
		//do nothing 
	}
	
	public ProblemModel(int m, int n)
	{
		parametersPanel(m);
		
		initialization(m,n);
		
	}
	
	
	private void parametersPanel(int m)
	{
		setLayout(new GridLayout(rows + 4, 0));//for each row we have a different Panel
//		MainFrame.changeFont(this,new java.awt.Font("Century Schoolbook L", 2, 14) );
	}
	
	private void initialization(int m, int n)
	{    
		/*
				Initializations
		*/
		//Variables
		rows = m;
		columns = n + 1; // We have to add the slack variables in the begin
		type = "Min";
		
		
		buttVariablehandler = new MyVarButtHandler(this);
		buttStarthandler = new MyStartButtHandler(this);
		
		vecPanel = new ArrayList<JPanel>(rows + 4);
		funcObj = new ArrayList<Variable>(columns + 1); // Objective function
		matButt = new ArrayList<ArrayList<JButton>>(rows + 1); // Matrice of buttons
		vecCont = new ArrayList<ArrayList<Variable>>(rows + 1);
		startvecCont = new ArrayList<ArrayList<Variable>>(rows + 1);
		startfuncObj = new ArrayList<Variable>(columns + 1);

		for (int i = 0; i <= rows + 2; i++) {
			JPanel auxPanel = new JPanel();
			vecVar = new ArrayList<Variable>(columns + 1);
			ArrayList<Variable> auxvecVar = new ArrayList<Variable>(columns + 1);
			vecButt = new ArrayList<JButton>(columns + 1);

			matButt.add(vecButt);
			vecCont.add(vecVar);

			startvecCont.add(auxvecVar);
			vecPanel.add(auxPanel);
		}

		initialize_FuncObject();
		initialize_Variables();
		initialize_RadioButt();
//		initialize_WarnningFrame();
		initialize_LastButt();
		initialize_RandomButton();
		setValuesFraction(false);
		setEnableRadioButtonFrac(false);

		/*
		 * vecCont.get(0).get(0).setValueD(2); vecCont.get(0).get(1).setValueD(2);
		 * vecCont.get(0).get(2).setValueD(-1);
		 * //vecCont.get(0).get(3).setValueD(-0.21); vecCont.get(1).get(0).setValueD(3);
		 * vecCont.get(1).get(1).setValueD(1); vecCont.get(1).get(2).setValueD(-1);
		 * //vecCont.get(1).get(3).setValueD(-0.12);
		 * 
		 * vecCont.get(2).get(0).setValueD(3); vecCont.get(2).get(1).setValueD(-1);
		 * vecCont.get(2).get(2).setValueD(0); //vecCont.get(2).get(3).setValueD(-0.52);
		 */

		setEditableText(true);
		setEditableButtons(false);
		setValuesFraction(false);
		addingInPanels_CanonicalProblem(vecPanel, matButt, vecCont, funcObj, rows, columns);
    }//end initialization
	
	
	public void initialize_RandomButton ()
	{
        
        random = new JButton("Creat Random");
        random.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {  
                        set_RandonVariable();
                        addingInPanels(vecPanel, matButt, vecCont, funcObj,rows, columns);
                    } //end actionPerformed smallButt
                   });
    }
	
	public void set_RandonVariable()
    {
        for (int ii=0; ii<rows; ii++)
            for (int jj=0; jj<columns; jj++){
                Random gerador = new Random();//int numero = gerador.nextInt(10) + 10;
                if (jj != 0){
                    vecCont.get(ii).get(jj).setValue(-1*(gerador.nextInt(9)+1));
                }else
                    vecCont.get(ii).get(jj).setValue(gerador.nextInt(6)+1);
            }
        
        //Function objectf 
       for (int jj=0; jj<columns; jj++){
            Random gerador = new Random();//int numero = gerador.nextInt(10) + 10;
            if (jj != 0){
                funcObj.get(jj).setValue(-1*(gerador.nextInt(9)+1));
            }else
                funcObj.get(jj).setValue((gerador.nextInt(5)+1));
        }
    }
	
	public void initialize_FuncObject()
	{
        Variable var, auxvar;
        for(int j=0; j<columns;j++){
            var = new Variable (-1,"x"+j);
            auxvar = new Variable (); 
            if (j == 0)
                var.setLabelName("z");
            
            var.setPosStart(j,-1);
            
             //adding button and var in matrix
             funcObj.add(var);
             startfuncObj.add(auxvar);
        }//end for j
        
        new ArrayList<Variable>(columns+1);
    }
	
	
	public void initialize_Variables()
	{
        JButton butt = new JButton("x");
        Variable var;
        Variable auxvar ;
        
              
        for (int i=0; i<rows; i++)
            for(int j=0; j<columns;j++){
                auxvar = new Variable ();
                 //adding button and var in matrix
                startvecCont.get(i).add(auxvar);
            }
            
        //Button funcitons in the constraints
        for (int i=0; i<rows; i++){
            for(int j=0; j<columns;j++){
                butt = new JButton("x"+j);
                var = new Variable (i,"x"+j);
                var.setPosStart(j,i);
                butt.setName(""+i);
                
                if (j == 0) {
                    butt.setLabel("s"+i);
                    var.setLabelName("s"+i);
                }
                
                //add funtion to buttons 
                 butt.addActionListener(buttVariablehandler);
                 
                 //adding button and var in matrix
                 matButt.get(i).add(butt);
                 vecCont.get(i).add(var);           
            }//end for j
      }//end for i
    }
	
	public  void initialize_RadioButt(){
        selcFrac = new JRadioButton("Fraction") ;
        selcDec = new JRadioButton("Decimal");
        
        
        
        setValuesFraction(true);
        
        selcFrac.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //System.out.println(vecCont.get(0).get(1).textVal.getText());
                        setValuesFraction(true);
                        addingInPanels(vecPanel, matButt, vecCont,funcObj,rows, columns);
                    } //end actionPerformed smallButt
                   });
        selcDec.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //System.out.println(vecCont.get(0).get(1).textVal.getText());
                        setValuesFraction(false);
                        addingInPanels(vecPanel, matButt, vecCont,funcObj,rows, columns);
                    } //end actionPerformed smallButt
                   });
    }
	
	
	public void initialize_LastButt()
    {
        lastButt = new JButton("Start");
        lastButt.addActionListener(buttStarthandler);
        
    }
	
	
	public void setValuesFraction(boolean t){
        selcFrac.setSelected(t);
        changingVariableFrac(t);
        fraction = t;
        selcDec.setSelected(!t);
    }
    
    public void changingVariableFrac(boolean t){
        for (int ii=0; ii<this.rows; ii++)
            for (int jj=0; jj<this.columns; jj++){
                vecCont.get(ii).get(jj).setOptionFrac(t);
            }
        for (int jj=0; jj<this.columns; jj++){
                funcObj.get(jj).setOptionFrac(t);
            }
    }
    
    
	
	
	public void addingInPanels(ArrayList<JPanel> vecPanel,  final ArrayList<ArrayList<JButton> > matButt, 
            final ArrayList< ArrayList<Variable> > vecCont, final ArrayList<Variable>  funcObj,
            final int m, final int n )
	{
        /*
       * 
       * ADDING fonction objective
       * 
       * */
		removeAll();
      
      int cont = 0;
      vecPanel.get(cont).removeAll();
      vecPanel.get(cont).add(new JLabel (type +" z = "));
      for (int jj=0; jj<n; jj++){
          JLabel msg = new JLabel(funcObj.get(jj).name);
          if (jj != 0){
                vecPanel.get(cont).add(funcObj.get(jj).textVal);
                vecPanel.get(cont).add(msg);
                if (jj != n-1)
                 vecPanel.get(cont).add(new JLabel (" + "));
            }else{
                vecPanel.get(cont).add(funcObj.get(jj).textVal);
                vecPanel.get(cont).add(new JLabel (" + "));
            }
      }
      add(vecPanel.get(cont));
      
      /*
       * 
       * ADDING Contraints
       * 
       * */ 
      for (int ii=0; ii<m; ii++){
          cont++;
          vecPanel.get(cont).removeAll();
          for (int jj=0; jj<n; jj++){
             if (jj != 0){
                 vecPanel.get(cont).add(vecCont.get(ii).get(jj).textVal);
                 vecPanel.get(cont).add(matButt.get(ii).get(jj));
                 if (jj != n-1)
                  vecPanel.get(cont).add(new JLabel (" + "));
             }else{
                 vecPanel.get(cont).add(matButt.get(ii).get(jj));
                 vecPanel.get(cont).add(new JLabel (" = "));
                 vecPanel.get(cont).add(vecCont.get(ii).get(jj).textVal);
                 vecPanel.get(cont).add(new JLabel (" + "));
             }
          }//end for jj
          add(vecPanel.get(cont));
      }// end for ii
      
      cont++;
      vecPanel.get(cont).removeAll();
      vecPanel.get(cont).add(selcFrac);
      vecPanel.get(cont).add(selcDec);
      vecPanel.get(cont).add(random);
      add(vecPanel.get(cont));

          
      
      cont++;
      vecPanel.get(cont).removeAll();
      vecPanel.get(cont).add(lastButt); // start or 
      add(vecPanel.get(cont));
  }//end modifyingPanels
	
	
	
	public void setEditableText(boolean t)
	{
        
        for (int ii=0; ii<rows; ii++)
            for (int jj=0; jj<columns; jj++)
                vecCont.get(ii).get(jj).setEditableText(t);
        //Function objectf 
       for (int jj=0; jj<columns; jj++)
            funcObj.get(jj).setEditableText(t);  
    }
    
    public void setEditableButtons(boolean bln)
    {
        for (int ii=0; ii<rows; ii++)
            for (int jj=0; jj<columns; jj++)
                matButt.get(ii).get(jj).setEnabled(bln); 
    }
    
    public void setEnableRadioButtonFrac(boolean bln)
    {
        selcFrac.setEnabled(bln);
        selcDec.setEnabled(bln);
        random.setEnabled(!bln);            
    }  
    
    public void restartProblem()
    {
        //constrants copy from first problem
        for (int ii=0; ii<rows; ii++)
            for (int jj=0; jj<columns; jj++)
                vecCont.get(ii).get(jj).copyFromVariable(startvecCont.get(ii).get(jj));
        //buttons copy from first problem
        for (int ii=0; ii<rows; ii++)
            for (int jj=0; jj<columns; jj++)
                matButt.get(ii).get(jj).setLabel(vecCont.get(ii).get(jj).getLabel());
        
        //function objective copy from first problem
        for(int jj=0; jj<columns;jj++)
             funcObj.get(jj).copyFromVariable(startfuncObj.get(jj));
    }//restart problem 
	
    
    public void addingInPanels_CanonicalProblem(ArrayList<JPanel> vecPanel,  final ArrayList<ArrayList<JButton> > matButt, 
            final ArrayList< ArrayList<Variable> > vecCont, final ArrayList<Variable>  funcObj,
            final int m, final int n )
	{
        /*
       * 
       * ADDING fonction objective
       * 
       * */
		removeAll();
		msg3= new JLabel();
		msg3.setText("<html><center>" + MainFrame.vocabulary.getText_index(4) + ": </center></html>");
//		msg3.setFont(new Font("Serif", Font.PLAIN, 20));
      int cont = 0;
      vecPanel.get(cont).removeAll();
      vecPanel.get(cont).add(msg3);
      vecPanel.get(cont).add( new JLabel(type +" z = " ));

      for (int jj=0; jj<n; jj++){
          JLabel msg = new JLabel(funcObj.get(jj).name);
          if (jj != 0){
                vecPanel.get(cont).add(funcObj.get(jj).textVal);
                vecPanel.get(cont).add(msg);
                if (jj != n-1)
                 vecPanel.get(cont).add(new JLabel(" + "));
            }else{
                vecPanel.get(cont).add(funcObj.get(jj).textVal);
                vecPanel.get(cont).add(new JLabel(" + "));
            }
      }
      add(vecPanel.get(cont));
      
      /*
       * 
       * ADDING Contraints
       * 
       * */ 
      for (int ii=0; ii<m; ii++){
          cont++;
          vecPanel.get(cont).removeAll();
          for (int jj=1; jj<n; jj++)
          {
                 vecPanel.get(cont).add(vecCont.get(ii).get(jj).textVal);
                 vecPanel.get(cont).add(matButt.get(ii).get(jj));
                 if (jj != n-1)
                  vecPanel.get(cont).add(new JLabel(" + "));
          }//end for jj
          
          {//for slack variable 
        	  int jj = 0;
//              vecPanel.get(cont).add(matButt.get(ii).get(jj));
              vecPanel.get(cont).add(new JLabel (" <= "));
              vecPanel.get(cont).add(vecCont.get(ii).get(jj).textVal); //seems ok 
              vecPanel.get(cont).add(new JLabel ("  (s"+cont +")"));
          }
          
          add(vecPanel.get(cont));
      }// end for ii
      
      cont++;
      vecPanel.get(cont).removeAll();
      vecPanel.get(cont).add(selcFrac);
      vecPanel.get(cont).add(selcDec);
      vecPanel.get(cont).add(random);
      add(vecPanel.get(cont));

          
      
      cont++;
      vecPanel.get(cont).removeAll();
      vecPanel.get(cont).add(lastButt); // start or 
      add(vecPanel.get(cont));
  }//end modifyingPanels
	
    
    
    
}
