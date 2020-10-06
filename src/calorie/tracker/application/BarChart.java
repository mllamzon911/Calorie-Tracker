/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calorie.tracker.application;

import java.awt.Font;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset; 

/**
 *
 * @author Marvin Llamzon
 */
public class BarChart extends JFrame{
    public BarChart(User current) {
        initUI(current);
    }
    
    public void initUI(User current) {
        DefaultCategoryDataset dataset = createDataset(current);
        JFreeChart bar = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(bar);
        chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
        
        add(chartPanel);
        
        setTitle("Bar chart");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private static DefaultCategoryDataset createDataset(User current) {
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (int i = 0; i < current.getJournals().size(); i++) {
            dataset.addValue(current.getJournals().elementAt(i).getTotalCalories(), "Calories", current.getJournals().elementAt(i).getDate() + " " + i);
        } 
        
        return dataset;
    }
    
    private static JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart bar = ChartFactory.createBarChart (
                "Total Daily Calories",
                "Date",
                "Estimated Calories",
                dataset
        );
        CategoryPlot plot = (CategoryPlot) bar.getPlot();
        plot.setForegroundAlpha(0.3f);
        bar.setTitle(new TextTitle("Total Daily Calories",
                new Font("Serif", java.awt.Font.BOLD, 18))
        );
        return bar;
    }
}
