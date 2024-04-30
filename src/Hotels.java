import java.io.Serializable;

public class Hotels implements Serializable{
    private int bookingNumber;
    private int numberOfRooms;
    private double pricePerDay;
    private String hotelName;

    public Hotels(int bookingNumber, int numberOfRooms, double pricePerDay, String hotelName) {
        this.bookingNumber = bookingNumber;
        this.numberOfRooms = numberOfRooms;
        this.pricePerDay = pricePerDay;
        this.hotelName = hotelName;
    }

    @Override
    public String toString() {
        return "Name: " + hotelName + "\t\t\t Booking Number: " + bookingNumber + "\n Number of rooms: "
                + numberOfRooms + "\n Price Per Day: " + pricePerDay + "$ ";
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public String getHotelName() {
        return hotelName;
    }

    

}
