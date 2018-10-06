/**
 * This program demonstrates how to draw XY line chart with XYDataset
 * using JFreechart library.
 * @author www.codejava.net
 *
 */

/**
 *
 * @author Jefte
 */


import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList; //vector
import java.util.Locale;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class PlotGraph extends JPanel {
  public DrawingPanel2 dPanel;
  public int size_n;
  public int size_m;
  
  
  /*Start initial graph*/
  public PlotGraph(){
      JPanel painel3 = new JPanel();
      painel3.setBackground(Color.red);

      dPanel = new DrawingPanel2();
      this.add(painel3);
      this.add(dPanel);
      
      
      /*Useless change*/
      //this.setVisible(true);
  }
  
    public void setNewGraph(ArrayList<Double>_vecX, ArrayList<Double> _vecY,  ArrayList<Double> _rhVal, final int m, final int n,
                          double Obx11, double Oby11){
     sizeGraph(n,m); // size of graph 
     dPanel.tranfXYObjValue (Obx11, Oby11); //Objective function point x and y 
     dPanel.tranfXYvalue (_vecX, _vecY, _rhVal); // Point in graph 
     dPanel.repaint();                           // action listener to paint new Panel
  }
  
  //Size of graph (Depend on number of constraints)
  public void sizeGraph(int n,int m){
      dPanel.size_X = (int)(((n+1)*400)/3);
      dPanel.size_Y = (int)(((m+3)*42 + 200)*0.9);
      //dPanel.
  }

}//End of PlotGraph


/****** Main class to draw ****/
class DrawingPanel2 extends JPanel
{
    public int size_X = 0;
    public int size_Y = 0;
    public int bordeGraph = 20;
    public int size_pointX = 0;
    public int size_pointY = 0;
    public int x1, y1, x2, y2;
    public double Obx1, Oby1;
    public ArrayList<Double> vecTX ;
    public ArrayList<Double> vecTY; 
    public ArrayList<Double> rhVal;
    public double maxX = 0; //minimum value of X >= 0
    public double maxY = 0;
    public boolean ShowObFunc; 
    Border blackline, raisedetched, loweredetched,
               raisedbevel, loweredbevel, empty;
    
    
    public DrawingPanel2(){
        vecTX = new ArrayList<Double> ();
        vecTY = new ArrayList<Double> ();
        rhVal = new ArrayList<Double> ();
        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        
        setLayout(new BorderLayout());
        
        
        
        setBorder(loweredetched);
        
        //set of Jpanel
        
        setOpaque(true);
        setBackground(Color.WHITE); 
       setFocusable(true);
       
       ShowObFunc = true;
    }
    
  public void tranfXYObjValue (double xx1, double yy1)
  {
      Obx1 = xx1;
      Oby1 = yy1;
  }
  public void tranfXYvalue (ArrayList<Double>_vecX, ArrayList<Double> _vecY, ArrayList<Double> _rhVal){
      
     vecTX = _vecX;
     vecTY = _vecY;
     rhVal = _rhVal;
     maxX = 0.01;
     maxY = 0.01;
     
     //findding maxY and maxX 
     for (int i=0; i<vecTY.size(); i++){
         if ((vecTX.get(i) != 0)&&(rhVal.get(i)/vecTX.get(i)  <0) && (-rhVal.get(i)/vecTX.get(i) > maxX))
             maxX = -rhVal.get(i)/vecTX.get(i);
         
         if ((vecTY.get(i) != 0) &&(rhVal.get(i)/vecTY.get(i)  <0) && (-rhVal.get(i)/vecTY.get(i) >maxY ))
             maxY = -rhVal.get(i)/vecTY.get(i) ;
     }
     System.out.println("maxY = " + maxY + "maxX = " + maxX );
     
  } 
  
  //this is a overrid of paintComponet 
    @Override
  public void paintComponent ( Graphics g ) //called by repaint 
  {
        
      this.add(new Button("Okay"));
      this.removeAll();     //clean
      this.setSize ( size_X,size_Y);
      //this.setLocation (40, 40);
      //this.setVisible (true);
      
    super.paintComponent ( g );
	 Graphics2D g2d = (Graphics2D) g;
    // Set up a Cartesian coordinate system

    // get the size of the drawing area		
    Dimension size = this.getSize();
    // place the origin at the middle
    g2d.translate (bordeGraph, size.height - bordeGraph);
    
    
    //1st - Paint every thing as a color of feasible Region
    //coloring the feasebel region of the problem (gray part of graph)
    color_FeaseableRegion((Graphics) g2d);
  
    g2d.setStroke(new BasicStroke(2));
    
    // draw the x and y axes
    drawXYAxes ((Graphics) g2d);

   // System.out.println("vecTX.size() = " + vecTX.size());
    
  //2st - Paint lines of each function
    for (int i=0; i<vecTX.size(); i++)
        graphLine2 (g,vecTX.get(i), vecTY.get(i), rhVal.get(i), i);
    
    if (ShowObFunc){
        LineFuncObj (g, Obx1, Oby1);
    }
        //Obx1 = xx1;
      //Oby1 = yy1;
  }//end of funciton 
 

        
 private void color_FeaseableRegion(Graphics g)
 {
     Dimension size = this.getSize();
     int hBound = size.width;
     int vBound = size.height;
    
    //1st ---- Color the all the graph in gray
    //coloring square
    g.setColor (Color.lightGray); //color of feasebel region
    g.fill3DRect (0, -vBound + 3*bordeGraph, hBound - 3*bordeGraph, vBound - 3*bordeGraph, true);
    
    //2nd ---- Set white in regions that are not the feeasible (It depend on the constraint)
    g.setColor (Color.white);
    for (int i=0; i<vecTX.size(); i++)
        feasebleRegion (g,vecTX.get(i), vecTY.get(i), rhVal.get(i), i);
    
 
 }
         
 private void feasebleRegion (Graphics g, double ax, double ay,  double b, int idx)
 {
     Dimension size = this.getSize();
     
    if ((ay != 0) || (ax != 0)){
         Calcul_XandY (ax,  ay,   b,  idx);
         //remember: x is X1 and y is X2
         
         System.out.println("idx =" +idx+"x1,y1 = " + x1 + ","+ y1 + "x2,y2 = " + x2 + ","+ y2 );
         
         if (b > 0){
         // Create a polygon
        if ((x1 == 0) && (y2 == 0)){
            int[] xPoints = {0, x2, size.width, size.width,   0};
            int[] yPoints = {y1, 0,  0,         -size.height, -size.height};
            int nPoints = xPoints.length;
            Polygon star = new Polygon (xPoints, yPoints, nPoints);
            g.fillPolygon(star);
        }else if (y2 == 0){
                int[] xPoints = {x1,x2, size.width, size.width};
                int[] yPoints = {y1, 0,  0,         -size.height};
                int nPoints = xPoints.length;
                Polygon star = new Polygon (xPoints, yPoints, nPoints);
                g.fillPolygon(star);
            }else if (x1 == 0){
                int[] xPoints = {x1, x1,             x2};
                int[] yPoints = {y1, -size.height,  y2};
                int nPoints = xPoints.length;
                Polygon star = new Polygon (xPoints, yPoints, nPoints);
                g.fillPolygon(star);
            }
         }else { //b < 0  
            if ((x1 == 0) && (y2 == 0)){
                int[] xPoints = {0, x2, 0, };
                int[] yPoints = {y1, 0,  0 };
                int nPoints = xPoints.length;
                Polygon star = new Polygon (xPoints, yPoints, nPoints);
                g.fillPolygon(star);
                }else if (y2 == 0){
                        if (y1 < maxY){
                            int[] xPoints = {x1,x2, 0,  0,           size.width };
                            int[] yPoints = {y1, 0,  0,-size.height, -size.height};
                            int nPoints = xPoints.length;
                            Polygon star = new Polygon (xPoints, yPoints, nPoints);
                            g.fillPolygon(star);
                        }else{
                            int[] xPoints = {x1,x2, 0, 0};
                            int[] yPoints = {y1, 0,  0, -size.height};
                            int nPoints = xPoints.length;
                            Polygon star = new Polygon (xPoints, yPoints, nPoints);
                            g.fillPolygon(star);
                        }
                    }else if (x1 == 0){
                        if (x2 < maxX){
                            int[] xPoints = {x1, 0, size.width,  size.width,     x2};
                            int[] yPoints = {y1, 0, 0,  -size.height, y2};
                            int nPoints = xPoints.length;
                            Polygon star = new Polygon (xPoints, yPoints, nPoints);
                            g.fillPolygon(star);
                        }else{
                            int[] xPoints = {x1,0 ,size.width ,x2,};
                            int[] yPoints = {y1, 0, 0,y2};
                            int nPoints = xPoints.length;
                            Polygon star = new Polygon (xPoints, yPoints, nPoints);
                            g.fillPolygon(star);
                        }
                     }else { // infeaseble 
                            int[] xPoints = {0, size.width, size.width,  0};
                            int[] yPoints = {0, 0,          -size.height, -size.height};
                            int nPoints = xPoints.length;
                            Polygon star = new Polygon (xPoints, yPoints, nPoints);
                            g.fillPolygon(star);
                    }
         }//end Else b<0
     }else{ //ax and ay == 0
        if (b < 0){ //infaseable !!!!
            int[] xPoints = {0, size.width, size.width,  0};
            int[] yPoints = {0, 0,          -size.height, -size.height};
            int nPoints = xPoints.length;
            Polygon star = new Polygon (xPoints, yPoints, nPoints);
            g.fillPolygon(star);
        }
    }
 }
 
 private void Calcul_XandY (double ax, double ay,  double b, int idx)
 {
        Dimension size = this.getSize();
        double SizeMaxX = size.width - 3*bordeGraph; //where the biggest value of X should be put
        double SizeMaxY = size.height - 3*bordeGraph;
       //System.out.println("maxY = " + maxY + ", SizeMaxY = " + SizeMaxY  + ", (b/ay) = " + (b/ay));

       //x is x1s and y is x2
       x1 = 0;
       if (ay != 0){
           y1 = (int) ((b/ay)*SizeMaxY/maxY); // must be negative
           if (y1 > 0){
               x1 = (int)SizeMaxX;
               y1 = (int) (((ax/ay)*maxX + (b/ay))*SizeMaxY/maxY);
           }
       }else{
           x1 = (int)(1*(b/ax)*SizeMaxX/maxX); //it is a negative number and must be possitive
           x1 = -x1;
           y1 = (int)-SizeMaxY;
       }
       // CALCULE OF (x2,y2)
       y2 = 0;
       if (ax != 0){
           x2 = (int)((b/ax)*SizeMaxX/maxX);
           x2  = - x2;
           if (x2 < 0){
               y2 = (int) -SizeMaxY;
               x2 = (int)(((ay/ax)*maxY +(b/ax))*SizeMaxX/maxX );
               x2  = - x2;
            }
       }else{
           x2 = (int)SizeMaxX;
           y2 = (int) ((b/ay)*SizeMaxY/maxY); 
       }
       //System.out.println("bEFORE x1,y1 = (" + x1 + "," + y1 + ")" + "x2,y2 = (" + x2 + "," + y2 + ")"); 
 }
 
   private void LineFuncObj (Graphics g, double ax, double ay)
  {
      int NbIte = 10;
      double bb;
      
      Graphics2D g2d = (Graphics2D) g;
        //float dash[] = {10.0f};
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed); // dashed line
        g2d.setColor (Color.black);
      
      if ((ax != 0) && (ax < ay)){
          double ratio = maxX/NbIte;
          double valX = ratio;
        for (int i=0; i<NbIte+5; i++){
            bb =  -((valX)*ax);
            //calcule of x1, y1, x2, y2;
             Calcul_XandY (ax,ay,  bb, i); // changes the value of globla variables: x1, y1, x2, y2;
             
            g2d.drawLine(x1, y1, x2, y2);
            System.out.println("ratio = " + ratio + ")");
            valX += ratio; 
            
        }//end FOR
      }else {
          double ratio = maxY/NbIte;
          double valY = ratio;
        for (int i=0; i<NbIte+5; i++){
            bb =  -((valY)*ay);
            //calcule of x1, y1, x2, y2;
             Calcul_XandY (ax,ay,  bb, i); // changes the value of globla variables: x1, y1, x2, y2;
             
            g2d.drawLine(x1, y1, x2, y2);
            System.out.println("ratio = " + ratio + ")");          
            valY += ratio; 
            
        }//end FOR
      }//end ELSE
  }
   
   //Function to set constraint in graphic 
 private void graphLine2 (Graphics g, double ax, double ay,  double b, int idx)
 {
     if ((ay != 0) || (ax != 0)){
         String ss;

         g.setColor (Color.red); // color of lines
         
         //calcule of x1, y1, x2, y2;
        Calcul_XandY (ax,ay,  b, idx); // changes the value of globla variables: x1, y1, x2, y2;
        
        //Printin numbers in axe  Y 
        if ((x1 == 0) && (y1 < 0)){
            ss = new DecimalFormat("0.0",DecimalFormatSymbols.getInstance(Locale.US)).format(-(b/ay));
            g.drawString (ss , -bordeGraph,  y1);
        }
        
        //Printing numbers in axe X 
        if ((y2 == 0) && (x2 > 0)){
            ss = new DecimalFormat("0.0",DecimalFormatSymbols.getInstance(Locale.US)).format(-(b/ax));
            g.drawString (ss , x2, (int)(0.6*bordeGraph) );
        }
       
       g.drawLine (x1, y1, x2, y2);
       
       //drawing NAME OF Constrint in graph 
        g.drawString ("s"+new Integer (idx).toString() , x1 + 10 ,  y1 - 5);
 
     }//end of first IF 
 }//end of function 
	
  private void drawXYAxes (Graphics g) {
    Dimension size = this.getSize();
    int hBound = size.width;
    int vBound = size.height;
    int tic = size.width / 300;
    
    g.setColor (Color.black);		
    // draw the x-axis
    g.drawLine (0, -2, hBound, -2);

    // draw the tic marks along the x axis
    for (int k = 0; k <= hBound; k += 10)
      g.drawLine (k, tic-2, k, -tic-2);
			
    // draw the y-axis
    g.drawLine (0, 0, 0, -vBound);	

    // draw the tic marks along the y axis
    for (int k = -vBound; k <= 0; k += 10)
      g.drawLine (-tic , k, +tic, k);
    
    g.drawString ("x1", -2*bordeGraph +hBound,(int)(0.5*bordeGraph) );
    g.drawString ("x2", -bordeGraph,-vBound +2*bordeGraph );
    
    
  }
  

   
   private void graphLine (Graphics g, double a,  double b) {
    Dimension size = this.getSize();
    g.setColor (Color.red);
		
    int x1 = size.width / 2;
    int y1 = (int) ((a * x1) + b);
    y1 = - y1;
		
    int x2 = - x1;
    int y2 = (int)((a * x2) + b);
    y2 = - y2;
		
    g.drawLine (x1, y1, x2, y2);	
  }
   
   /*
  private void graphLine (Graphics g, final double X, final double Y) {
    Dimension size = this.getSize();
    g.setColor (Color.red);
		
    double a = 0.5, b = 0.75;
    int x1 = (int)X;
    int y1 = ;

    int x2 = 0;
    int y2 = -(int)Y;
    
    g.drawLine (x1, y1, x2, y2);	
  }
  * */
	
  private void graphQuadratic (Graphics g, double a, double b, double c) {
    Dimension size = this.getSize();
    g.setColor (Color.blue);
	
    int hBound = size.width / 2;
    for (int i = -hBound; i <= hBound; i++) {
      int x1 = i;
      int y1 = (int) (a * x1 * x1 + b * x1 + c);
      y1 = - y1;
		
      int x2 = x1 + 1;
      int y2 = (int) (a * x2 * x2 + b * x2 + c);
      y2 = - y2;
					
      g.drawLine (x1, y1, x2, y2);
  }	
  
  }
}