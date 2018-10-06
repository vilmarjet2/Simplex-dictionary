/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.*;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JButton;

/**
 *
 * @author Jefte
 */

public class JPaneIntroduction {
    javax.swing.JButton start;
    public int          nbvar;
    public int          nbcons;
    JFrame              frame;
    frameDictionaire    frameDic;
    public JTextArea vars;
    public JTextArea cons;
    
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   private JPanel controlPanel2;
   
   private JLabel msglabel;
   public JButton smallButt ;
   int sizeVec = 3;
   
    public JPaneIntroduction (){
        prepareGUI();
    } 
    
    private void prepareGUI(){
      mainFrame = new JFrame("Java Swing Examples");
      mainFrame.setSize(400,700);
      mainFrame.setLayout(new GridLayout(5, 1));
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      headerLabel = new JLabel("", JLabel.CENTER);        
      statusLabel = new JLabel("",JLabel.CENTER);    

      statusLabel.setSize(100,100);

      msglabel = new JLabel("Welcome to TutorialsPoint SWING Tutorial.", JLabel.CENTER);

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());
      
      controlPanel2 = new JPanel();
      controlPanel2.setLayout(new FlowLayout());
      
      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(controlPanel2);
      controlPanel2.setLocation(400, 400);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }
    
    public void showJFrameDemo(){
        
      //final Vector<JButton> vec = new Vector<JButton>();
      
      headerLabel.setText("Container in action: JFrame");   
      
      
      //final JFrame frame = new JFrame();
      frame = new JFrame();
      frameDic = new frameDictionaire(5,5);
      
      frame.setSize(300, 300);
      frame.setLayout(new FlowLayout());       
      frame.add(msglabel);
      frame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            frame.dispose();
            statusLabel.setVisible(false);
         }        
      });    
      JButton okButton = new JButton("Open a Frame");
      okButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            statusLabel.setText("A Frame shown to the user.");
            statusLabel.setVisible(true);
            //frame.setVisible(true);
            frameDic.setVisiblee(true);

         }
      });
      //JButton smallButt ;
      
      //frameDic.setVisiblee(true);
      
//      for (int i=0; i<5; i++){          
//          smallButt = new JButton("x"+i);
//          if (i == 0) smallButt.setLabel("s"+i);
//          smallButt.setSize(5, 1);
//          smallButt.setName(""+i);
//          
//          smallButt.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                //String fst = vec.firstElement().getLabel();     
//                swichVector (vec, e.getActionCommand(), 0);
//                for (int i=0; i<vec.size(); i++){
//                   controlPanel.add(vec.get(i)); 
//                }
//            } //end actionPerformed smallButt
//           });  
//          vec.addElement(smallButt);
//      }
//      
      controlPanel2.add(okButton);
      
//      for (int i=0; i<vec.size(); i++){
//         controlPanel.add(vec.get(i)); 
//      }
//       
      //controlPanel.setVisible(false);
      
      mainFrame.setVisible(true);  
   }
    
//    public void swichVector (Vector<JButton> vec, String action, int v0){
//        int     auxInt = -1;
//        
//        for (int i=0; i<vec.size(); i++){
//            if (vec.get(i).getLabel().equals(action)){
//                auxInt = i;
//                i = vec.size(); 
//            }
//        }
//        
//        if (auxInt >= 0){
//           vec.get(auxInt).setLabel(vec.get(v0).getLabel());
//           vec.get(v0).setLabel(action);
//        }
//    }

}
