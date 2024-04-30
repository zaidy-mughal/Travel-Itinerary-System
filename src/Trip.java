import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Trip implements Serializable{
    private LocalDate startDate;
    private LocalDate endDate;
    private Location locationOfTrip;
    private ArrayList<Activity> activities;


public Trip(LocalDate startDate, LocalDate endDate, Location locationOfTrip, ArrayList<Activity> activities) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.locationOfTrip = locationOfTrip;
        this.activities = activities;
    }

    public double totalPrice(){
        double price=0;
        for (int i = 0; i < activities.size(); i++) {
            price += activities.get(i).totalPrice();
        }
        return price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Location getLocationOfTrip() {
        return locationOfTrip;
    }

    public void setLocationOfTrip(Location locationOfTrip) {
        this.locationOfTrip = locationOfTrip;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }
    
    
    
}