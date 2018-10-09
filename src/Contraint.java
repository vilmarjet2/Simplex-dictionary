
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jefte
 */
public class Contraint {
   
    ArrayList<JButton>         vecButt;
    public JRadioButton     selcFrac;
    public JRadioButton     selcDec;
 
    
    public ArrayList<Variable> vecVar; // using in Objective fonction
    
    //***** Two main Panels in our application
    public JPanel    LeftPanel;  //Where the problem is presented 
    public PlotGraph graphPanel; //To plot graphic (just for 2 variables)... Override of JPanel
    
    
    //public JPanel graphPanel; //will store the graphs 
    public ArrayList<ArrayList<JButton> >     matButt;
    public ArrayList< ArrayList<Variable>>    vecCont;
    public ArrayList<JPanel>               vecPanel;
    int              rows;
    int              columns;
    public ArrayList<Variable>             funcObj;
    String           type;
    JButton          lastButt;
    JButton          random;
    private JFrame   warningFrame;
    boolean         fraction;
    boolean         Div0;
    Dual            dual;
    double          epsilon = 0.00001;
    
    //save the first problem
    public ArrayList< ArrayList<Variable>>    startvecCont;
    public ArrayList<Variable>             startfuncObj;
    
    public Contraint (int m, int n)
    {
        initialization(m, n);
        
    }
    
    public Contraint (){
       //do nothing
    }
    
    public JPanel getPanel(int ii){
        return vecPanel.get(ii);
    }
    
    private void initialization(final int m,final int n){    
        //Declarations
    	LeftPanel = new JPanel();
        LeftPanel.setLayout(new GridLayout(m+3, 0));
        if (n == 2)
            graphPanel = new PlotGraph();
        
        rows  = m;
        columns = n+1; //We have to add the slack variables in the begin
        type = "Min";
        
        
//        Arrays of Buttons, Variables and Panels
        vecPanel = new ArrayList<JPanel>(rows+4); 
        funcObj = new ArrayList<Variable>(columns+1); // Objective function
        matButt = new ArrayList<ArrayList<JButton> > (rows+1);  // Matrice of buttons 
        vecCont = new ArrayList <ArrayList<Variable>>(rows+1); 
        startvecCont = new ArrayList <ArrayList<Variable>>(rows+1);
        startfuncObj = new ArrayList<Variable>(columns+1);
        
        for (int i=0; i<= rows+2; i++)
        {
            JPanel auxPanel = new JPanel();
            vecVar = new ArrayList<Variable>(columns+1);
            ArrayList<Variable> auxvecVar = new ArrayList<Variable>(columns+1);
            vecButt = new ArrayList<JButton>(columns+1);
            
            matButt.add(vecButt);
            vecCont.add(vecVar);
            
            startvecCont.add(auxvecVar);
            vecPanel.add(auxPanel);
        }
         
        
        initialize_FuncObject();
        initialize_Variables();
        initialize_RadioButt();
        initialize_WarnningFrame();
        initialize_LastButt ();
        initialize_RandomButton ();
        setValuesFraction(false);
        setEnableRadioButtonFrac(false);
       
        
/*        vecCont.get(0).get(0).setValueD(2);
        vecCont.get(0).get(1).setValueD(2);
        vecCont.get(0).get(2).setValueD(-1); 
        //vecCont.get(0).get(3).setValueD(-0.21);
        vecCont.get(1).get(0).setValueD(3);
        vecCont.get(1).get(1).setValueD(1);
        vecCont.get(1).get(2).setValueD(-1); 
        //vecCont.get(1).get(3).setValueD(-0.12);
        
        vecCont.get(2).get(0).setValueD(3);
        vecCont.get(2).get(1).setValueD(-1);
        vecCont.get(2).get(2).setValueD(0); 
        //vecCont.get(2).get(3).setValueD(-0.52);
        */
        
        setEditableText(true);
        setEditableButtons(false);
        setValuesFraction(false);
        addingInPanels(vecPanel, matButt, vecCont, funcObj,rows, columns);
    }//end initialization
        
    
    public void initialize_WarnningFrame(){
        warningFrame = new JFrame("Warnning !!! ");
        warningFrame.setSize(400,100);
        warningFrame.setLayout(new GridLayout(2, 1));
        warningFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            warningFrame.dispose();
         }        
      });
    }
    
    public void initialize_LastButt()
    {
        lastButt = new JButton("Start");
        lastButt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                String nameActButt = e.getActionCommand(); //aB = ActiveButton
                
                if (nameActButt.equals("Start")){
                    int verif = 0; 
                    verif = gettingFuncObj();
                    if (verif == 1 )
                        verif = getConstrants();
        
                    if (verif == 1){
                        
                        lastButt.setLabel("Edit/Restart");
                        //System.out.println(vecCont.get(0).get(1).textVal.getText());
                        setValuesFraction(false);
                        addingInPanels(vecPanel, matButt, vecCont,funcObj,rows, columns);
                        setEditableText(false);
                        setEditableButtons(true);
                        setEnableRadioButtonFrac(true);
                        copyToStartVariables();
                        if (columns-1 == 2)//nb
                            setGraph(vecPanel, matButt, vecCont,funcObj,rows, columns);
                    }else{
                        addingWarrningFrame("All numbers must be Integer or Double (ex: 1.5)!");
                    }
                }else{
                    lastButt.setLabel("Start"); 
                    setEditableText(true);
                    setEditableButtons(false);
                    setValuesFraction(false);
                    restartProblem();
                    addingInPanels(vecPanel, matButt, vecCont,funcObj,rows, columns);
                    setEnableRadioButtonFrac(false);
                    
                }   
            } //end actionPerformed smallButt
           });
        
    }
    
    public void initialize_RandomButton (){
        
        random = new JButton("Creat Random");
        random.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {  
                        set_RandonVariable();
                        addingInPanels(vecPanel, matButt, vecCont, funcObj,rows, columns);
                    } //end actionPerformed smallButt
                   });
    }
    
    
    public void restartProblem(){
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
    }
    
    public void copyToStartVariables(){
        for (int ii=0; ii<rows; ii++)
            for (int jj=0; jj<columns; jj++)
                startvecCont.get(ii).get(jj).copyFromVariable(vecCont.get(ii).get(jj)); 
        
        for(int jj=0; jj<columns;jj++)
             startfuncObj.get(jj).copyFromVariable(funcObj.get(jj));
    }
    
    public void setEditableText(boolean t){
        
        for (int ii=0; ii<rows; ii++)
            for (int jj=0; jj<columns; jj++)
                vecCont.get(ii).get(jj).setEditableText(t);
        //Function objectf 
       for (int jj=0; jj<columns; jj++)
            funcObj.get(jj).setEditableText(t);  
    }
    
    public void setEditableButtons(boolean bln){
        for (int ii=0; ii<rows; ii++)
            for (int jj=0; jj<columns; jj++)
                matButt.get(ii).get(jj).setEnabled(bln); 
    }
    
//    public void setValueDualPrimal(){
//        float zP, wD;
//        
//    }
    
     public void addingWarrningFrame(String str){
        warningFrame.dispose();
        warningFrame.removeAll();
        
        initialize_WarnningFrame();
        //First Panel 
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(new JLabel (str));
        
        
        JButton butt = new JButton("OK");
        
        butt.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        warningFrame.dispose();
                    } //end actionPerformed smallButt
                   });
        //Second Panel 
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(butt);
        
        warningFrame.add(panel1);
        warningFrame.add(panel2);
        warningFrame.toFront();
        warningFrame.setVisible(true);
        
    }
    
    public int getConstrants(){
         double auxD;
        for (int ii=0; ii<rows; ii++)
            for (int jj=0; jj<columns; jj++){
                try {   
                    auxD = Double.parseDouble(vecCont.get(ii).get(jj).getText());
                } catch (Exception ex) {return 0;}
                vecCont.get(ii).get(jj).setValueD(auxD);
            }
        
        return 1;
    }//end getConstrants
    
    public int gettingFuncObj(){
        double auxD; 
        for (int jj=0; jj<columns; jj++){
             try {
                auxD = Double.parseDouble(funcObj.get(jj).getText());
            } catch (Exception ex) {return 0;}
             
            funcObj.get(jj).setValueD(auxD);
        }
        
                        
        /* for (int jj=0; jj<columns; jj++){
            double nbVar;
            try {
                nbVar = Double.parseDouble(.getText());
            } catch (Exception ex) {nbVar =0;}
        }
        */
        return 1;
    }//end gettingfuction objective
    
    public void set_RandonVariable(){
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
    
    public void setEnableRadioButtonFrac(boolean bln){
        selcFrac.setEnabled(bln);
        selcDec.setEnabled(bln);
        random.setEnabled(!bln);            
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
    
    public void initialize_FuncObject(){
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
    
    public void initialize_Variables(){
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
                 butt.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //Taking the constraint that is activated by button
                        String activeCont = null;
                         try {
                             for (int ii=1;ii < 4; ii++){ 
                                String auxStri = e.toString().substring(e.toString().length() - ii);
                                int AUXint = Integer.parseInt(auxStri);//if it is not integer it will cause a problem
                                activeCont = auxStri; // it will be accepted just if auxStri is a integer (number of panel) 
                             }
                        } catch (Exception ex) {}//do nothing
                            
                        int aC = Integer.parseInt(activeCont);
                        String activeButton = e.getActionCommand(); //aB = ActiveButton (name of the variable)
                       
                        changingContraintS (matButt, vecCont,activeButton, aC, rows, columns);
                        
                        if (!Div0){
                            changingFunction (matButt, vecCont, funcObj,activeButton,rows, columns);
                            //changingFunctionFrac (matButt, vecCont, funcObj,activeButton,rows, columns);
                            addingInPanels(vecPanel, matButt, vecCont,funcObj,rows, columns);
                            //vecCont.get(0).get(2).textVal.setText("3");
                            //System.out.println(vecCont.get(0).get(1).textVal.getText());
                            
                            System.out.println("column:" + funcObj.get(1).column + "contraint_origem"+ funcObj.get(1).contr);
                            
                        }else{
                            addingWarrningFrame("Undefined Division by 0 (zero) !! ");
                        }
  
                    } //end actionPerformed smallButt
                   });
                 
                 //adding button and var in matrix
                 matButt.get(i).add(butt);
                 vecCont.get(i).add(var);           
            }//end for j
      }//end for i
    }
    
    
    public void addingInPanels(ArrayList<JPanel> vecPanel,  final ArrayList<ArrayList<JButton> > matButt, 
              final ArrayList< ArrayList<Variable> > vecCont, final ArrayList<Variable>  funcObj,
              final int m, final int n ){
          /*
         * 
         * ADDING fonction objective
         * 
         * */
        LeftPanel.removeAll();
        
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
        LeftPanel.add(vecPanel.get(cont));
        
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
            LeftPanel.add(vecPanel.get(cont));
        }// end for ii
        
        cont++;
        vecPanel.get(cont).removeAll();
        vecPanel.get(cont).add(selcFrac);
        vecPanel.get(cont).add(selcDec);
        vecPanel.get(cont).add(random);
        LeftPanel.add(vecPanel.get(cont));

            
        
        cont++;
        vecPanel.get(cont).removeAll();
        vecPanel.get(cont).add(lastButt); // start or 
        LeftPanel.add(vecPanel.get(cont));
        
        
        
    }//end modifyingPanels
    
    
     public void changingFunction( final ArrayList<ArrayList<JButton>> matButt, final ArrayList< ArrayList<Variable> > vecCont, 
            ArrayList<Variable>  funcObj, String aB,final int  m, final int n){
        int auxI = -1;
        int auxIf = -1;
        
        //identifiing constrant
        for (int jj=0; jj<m; jj++){
            if (matButt.get(jj).get(0).getLabel().equals(aB)){
                auxI = jj;
                jj = m+1;
            }
        }//end for
        
        //identifiing in function
        for (int ii=1; ii<n; ii++){
            if (funcObj.get(ii).getLabel().equals(aB)){
                auxIf= ii;
                ii = n+1;
            }
        }//end for
        
        //System.out.println(aB + " na column fonc:" + auxIf + " will change " +auxI +" to "+ vecCont.get(auxI).get(auxIf).getLabel());
           
        if (auxI >= 0){
            double auxD = funcObj.get(auxIf).getValue();
            for (int ii=0; ii<n; ii++){
                double auxD2 = funcObj.get(ii).getValue(); //old value of variable in function
                double auxD3 = vecCont.get(auxI).get(ii).getValue();  // Value in new constraint
                if (ii != auxIf){ 
                    funcObj.get(ii).setValueD(auxD*auxD3 + auxD2); // changing values 
                }else{
                    funcObj.get(ii).setValueD(auxD*auxD3); // changing values
                    funcObj.get(ii).setLabelName(vecCont.get(auxI).get(ii).getLabel()); // changing values
                    funcObj.get(ii).setPosStart(vecCont.get(auxI).get(ii).getColumn(),vecCont.get(auxI).get(ii).getContrant()); // changing column and contrant of function
                }
            }
        }
     
        
                
             
    }//end changing function
    
    
    public void changingFunctionFrac( final ArrayList<ArrayList<JButton>> matButt, final ArrayList< ArrayList<Variable> > vecCont, 
            ArrayList<Variable>  funcObj,
            String aB,final int  m, final int n){
        int auxI = -1;
        int auxIf = -1;
        
        //identifiing constrant
        for (int jj=0; jj<m; jj++){
            if (matButt.get(jj).get(0).getLabel().equals(aB)){
                auxI = jj;
                jj = m+1;
            }
        }//end for
        
        //identifiing in function
        for (int ii=1; ii<n; ii++){
            if (funcObj.get(ii).getLabel().equals(aB)){
                auxIf= ii;
                ii = n+1;
            }
        }//end for
        
        if (auxI >= 0){
            long auxD_num = funcObj.get(auxIf).getValueNum();
            long auxD_den = funcObj.get(auxIf).getValueDen();
            for (int ii=0; ii<n; ii++){
                long auxD2_num = funcObj.get(ii).getValueNum(); //old value of variable in function
                long auxD2_den = funcObj.get(ii).getValueDen(); //old value of variable in function
                long auxD3_num = vecCont.get(auxI).get(ii).getValueNum(); 
                long auxD3_den = vecCont.get(auxI).get(ii).getValueDen(); 
                if (ii != auxIf){ 
                    funcObj.get(ii).setValueFrac(auxD_num*auxD3_num*auxD2_den + auxD2_num*auxD_den*auxD3_den, auxD_den*auxD3_den*auxD2_den); // changing values 
                }else{
                    funcObj.get(ii).setValueFrac(auxD_num*auxD3_num, auxD_den*auxD3_den); // changing values
                    funcObj.get(ii).setName(vecCont.get(auxI).get(ii).getLabel()); // changing values
                    funcObj.get(ii).setLabel(vecCont.get(auxI).get(ii).getLabel()); // changing values
                }
            }
        }
     
        
                
             
    }//end changing functionFrac
    
    public void changingContraintS (ArrayList<ArrayList<JButton>> matButt, ArrayList< ArrayList<Variable> > vecCont, 
            String aB, final int aC,final int  m, final int n){
        //Tenho que mudar of vector de variaveis tbem !!! (Trabalho futuros) !!!!
        String  auxS; // first button of 
        int     auxI = -1;
        
        //finding position where is the button
        for (int jj=0; jj<n; jj++)
            if (matButt.get(aC).get(jj).getLabel().equals(aB)){
                auxI = jj;
                jj = n+1;
            }//end IF and FOR

        
        if (auxI>0){
            double num = vecCont.get(aC).get(auxI).getValue();
            if ((num <= epsilon) && (num > -epsilon)){
                Div0 = true;
            }else{ 
                Div0 = false;
                System.out.println("num:"+num);
                auxS = matButt.get(aC).get(0).getLabel();//Name variable right size of activated constraint
                //changingValueVariablesFraction(vecCont,auxI, aC, m, n);
                changingValueVariables(vecCont,auxI, aC, m, n);
                changingButtons(matButt,auxI,aB, aC,m,n,auxS);
            }
        }//end if  auxI 
    }//end changingContraintS
    
    public void changingButtons (ArrayList<ArrayList<JButton>> matButt, final int auxI, final String aB, 
            final int  aC, final int m, final int n, final String auxS){
        //CHANGING BUTTONS 
        for (int ii=0; ii<m; ii++)
            for (int jj=0; jj<n; jj++){
                if (ii == aC)
                    matButt.get(ii).get(0).setLabel(aB); // putting variable in the right BUTTON
                matButt.get(ii).get(auxI).setLabel(auxS);
            }//end for ii and jj
    }
    
    public void changingValueVariablesFraction(ArrayList< ArrayList<Variable> > vecCont, final int auxI, 
            final int aC, final int m, final int n){
        
        //Changing in the actived contraint
        String auxS = vecCont.get(aC).get(auxI).getLabel(); //name variable selected
        String auxS_0 = vecCont.get(aC).get(0).getLabel();// name of first variable 
        vecCont.get(aC).get(0).setLabelName(auxS); //changing name of first variable
        
        long val_num = - vecCont.get(aC).get(auxI).getValueNum(); // minus cause change the side
        long val_den = vecCont.get(aC).get(auxI).getValueDen();
        
        for (int jj=0; jj<n; jj++){
            if (jj == auxI){
                vecCont.get(aC).get(jj).setValueFrac(-1*val_den, val_num);
                vecCont.get(aC).get(jj).setLabelName(auxS_0);
            }else{
                long auxII_num = vecCont.get(aC).get(jj).getValueNum(); 
                long auxII_den = vecCont.get(aC).get(jj).getValueDen();
                
                //tring to avoit the long numbers erros 
                simplificationPrimes(auxII_num, val_num);
                simplificationPrimes(val_den, auxII_den);
        
                vecCont.get(aC).get(jj).setValueFrac(auxII_num*val_den, auxII_den*val_num);           
                //double auxD = vecCont.get(aC).get(jj).getValue();
                //vecCont.get(aC).get(jj).setValueD(auxD/val);
            }
        }
        
        //changing other contraints
        for (int ii=0; ii<m; ii++)
            if (ii != aC){
                long auxD_num = vecCont.get(ii).get(auxI).getValueNum();
                long auxD_den = vecCont.get(ii).get(auxI).getValueDen();
                for (int jj=0; jj<n; jj++){
                    long auxD2_num = vecCont.get(ii).get(jj).getValueNum(); //old value of variable in constrant
                    long auxD2_den = vecCont.get(ii).get(jj).getValueDen(); //old value of variable in constrant
                    
                    long auxD3_num = vecCont.get(aC).get(jj).getValueNum(); 
                    long auxD3_den = vecCont.get(aC).get(jj).getValueDen();
                    
                    //tring to avoit the long numbers erros 
                    simplificationPrimes(auxD3_num, auxD_den);
                    simplificationPrimes(auxD_num, auxD3_den);
                        
                        
                    if (jj != auxI){
                        long mmc1 = mmc(auxD2_den,auxD_den*auxD3_den);
                        vecCont.get(ii).get(jj).setValueFrac(auxD_num*auxD3_num*(mmc1/(auxD_den*auxD3_den))+ auxD2_num*(mmc1/auxD2_den) , mmc1);
                        //vecCont.get(ii).get(jj).setValueFrac(auxD_num*auxD3_num*auxD2_den + auxD2_num*auxD_den*auxD3_den, auxD_den*auxD3_den*auxD2_den); // changing values 
                    }else{
                        vecCont.get(ii).get(jj).setValueFrac(auxD_num*auxD3_num,auxD_den*auxD3_den); // changing values
                        vecCont.get(ii).get(jj).setLabelName(auxS_0);
                    }    
                }    
            }
    }//end changingValueVariablesFraction
    
    public void simplificationPrimes(long numerat, long denominat){
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
            
            ArrayList<Integer> primes = new ArrayList<Integer>(20);
         
            primes.add(71);primes.add(67);primes.add(61);primes.add(59);primes.add(53);
            primes.add(47);primes.add(43);primes.add(41);primes.add(37);primes.add(31);
            primes.add(29);primes.add(23);primes.add(19);primes.add(17);primes.add(13);
            primes.add(11);primes.add(7);primes.add(5);primes.add(3);primes.add(2);
            
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
    
    public void changingValueVariables(ArrayList< ArrayList<Variable> > vecCont, final int auxI, 
            final int aC, final int m, final int n){
        
        //Names
        String auxS = vecCont.get(aC).get(auxI).getLabel(); //name variable selected
        int col_S = vecCont.get(aC).get(auxI).getColumn();// name of first variable
        String auxS_0 = vecCont.get(aC).get(0).getLabel();// name of first variable 
        int col_0 = vecCont.get(aC).get(0).getColumn();// name of first variable
        int con_0 = vecCont.get(aC).get(0).getContrant();
        
        vecCont.get(aC).get(0).set_LabelNamePosition(vecCont.get(aC).get(auxI));
        //vecCont.get(aC).get(0).setLabelName(auxS); //changing name of first variable
        //vecCont.get(aC).get(0).setColumn(col_S);
        
        //Changing the actived contraint
        double val = - vecCont.get(aC).get(auxI).getValue(); // minus cause change the side
        for (int jj=0; jj<n; jj++){
            if (jj == auxI){
                vecCont.get(aC).get(jj).setValueD(-1.0/val);
                vecCont.get(aC).get(jj).setLabelName(auxS_0);
                vecCont.get(aC).get(jj).setPosStart (col_0, con_0);
            }else{
                double auxD = vecCont.get(aC).get(jj).getValue();
                vecCont.get(aC).get(jj).setValueD(auxD/val);
            }
        }
        
        //changing other contraints
        for (int ii=0; ii<m; ii++)
            if (ii != aC){
                double auxD = vecCont.get(ii).get(auxI).getValue();
                for (int jj=0; jj<n; jj++){
                    double auxD2 = vecCont.get(ii).get(jj).getValue(); //old value of variable in constrant
                    double auxD3 = vecCont.get(aC).get(jj).getValue(); // value of variable in active contraint
                    if (jj != auxI){
                        vecCont.get(ii).get(jj).setValueD(auxD*auxD3 + auxD2); // changing values 
                    }else{
                        vecCont.get(ii).get(jj).setLabelName(auxS_0);
                        vecCont.get(ii).get(jj).setPosStart (col_0, con_0);
                        vecCont.get(ii).get(jj).setValueD(auxD*auxD3); // changing values 
                    }
                }    
            } 
    }//end changingValueVariables
    
    long mdc(long a,long b)
    {
        if(b == 0) return a;
        else
        return mdc(b,a%b);
    }
    long mmc(long a,long b)
    {
        long div;
        if(b == 0) return a;
        else
         div = (a*b)/(mdc(a,b));
        return (div); 
    }      

    // **********
    // *************GRAPH 
    // ****************************
    
    public void setGraph(ArrayList<JPanel> vecPanel,  final ArrayList<ArrayList<JButton> > matButt, 
              final ArrayList< ArrayList<Variable> > vecCont, final ArrayList<Variable>  funcObj,
              final int m, final int n )
    {
        ArrayList<Double> vecX1 = new ArrayList<Double> ();
        ArrayList<Double> vecX2 = new ArrayList<Double> ();
        ArrayList<Double> rhVal = new ArrayList<Double> ();
        
        double ObX1 = funcObj.get(1).value;
        double ObX2 = funcObj.get(2).value;

        
        // valeur de x1 et x2, et reapaint le graph
        GetX1andX2_values(vecCont, vecX1, vecX2, rhVal,  m, n);
        graphPanel.setNewGraph(vecX1, vecX2, rhVal, m, n,ObX1, ObX2);
        
        
        
        
    }
    
    public void GetX1andX2_values(ArrayList< ArrayList<Variable> > vecCont, ArrayList<Double> vecX1, 
            ArrayList<Double> vecX2,ArrayList<Double> rhVal,  final int m, final int n )
    {
        //System.out.println("Chegou aqui no GetX1andX2_values TAP: " + vecX1.size());
        //Scanner in= new Scanner(System.in);
        //int x=in.nextInt(); //nextInt() method for reading integer
        //String s=in.next(); // next() - reads strings
        vecX1.clear();
        vecX2.clear();
        rhVal.clear();
        
        for (int i=0;i<m;i++){ //loop in every constraint
               vecX1.add(vecCont.get(i).get(1).value);
               vecX2.add(vecCont.get(i).get(2).value);
               rhVal.add(vecCont.get(i).get(0).value); 
        }
    }
       
    
}//end class Contraint
