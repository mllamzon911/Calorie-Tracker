/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calorie.tracker.application;
import java.awt.Dimension;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 *
 * @author Marvin Llamzon
 */
public class CalorieOptionsPanel extends CalorieFrame {
    private static final int HEIGHT = 400, WIDTH = 800;
    
    private JMenuBar navigationBar;

    private JMenu file_menu;
        private static JFileChooser readChooser, saveChooser;
        private JMenuItem readFile, saveFile;
    private JMenu account;
        private JMenuItem addAccount, addJournal, addFood;
    private JMenu journal;
        private JMenuItem displayJournal, displayAll;
    private JMenu plotting;
        private JMenuItem plotUser;
    
    public CalorieOptionsPanel(String title) {
        super(title);
        
        navigationBar = new JMenuBar();
        
        file_menu = new JMenu("File");
        readFile = new JMenuItem("Read");
        saveFile = new JMenuItem("Save");
        saveChooser = new JFileChooser();
        saveChooser.setDialogTitle("Save");

        file_menu.add(readFile);
        file_menu.add(saveFile);

        account = new JMenu("User Navigation");
        addAccount = new JMenuItem("Create an account");
        addJournal = new JMenuItem("Create a new journal log");
        addFood = new JMenuItem("Add food to your journal");
        
        account.add(addAccount);
        account.add(addJournal);
        account.add(addFood);
        
        journal = new JMenu("Journal Navigation");
        displayJournal = new JMenuItem("List current journal");
        displayAll = new JMenuItem("List all journals");
        
        journal.add(displayJournal);
        journal.add(displayAll);
        
        plotting = new JMenu("Plot Navigation");
        plotUser = new JMenuItem("Plot journals");
        
        plotting.add(plotUser);

        MenuOptionsListener ml = new MenuOptionsListener();
        
        readFile.addActionListener(ml);
        saveFile.addActionListener(ml);

        addAccount.addActionListener(ml);
        addJournal.addActionListener(ml);
        addFood.addActionListener(ml);
        
        displayJournal.addActionListener(ml);
        displayAll.addActionListener(ml);
        
        plotUser.addActionListener(ml);
        
        navigationBar.add(account);
        navigationBar.add(journal);
        navigationBar.add(plotting);
        
        setJMenuBar(navigationBar);
        
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
    }
    
    private class MenuOptionsListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String source = event.getActionCommand();
            switch(source) {
                case "Create an account": 
                    CalorieTrackerApplication.addUser();
                    break;
                case "Create a new journal log":
                    CalorieTrackerApplication.createJournal();
                    break;
                case "Add food to your journal":
                    CalorieTrackerApplication.addFood();
                    break;
                case "List current journal":
                    CalorieTrackerApplication.listCurrent();
                    break;
                case "List all journals":
                    CalorieTrackerApplication.displayAllJournals();
                    break;
                case "Plot journals":
                    CalorieTrackerApplication.plotUser();
                    break;
                case "Save":
                    try {
                        File file = saveChooser.getSelectedFile();
                        CalorieTrackerApplication.save(file);
                    } catch (NullPointerException NPex) {
                        System.err.println("Null file name, or user exited window");
                        JOptionPane.showMessageDialog(null,"Returning to Menu.");
                    } catch (Exception ex) {
                        ex.printStackTrace(System.err);
                        System.err.println("Unknown error/exception has occurred.");
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
