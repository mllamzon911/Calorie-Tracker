package calorie.tracker.application;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CalorieJFrame extends JFrame{
    public CalorieJFrame(String title) {
        super(title);
        FrameListener listener = new FrameListener();
        addWindowListener(listener);
    }
    private class FrameListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(null, "Now exiting program.");
                System.exit(0);
            }
        }
    }   
}
