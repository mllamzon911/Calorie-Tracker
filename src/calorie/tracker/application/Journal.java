package calorie.tracker.application;
import java.io.Serializable;
import java.util.Vector;
import java.text.SimpleDateFormat;  
import java.util.Date; 
/**
 *
 * @author Marvin Llamzon
 */
public class Journal implements Serializable{
    protected double totalCalories, totalProtein, totalCarbs, totalFats;
    protected Vector<food> list;
    protected Date logged;
    public Journal(double calories, Date logged) {
        totalCalories = calories;
        totalProtein = 0;
        totalCarbs = 0;
        totalFats = 0;
        list = new Vector<food>();
        this.logged = logged;
    }
    public void addFoodItem(food f) {
        list.addElement(f);
        addNutrition(f.getCalories(),f.getProtein(), f.getCarbs(), f.getFat());
    }
    
    public food getFoodItem(String item) {
        for (int i = 0; i < list.size(); i++) {
            if (list.elementAt(i).getName() == item)            
                return list.elementAt(i);
        }
        return null;
    }
    
    public boolean isEmpty() {
        if (list.size() == 0) 
            return true;
        return false;
    }
    
    public void addNutrition(double calories, double protein, double carbs, double fats) { 
        totalCalories += calories;
        totalProtein += protein;
        totalCarbs += carbs;
        totalFats += fats;
    }

    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(this.logged);
    }
    
    public Vector<food> getList() {
        return list;
    }

    public double getTotalCalories() {
        return totalCalories;
    }
    public double getTotalProtein() {
        return totalProtein;
    }
    public double getTotalCarbs() {
        return totalCarbs;
    }
    public double getTotalFats() {
        return totalFats;
    }
    
}
