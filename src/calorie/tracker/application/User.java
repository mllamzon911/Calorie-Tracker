package calorie.tracker.application;
import java.util.Vector;
/**
 *
 * @author Marvin Llamzon
 */
public class User {
    protected Vector<Journal> tracker;
    protected String username;
    protected int entries;
    public User(String name) {
        username = name;
        tracker = new Vector<Journal>();
        entries = 0;
    }
    public void newJournal(Journal j) {
        tracker.addElement(j);
        entries++;
    }
    public String getName() {
        return username;
    }
    public int getEntries() {
        return entries;
    }
    
    public Vector<Journal> getJournals() {
        return tracker;
    }
}
