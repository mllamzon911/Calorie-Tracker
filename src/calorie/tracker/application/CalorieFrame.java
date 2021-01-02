package calorie.tracker.application;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.io.File;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 *
 * @author Marvin Llamzon
 */
public class CalorieFrame extends JFrame {
    public CalorieFrame(String title) {
        super(title);
        FrameListener listener = new FrameListener();
    }
    
    private class FrameListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            int option = JOptionPane.showConfirmDialog(null, "Would you like to save your accounts to a file?", "Save Account?", JOptionPane.YES_NO_OPTION); 
                if(option == JOptionPane.YES_OPTION) {
                    JFileChooser saveChooser = new JFileChooser();
                    int value = saveChooser.showSaveDialog(null);  
                    File file = saveChooser.getSelectedFile(); 
                }
                System.exit(0);
        }
    }
}
