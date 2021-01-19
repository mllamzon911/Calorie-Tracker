/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calorie.tracker.application;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.Date; 
import javax.swing.JTextArea;
import java.awt.Font;
import java.util.Vector;

import java.io.File;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.EventQueue;

/* Exception Handling */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;

/* JFreeChart Library Imports */
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.AreaRendererEndType;
import org.jfree.chart.renderer.category.AreaRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtils;


/**
 *
 * @author Marvin Llamzon
 */
public class CalorieTrackerApplication {
    /* Frame Application Variables */
    private static CalorieFrame frame;
    private static JFrame graph;
    private static JTextArea text;
    /* Global Variables to keep track of current user */
    public static User currentUser; 
    public static Journal currentJournal;
    
    public static void main(String[] args) {
        frame = new CalorieOptionsPanel("Calorie Tracker");
        text = new JTextArea(10,50);
        text.setFont(new Font("Monospaced",Font.PLAIN, 16));
        
        frame.getContentPane().add(text);
        text.setEditable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    /* Methods to allocate objects (user, journals, food) */
    
    public static void addUser() {
        String username = JOptionPane.showInputDialog("Enter a name:");
        currentUser = new User(username);
        text.setText("Hello " + currentUser.getName() + "!");
    }
    
    public static void createJournal() {
        Date currDate = new Date();
        currentJournal = new Journal(0, currDate);
        currentUser.newJournal(currentJournal);
        text.setText("Current Journal #: " + currentUser.getEntries());
    }
    
    public static void addFood() {
        /* Input Fields */
        JTextField foodInput = new JTextField();
        JTextField calorieInput = new JTextField();
        JTextField proteinInput = new JTextField();
        JTextField fatInput = new JTextField();
        JTextField carbsInput = new JTextField();
        
        Object[] message = {
            "Please enter the food name: ", foodInput,
            "Enter the item's calories: ", calorieInput,
            "Enter the item's total protein (g): ", proteinInput,
            "Input the item's total fats (g): ", fatInput,
            "Input the item's total carbs (g): ", carbsInput,
        };
        
        int option = JOptionPane.showConfirmDialog(null, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String item = foodInput.getText();
            String calories = calorieInput.getText();
            String protein = proteinInput.getText();
            String fats = fatInput.getText();
            String carbs = carbsInput.getText();
            
            double cal = Double.parseDouble(calories);
            double itemProtein = Double.parseDouble(protein);
            double itemFats = Double.parseDouble(fats);
            double itemCarbs = Double.parseDouble(carbs);
            
            food newItem = new food(item, cal, itemProtein, itemFats, itemCarbs);
            currentJournal.addFoodItem(newItem);
        }
    }
    
    /* Methods to display user's journals and specific journals */
    
    /* Lists current journal and the food items listed in the journal */
    public static void listCurrent() {
        String display = "";
        
        display += "Current Journal\n";
        display += "--------------------------------------------------\n";
        display += String.format("%-22s %-12s %-12s %-12s %6s", "Item", "Protein", "Fats", "Carbs", "Calories");
        
        for (int i = 0; i < currentJournal.getList().size(); i++) {
            display += "\n";
            display += String.format("%-22s %-12s %-12s %-12s %6s",
                    currentJournal.getList().elementAt(i).getName(), 
                    currentJournal.getList().elementAt(i).getProtein(), 
                    currentJournal.getList().elementAt(i).getCarbs(),
                    currentJournal.getList().elementAt(i).getFat(),
                    currentJournal.getList().elementAt(i).getCalories());
        }
        text.setText(display);
    }
    
    public static void displayAllJournals() {
        String display = "";
        display += currentUser.getName() + "'s journals\n";
        display += "--------------------------------------------------\n";
        display += String.format("%-15s %-12s %-18s %-14s %-16s %-16s", "Journal #", "Total Calories", "Total Protein", "Total Fats", "Total Carbs", "Date");
        for (int i = 0; i < currentUser.getEntries(); i++) {
            display += "\n";
            display += String.format("%-15s %-12s %-18s %-16s %-16s %-16s", i+1, 
                    currentUser.getJournals().elementAt(i).getTotalCalories(), 
                    currentUser.getJournals().elementAt(i).getTotalProtein(),
                    currentUser.getJournals().elementAt(i).getTotalFats(),
                    currentUser.getJournals().elementAt(i).getTotalCarbs(),
                    currentUser.getJournals().elementAt(i).getDate());
        }
        text.setText(display);
    }
    
    /* Plot calories onto a graph based off of user's journal entries */
    public static void plotUser() {
        EventQueue.invokeLater(() -> {

            var graph = new AreaChart(currentUser);            
            graph.setVisible(true);
        });
    }
    
    
    /* Method allowing users to save journal logs to a file */
    public static void save(File getFile) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(getFile));    
            output.writeObject(currentJournal);   
            output.close();
        }
        catch (StreamCorruptedException stce) {
            System.err.println("Unable to write into file. \n"
            + "Please write into a a text file. \n");
            JOptionPane.showMessageDialog(null, "Unable to write into file since it is not a text file. Try again!");
        }
        catch(FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "Unknown file selected");
            System.err.println("Illegal buffer size has been entered. (file not found in console)");
        }
        catch(IOException ex) {
            ex.printStackTrace(System.err);
            System.err.println("User doesn't have permission to write into file / no space to write");
        }
    }
}
