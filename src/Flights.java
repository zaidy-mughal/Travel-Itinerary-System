import java.io.Serializable;

public class Flights implements Serializable{
    private int bookingID;
    private int numberOfSeats;
    private String airLine;
    private String departureAirport;
    private String arrivalAirport;
    private double bookingPrice;


    public Flights(int bookingID,int numberOfSeats, String airLine, String departureAirport, String arrivalAirport,double bookingPrice) {
        this.bookingID = bookingID;
        this.numberOfSeats = numberOfSeats;
        this.airLine = airLine;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.bookingPrice = bookingPrice;
    }


    @Override
    public String toString() {
        return " BookingID : " + bookingID + "\t\t\t  Number Of Seats: " + numberOfSeats + "\n AirLine : " + airLine
                + "\t\t Destiny Airport : " + arrivalAirport + "\n BookingPrice = "
                + bookingPrice + "$";
    }


    public double getBookingPrice() {
        return bookingPrice*numberOfSeats;
    }

    public int getBookingID() {
        return bookingID;
    }




    public String getAirLine() {
        return airLine;
    }




    public String getDepartureAirport() {
        return departureAirport;
    }




    public String getArrivalAirport() {
        return arrivalAirport;
    }
    

    
}