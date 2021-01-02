package calorie.tracker.application;
/**
 *
 * @author Marvin Llamzon
 */
public class food {
    protected String item;
    protected double calories, protein, fats, carbs;
    protected int amount;
    
    public food(String name, double calories, double protein, double carbs, double fats) {
        item = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
        this.amount = 1;
    }

    /* Below are functions that will return the nutrients of said food item */

    public String getName() { return item; }
    
    public double getCalories() { return calories; }
    
    public double getProtein() { return protein; }
    
    public double getCarbs() { return carbs; }
    
    public double getFat() { return fats; }
    
}
