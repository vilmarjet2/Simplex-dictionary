
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import java.util.Vector;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jefte
 */
public class frameDictionaire extends JFrame {
    
    private JFrame BeforeDic;
    private JFrame myFrame; //Principal Frame 
    private JFrame warningFrame; 
    //public  ControlPanel panelPrinc;
    public  Contraint mainCont;
    int     nbVar, nbConst;
    public PlotGraph        graph; //Plot graph of problem (Just if we have 2 variables)
    
    GroupLayout layout ;
    
    JTextField text1 ;
    JTextField text2 ;
    
    public frameDictionaire(int m, int n){
        initFrame(m, n);
    } 
    
 
    private void initFrame(int m, int n){
        
        settingWarnningFrame();
        settingBeforeFrame(); //Start button is here
        settingsFrame(0,0);
       
     }//end intFrame 
    
    public void settingWarnningFrame(){
        warningFrame = new JFrame("Warnning !!! ");
        warningFrame.setSize(400,100);
        warningFrame.setLayout(new GridLayout(2, 1));
        warningFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            warningFrame.dispose();
         }        
      });
    }
    
    public void settingBeforeFrame(){

        //myFrame.dispose();
        BeforeDic = new JFrame("Problem options");
        BeforeDic.setSize(400,200);
        BeforeDic.setLayout(new GridLayout(3, 1));
        BeforeDic.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            BeforeDic.dispose();
         }        
      });
        addingPanelsBF();
        BeforeDic.setVisible(true);
        
        
    }
    
    public void addingPanelsBF(){
        
        JLabel msglabel = new JLabel("Data of your problem", JLabel.CENTER);
        nbVar = 0;
        nbConst = 0;
        
        //First Panel 
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(new JLabel (" Number of variables:"));
        text1 = new JTextField(3);
        text1.setText("2");
        panel1.add(text1);
        
        //Second Panel 
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(new JLabel (" Number of constrants:"));
        text2 = new JTextField(3);
        text2.setText("3");
        panel2.add(text2);
       
        //Panel (Button)
        JButton butt = new JButton("Start");
        butt.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //Taking the contrait that activated by button
                        //text1.getText();
                        try {
                            nbVar = Integer.parseInt(text1.getText());
                        } catch (Exception ex) {nbVar =0;}
                        
                        try {
                            nbConst = Integer.parseInt(text2.getText());
                        } catch (Exception ex) {nbConst =0;}
                        
                        if ((nbVar <= 0) || (nbConst <= 0)){
                                addingWarrningFrame("Number of Variables and/or Constrants is forbidden !");
                        }else{
                            //Do myframe
                            settingDicFrame(nbConst, nbVar);    
                        }
                    } //end actionPerformed smallButt
                   });
        
        
        
        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.add(butt);             
      
        //BeforeDic.add(msglabel);
        BeforeDic.add(panel1);
        BeforeDic.add(panel2);
        BeforeDic.add(panel3); 
        
        //nbConst = 3;
        //nbVar = 2;
        //settingDicFrame(nbConst, nbVar);  
    }
    
    public void settingDicFrame(int m, int n){
        myFrame.removeAll();
        settingsFrame(nbConst,nbVar);
        
        
        //Declaration
        mainCont = new Contraint(m,n);
        graph = new PlotGraph();
        addContraints(m,n);
        
        myFrame.setVisible(true);
    }
                            
    
    public void addingWarrningFrame(String str){
        warningFrame.removeAll();
        settingWarnningFrame();
        //First Panel 
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(new JLabel (str));
        
        
        JButton butt = new JButton("OK");
        
        butt.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //Taking the contrait that activated by button 
                        warningFrame.dispose();
                    } //end actionPerformed smallButt
                   });
        //Second Panel 
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(butt);
        
        warningFrame.add(panel1);
        warningFrame.add(panel2);
        warningFrame.setVisible(true);
    }
    
    @Override
    public void setVisible (boolean bo){
          BeforeDic.setVisible(bo);
    } 
    
    
    private void settingsFrame(int m, int n){
     if (n != 2){   
        myFrame = new JFrame("Our Problem");
        myFrame.setSize((n+1)*150,(m+4)*42);//comprimento vs largura 
        myFrame.setLayout(new GridLayout(1, 0)); // amount of panels in same collumn
        myFrame.addWindowListener(new WindowAdapter() { //defining closure of frame
            public void windowClosing(WindowEvent windowEvent){
                myFrame.dispose();
            }
        });
     }else{ // we have to add the size of Grafic
         layout = new GroupLayout(myFrame);
         myFrame = new JFrame("Our Problem");
        myFrame.setSize((n+1)*400,(m+4)*42 + 150);//comprimento vs largura 
        myFrame.setLayout(new GridLayout(0, 2)); // amount of panels in same collumn
        myFrame.addWindowListener(new WindowAdapter() { //defining closure of frame
            public void windowClosing(WindowEvent windowEvent){
                myFrame.dispose();
            }
        });
     }
    }
    
    private void addContraints(int m,int n){
        //ControlPanel auxControl; 
        //Contraint     auxContraint;
        
            //for (int i=0; i<=m+2; i++)  
                //myFrame.add(mainCont.getPanel(i));  
        
        myFrame.add(mainCont.LeftPanel);
        
        if (n==2)
             myFrame.add(mainCont.graphPanel.dPanel);
        /*
        for (int i=0; i<m; i++){
            auxContraint = new Contraint(i, n, vecPan.get(i).getPanel());
            vecContr.addElement(auxContraint);
        }
        */ 
    }
          
}//end class FrameDictionaire
