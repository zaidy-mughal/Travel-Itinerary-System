
public class Excursion extends Activity{
    private String excursionName;
    private String excursionDescription;
    private double excursionPrice;


    public Excursion(Location location, String excursionName, String excursionDescription, double excursionPrice) {
        super(location);
        this.excursionName = excursionName;
        this.excursionDescription = excursionDescription;
        this.excursionPrice = excursionPrice;
    }

    @Override
    public double totalPrice(){
        return excursionPrice;
    }


    @Override
    public String toString() {
        return "Excursion Name: " + excursionName +
                "\n excursionPrice: " + excursionPrice + "$\n";
    }

    public String getExcursionName() {
        return excursionName;
    }

    public String getExcursionDescription() {
        return excursionDescription;
    }

    public double getExcursionPrice() {
        return excursionPrice;
    }

    
}
