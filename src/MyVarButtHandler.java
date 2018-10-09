import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class MyVarButtHandler implements ActionListener {
	
	ProblemModel myPanel;
	
	double          epsilon = 0.00001;
	
	
	public MyVarButtHandler(ProblemModel panel_orig) 
	{
		this.myPanel = panel_orig;
	}
	// Handle action events.
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		//Taking the constraint that is activated by button
        String activeCont = null;
         try 
         {
             for (int ii=1;ii < 4; ii++){ 
                String auxStri = e.toString().substring(e.toString().length() - ii);
                int AUXint = Integer.parseInt(auxStri);//if it is not integer it will cause a problem
                activeCont = auxStri; // it will be accepted just if auxStri is a integer (number of panel) 
             }
         } 
         catch (Exception ex) {}//do nothing
            
        int aC = Integer.parseInt(activeCont);
        String activeButton = e.getActionCommand(); //aB = ActiveButton (name of the variable)
       
        changingContraintS (myPanel.matButt, myPanel.vecCont,activeButton, aC, myPanel.rows, myPanel.columns);
        
        if (!myPanel.Div0){
            changingFunction (myPanel.matButt, myPanel.vecCont, myPanel.funcObj,activeButton,myPanel.rows, myPanel.columns);
            //changingFunctionFrac (matButt, vecCont, funcObj,activeButton,rows, columns);
            myPanel.addingInPanels(myPanel.vecPanel, myPanel.matButt, myPanel.vecCont,myPanel.funcObj,myPanel.rows, myPanel.columns);
            //vecCont.get(0).get(2).textVal.setText("3");
            //System.out.println(vecCont.get(0).get(1).textVal.getText());
            
//            System.out.println("column:" + myPanel.funcObj.get(1).column + "contraint_origem"+ myPanel.funcObj.get(1).contr);
            
        }else{
        	new My_WarningFrame("Undefined Division by 0 (zero) !! ");
        }
			
	}
	
	
	public void changingContraintS (ArrayList<ArrayList<JButton>> matButt, 
									ArrayList< ArrayList<Variable> > vecCont, 
            						String aB, final int aC,final int  m, final int n){
        
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
            	myPanel.Div0 = true;
            }else{ 
            	myPanel.Div0 = false;
                System.out.println("num:"+num);
                auxS = matButt.get(aC).get(0).getLabel();//Name variable right size of activated constraint
                //changingValueVariablesFraction(vecCont,auxI, aC, m, n);
                changingValueVariables(vecCont,auxI, aC, m, n);
                changingButtons(matButt,auxI,aB, aC,m,n,auxS);
            }
        }//end if  auxI 
    }//end changingContraintS
	
	
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
	
	
	public void changingButtons (ArrayList<ArrayList<JButton>> matButt, final int auxI, final String aB, 
            final int  aC, final int m, final int n, final String auxS)
	{
        //CHANGING BUTTONS 
        for (int ii=0; ii<m; ii++)
            for (int jj=0; jj<n; jj++){
                if (ii == aC)
                    matButt.get(ii).get(0).setLabel(aB); // putting variable in the right BUTTON
                matButt.get(ii).get(auxI).setLabel(auxS);
            }//end for ii and jj
    }
	
	
	public void changingFunction( 	final ArrayList<ArrayList<JButton>> matButt, 
									final ArrayList< ArrayList<Variable> > vecCont, 
            						ArrayList<Variable>  funcObj, String aB,final int  m, final int n)
	{
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
	

}
