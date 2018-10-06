/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.applet.Applet;
import javax.swing.*;

/**
 *
 * @author Jefte
 */
public class DicApplet extends Applet {
    public JTextPane PaneIntrod;
    
    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    public void init() {
        // TODO start asynchronous download of heavy resources
        
      JPaneIntroduction  swingContainerDemo = new JPaneIntroduction();  
      swingContainerDemo.showJFrameDemo();

    }
    // TODO overwrite start(), stop() and destroy() methods
}
