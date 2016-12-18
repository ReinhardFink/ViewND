package viewND;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartViewND extends JApplet {

    private static final long serialVersionUID = 1L;

    public static void main(String args[]) {
        JFrame it = new JFrame("ND Viewer");
        
        it.addWindowListener(
            new WindowAdapter() {
                    public void windowClosing( WindowEvent e ) {
                        Runtime.getRuntime().exit( 0 );
                    }
            }
        );
        JPanel viewND = new ViewND();
        it.getContentPane().add(viewND,BorderLayout.CENTER);
        //it.getContentPane().add(new View3DPanel(),BorderLayout.CENTER);
        it.setLocation(30, 30);
        it.setSize(viewND.getWidth(), viewND.getHeight());
        //it.setSize(new Dimension(500,500));
        //it.pack();
        it.setVisible(true);
    };
  
    public void init() {
       // this.getContentPane().add(new Pong(),BorderLayout.CENTER);  
    }
}