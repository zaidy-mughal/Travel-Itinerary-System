
public class FlightBooking extends Activity{
    
    private Flights bookedFlight;

    
    public FlightBooking(Location location, Flights bookedFlight) {
        super(location);
        this.bookedFlight = bookedFlight;
    }

    

    @Override
    public String toString() {
        return bookedFlight.toString() + "\n";
    }



    @Override
    public double totalPrice(){
        return bookedFlight.getBookingPrice();
    }
}
