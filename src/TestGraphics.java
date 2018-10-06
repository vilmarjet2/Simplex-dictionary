/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jefte
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;


import javax.swing.JButton;
import javax.swing.JFrame;
/*
public class TestGraphics {
        
public static JFrame myFrame; //Principal Frame 

    public static void main(String[] args) {
        JPanel    vecPanel1 = new JPanel (new GridLayout(3, 0)) ;
        JPanel    vecPanel2 = new JPanel (new GridLayout(1, 1)) ;;
        myFrame = new JFrame("Our Problem");
        myFrame.setSize(400,300);//comprimento vs largura 
        myFrame.setLayout(new GridLayout(0, 2)); // amount of panels in same collumn
        myFrame.addWindowListener(new WindowAdapter() { //defining closure of frame
            public void windowClosing(WindowEvent windowEvent){
                myFrame.dispose();
            }
        });
        
        
        
        vecPanel1.add(new JButton("Button 1"));
        vecPanel1.add(new JButton("Button 2"));
        vecPanel1.add(new JButton("Button 2.1"));
        vecPanel2.add(new JButton("Button 3"));
        
        
        myFrame.add(vecPanel1);
        myFrame.add(vecPanel2);
        
        myFrame.setVisible(true);
        
    }
}


*/


/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel; 
import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.BoxLayout;
 
/*
 * BorderDemo.java requires the following file:
 *    images/wavy.gif
 */

/*
public class TestGraphics extends JPanel {
    public TestGraphics() {
        super(new GridLayout(1,0));
 
        //Keep references to the next few borders,
        //for use in titles and compound borders.
        Border blackline, raisedetched, loweredetched,
               raisedbevel, loweredbevel, empty;
 
        //A border that puts 10 extra pixels at the sides and
        //bottom of each pane.
        Border paneEdge = BorderFactory.createEmptyBorder(0,10,10,10);
 
        blackline = BorderFactory.createLineBorder(Color.black);
        raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        loweredbevel = BorderFactory.createLoweredBevelBorder();
        empty = BorderFactory.createEmptyBorder();
 
        //First pane: simple borders
        JPanel simpleBorders = new JPanel();
        simpleBorders.setBorder(paneEdge);
        simpleBorders.setLayout(new BoxLayout(simpleBorders,
                                              BoxLayout.Y_AXIS));
 
        addCompForBorder(blackline, "line border",
                         simpleBorders);
        addCompForBorder(raisedetched, "raised etched border",
                         simpleBorders);
        addCompForBorder(loweredetched, "lowered etched border",
                         simpleBorders);
        addCompForBorder(raisedbevel, "raised bevel border",
                         simpleBorders);
        addCompForBorder(loweredbevel, "lowered bevel border",
                         simpleBorders);
        addCompForBorder(empty, "empty border",
                         simpleBorders);
 
        //Second pane: matte borders
        JPanel matteBorders = new JPanel();
        matteBorders.setBorder(paneEdge);
        matteBorders.setLayout(new BoxLayout(matteBorders,
                                              BoxLayout.Y_AXIS));
 
        ImageIcon icon = createImageIcon("images/wavy.gif",
                                         "wavy-line border icon"); //20x22
        Border border = BorderFactory.createMatteBorder(-1, -1, -1, -1, icon);
        if (icon != null) {
            addCompForBorder(border,
                             "matte border (-1,-1,-1,-1,icon)",
                             matteBorders);
        } else {
            addCompForBorder(border,
                             "matte border (-1,-1,-1,-1,<null-icon>)",
                             matteBorders);
        }
        border = BorderFactory.createMatteBorder(1, 5, 1, 1, Color.red);
        addCompForBorder(border,
                         "matte border (1,5,1,1,Color.red)",
                         matteBorders);
 
        border = BorderFactory.createMatteBorder(0, 20, 0, 0, icon);
        if (icon != null) {
            addCompForBorder(border,
                             "matte border (0,20,0,0,icon)",
                             matteBorders);
        } else {
            addCompForBorder(border,
                             "matte border (0,20,0,0,<null-icon>)",
                             matteBorders);
        }
 
        //Third pane: titled borders
        JPanel titledBorders = new JPanel();
        titledBorders.setBorder(paneEdge);
        titledBorders.setLayout(new BoxLayout(titledBorders,
                                              BoxLayout.Y_AXIS));
        TitledBorder titled;
 
        titled = BorderFactory.createTitledBorder("title");
        addCompForBorder(titled,
                         "default titled border"
                         + " (default just., default pos.)",
                         titledBorders);
 
        titled = BorderFactory.createTitledBorder(
                              blackline, "title");
        addCompForTitledBorder(titled,
                               "titled line border"
                                   + " (centered, default pos.)",
                               TitledBorder.CENTER,
                               TitledBorder.DEFAULT_POSITION,
                               titledBorders);
 
        titled = BorderFactory.createTitledBorder(loweredetched, "title");
        addCompForTitledBorder(titled,
                               "titled lowered etched border"
                                   + " (right just., default pos.)",
                               TitledBorder.RIGHT,
                               TitledBorder.DEFAULT_POSITION,
                               titledBorders);
 
        titled = BorderFactory.createTitledBorder(
                        loweredbevel, "title");
        addCompForTitledBorder(titled,
                               "titled lowered bevel border"
                                   + " (default just., above top)",
                               TitledBorder.DEFAULT_JUSTIFICATION,
                               TitledBorder.ABOVE_TOP,
                               titledBorders);
 
        titled = BorderFactory.createTitledBorder(
                        empty, "title");
        addCompForTitledBorder(titled, "titled empty border"
                               + " (default just., bottom)",
                               TitledBorder.DEFAULT_JUSTIFICATION,
                               TitledBorder.BOTTOM,
                               titledBorders);
 
        //Fourth pane: compound borders
        JPanel compoundBorders = new JPanel();
        compoundBorders.setBorder(paneEdge);
        compoundBorders.setLayout(new BoxLayout(compoundBorders,
                                              BoxLayout.Y_AXIS));
        Border redline = BorderFactory.createLineBorder(Color.red);
 
        Border compound;
        compound = BorderFactory.createCompoundBorder(
                                  raisedbevel, loweredbevel);
        addCompForBorder(compound, "compound border (two bevels)",
                         compoundBorders);
 
        compound = BorderFactory.createCompoundBorder(
                                  redline, compound);
        addCompForBorder(compound, "compound border (add a red outline)",
                         compoundBorders);
 
        titled = BorderFactory.createTitledBorder(
                                  compound, "title",
                                  TitledBorder.CENTER,
                                  TitledBorder.BELOW_BOTTOM);
        addCompForBorder(titled, 
                         "titled compound border"
                         + " (centered, below bottom)",
                         compoundBorders);
 
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Simple", null, simpleBorders, null);
        tabbedPane.addTab("Matte", null, matteBorders, null);
        tabbedPane.addTab("Titled", null, titledBorders, null);
        tabbedPane.addTab("Compound", null, compoundBorders, null);
        tabbedPane.setSelectedIndex(0);
        String toolTip = new String("<html>Blue Wavy Line border art crew:<br>&nbsp;&nbsp;&nbsp;Bill Pauley<br>&nbsp;&nbsp;&nbsp;Cris St. Aubyn<br>&nbsp;&nbsp;&nbsp;Ben Wronsky<br>&nbsp;&nbsp;&nbsp;Nathan Walrath<br>&nbsp;&nbsp;&nbsp;Tommy Adams, special consultant</html>");
        tabbedPane.setToolTipTextAt(1, toolTip);
 
        add(tabbedPane);
    }
 
    void addCompForTitledBorder(TitledBorder border,
                                String description,
                                int justification,
                                int position,
                                Container container) {
        border.setTitleJustification(justification);
        border.setTitlePosition(position);
        addCompForBorder(border, description,
                         container);
    }
 
    void addCompForBorder(Border border,
                          String description,
                          Container container) {
        JPanel comp = new JPanel(new GridLayout(1, 1), false);
        JLabel label = new JLabel(description, JLabel.CENTER);
        comp.add(label);
        comp.setBorder(border);
 
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(comp);
    }
 
 
    // Returns an ImageIcon, or null if the path was invalid. 
    protected static ImageIcon createImageIcon(String path,
                                               String description) {
        java.net.URL imgURL = TestGraphics.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
 
    // **
    // * Create the GUI and show it.  For thread safety,
    // * this method should be invoked from the 
    // * //event-dispatching thread.
     // *
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("BorderDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        TestGraphics newContentPane = new TestGraphics();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
*/
public class TestGraphics
{
  public static void main ( String[] args )
  {
      JFrame frame = new JFrame ("Graphics Test");
      DrawingPanel3 dPanel = new DrawingPanel3();
      frame.getContentPane().add(dPanel);
      frame.setSize (800, 600);
      frame.setLocation (40, 40);
      frame.setVisible (true);
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
  }
}

class DrawingPanel3 extends JPanel
{
  public void paintComponent ( Graphics g )
  {
    super.paintComponent ( g );
	
    // Set the font
    g.setFont (new Font("Serif", Font.BOLD, 20));
    g.drawString ("Serif", 10, 30);
    g.setFont (new Font("SansSerif", Font.ITALIC, 20));
    g.drawString ("SansSerif", 150, 30);
    g.setFont (new Font ("Monospaced", Font.PLAIN, 20));
    g.drawString ("Monospaced", 300, 30);
	
    // Draw lines to form a T
    g.setColor (Color.red);
    g.drawLine (10, 50, 100, 50);
    g.setColor (Color.blue);
    g.drawLine (55, 50, 55, 150);

    // Draw rectangle and filled rounded square
    g.setColor (Color.orange);
    g.drawRect (150, 50, 50, 75);
    g.fillRoundRect (300, 50, 50, 75, 20, 20);	
		
    // 3D: Draw filled rectangle and square
    g.setColor (Color.green);
    g.fill3DRect (10, 200, 50, 75, true);
    g.draw3DRect (150, 200, 50, 50, true);

    // Draw an open and a filled ellipse
    g.setColor (Color.cyan);
    g.drawOval (250, 200, 50, 75);
    g.fillOval (350, 200, 50, 75);
		
    // Draw an open and a filled circle
    g.setColor (Color.magenta);
    g.drawOval (10, 300, 75, 75);
    g.fillOval (150, 300, 75, 75);

    // Draw arc to represent a Pac man
    g.setColor (Color.pink);
    g.drawArc (250, 300, 50, 50, 30, 300);
    g.fillArc (350, 300, 50, 50, 30, 300);		

    // Create a polygon
    int[] xPoints = {10, 30, 40, 50, 70, 50, 40, 30};
    int[] yPoints = {440, 430, 410, 430, 440, 450, 470, 450};
    int nPoints = xPoints.length;
    Polygon star = new Polygon (xPoints, yPoints, nPoints);
    g.setColor (Color.yellow);
    g.drawPolygon (star);
    g.fillPolygon(star);
		
    // Put a box around the polygon
    g.setColor (Color.darkGray);
    Rectangle bounds = star.getBounds();
    g.drawRect (bounds.x, bounds.y, bounds.width, bounds.height);

    // Set the clipping region
    g.setClip (200, 400, 100, 100);
		
    // Draw Pac-man in a 500 x 600 area
    for (int i = 0; i < 500; i += 60)
      for (int j = 0; j < 600; j += 60)
        g.fillArc (i, j,		// top left hand corner
	           50, 50,		// width, height
	           30, 300);		// start angle, sweep angle	

  }
	
}

/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawingGraph 
{
  public static void main ( String[] args )
  {
      JFrame frame = new JFrame ("Drawing a Graph");
      DrawingPanel dPanel = new DrawingPanel();
      frame.getContentPane().add(dPanel);
      frame.setSize (800, 600);
      frame.setLocation (40, 40);
      frame.setVisible (true);
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
  }
}

class DrawingPanel extends JPanel
{
  public void paintComponent ( Graphics g )
  {
    super.paintComponent ( g );
	
    // Set up a Cartesian coordinate system

    // get the size of the drawing area		
    Dimension size = this.getSize();

    // place the origin at the middle
    g.translate (size.width / 2, size.height / 2);

    // draw the x and y axes
    drawXYAxes (g);
		
    graphLine (g, 0.5, 25.2);
    graphQuadratic (g, -0.125, 0, 0);

  }
	
  private void drawXYAxes (Graphics g) {
    Dimension size = this.getSize();
    int hBound = size.width / 2;
    int vBound = size.height / 2;
    int tic = size.width / 100;
			
    // draw the x-axis
    g.drawLine (-hBound, 0, hBound, 0);

    // draw the tic marks along the x axis
    for (int k = -hBound; k <= hBound; k += 10)
      g.drawLine (k, tic, k, -tic);
			
    // draw the y-axis
    g.drawLine (0, vBound, 0, -vBound);	

    // draw the tic marks along the y axis
    for (int k = -vBound; k <= vBound; k += 10)
      g.drawLine (-tic , k, +tic, k);					
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

*/