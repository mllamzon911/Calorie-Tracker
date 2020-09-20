/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calorie.tracker.application;

import static calorie.tracker.application.CalorieTrackerApplication.currentUser;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.AreaRendererEndType;
import org.jfree.chart.renderer.category.AreaRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Marvin Llamzon
 */
public class AreaChart extends JFrame{
    public AreaChart(User current) {
        initUI(current);
    }
    public void initUI(User current) {
        DefaultCategoryDataset dataset = createDataset(current);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        
        add(chartPanel);

        setTitle("Area chart");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private static DefaultCategoryDataset createDataset(User current) {
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (int i = 0; i < currentUser.getJournals().size(); i++) {
            dataset.addValue(current.getJournals().elementAt(i).getTotalCalories(), "Calories", current.getJournals().elementAt(i).getDate() + " " + i);
        } 
        
        return dataset;
    }
    
    private static JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createAreaChart (
                "Total Logged Calories",
                "Date",
                "Estimated Calories",
                dataset
        );
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setForegroundAlpha(0.3f);
        
        AreaRenderer renderer = (AreaRenderer) plot.getRenderer();
        renderer.setEndType(AreaRendererEndType.LEVEL);

        chart.setTitle(new TextTitle("Total Calories",
                new Font("Serif", java.awt.Font.BOLD, 18))
        );

        return chart;
    }
}
