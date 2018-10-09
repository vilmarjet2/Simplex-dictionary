import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyStartButtHandler implements ActionListener
{
	ProblemModel myPanel;
	
	public MyStartButtHandler(ProblemModel panel_orig) 
	{
		this.myPanel = panel_orig;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		 String nameActButt = e.getActionCommand(); //aB = ActiveButton
         
         if (nameActButt.equals("Start"))
         {
             int verif = 0; 
             verif = gettingFuncObj();
             if (verif == 1 )
                 verif = getConstrants();
 
             if (verif == 1){
                 
            	 myPanel.lastButt.setText("Edit/Restart");
                 //System.out.println(vecCont.get(0).get(1).textVal.getText());
            	 myPanel.setValuesFraction(false);
                 myPanel.addingInPanels(myPanel.vecPanel, myPanel.matButt, myPanel.vecCont,myPanel.funcObj,myPanel.rows, myPanel.columns);
                 myPanel.setEditableText(false);
                 myPanel.setEditableButtons(true);
                 myPanel.setEnableRadioButtonFrac(true);
                 copyToStartVariables();
                 if (myPanel.columns-1 == 2) {//nb{
                	 //@TODO 
//                     setGraph(vecPanel, matButt, vecCont,funcObj,rows, columns);
                 }
             }else{
            	 new My_WarningFrame("All numbers must be Integer or Double (ex: 1.5)!");
             }
         }
         else
         {
        	 myPanel.lastButt.setText("Start"); 
        	 myPanel.setEditableText(true);
        	 myPanel.setEditableButtons(false);
        	 myPanel.setValuesFraction(false);
        	 myPanel.restartProblem();
             myPanel.addingInPanels_CanonicalProblem(myPanel.vecPanel, myPanel.matButt, myPanel.vecCont,myPanel.funcObj,myPanel.rows, myPanel.columns);
             myPanel.setEnableRadioButtonFrac(false);
             
         } 
	}//end function
	
	
	public void copyToStartVariables()
    {
        for (int ii=0; ii<myPanel.rows; ii++)
            for (int jj=0; jj<myPanel.columns; jj++)
            	myPanel.startvecCont.get(ii).get(jj).copyFromVariable(myPanel.vecCont.get(ii).get(jj)); 
        
        for(int jj=0; jj<myPanel.columns;jj++)
        	myPanel.startfuncObj.get(jj).copyFromVariable(myPanel.funcObj.get(jj));
    }//emd copytoStart
	
	
	public int gettingFuncObj()
	{
        double auxD; 
        for (int jj=0; jj<myPanel.columns; jj++){
             try {
                auxD = Double.parseDouble(myPanel.funcObj.get(jj).getText());
            } catch (Exception ex) {return 0;}
             
             myPanel.funcObj.get(jj).setValueD(auxD);
        }
        
        return 1;
	}//end gettingfuction objective
	
	
	public int getConstrants()
	{
        double auxD;
       for (int ii=0; ii<myPanel.rows; ii++)
           for (int jj=0; jj<myPanel.columns; jj++){
               try {   
                   auxD = Double.parseDouble(myPanel.vecCont.get(ii).get(jj).getText());
               } catch (Exception ex) {return 0;}
               myPanel.vecCont.get(ii).get(jj).setValueD(auxD);
           }
       return 1;
   }//end getConstrants
	
	

}
