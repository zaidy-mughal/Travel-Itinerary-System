import java.time.LocalDate;

public class HotelBooking extends Activity{
    private Hotels bookedHotel;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    

    public HotelBooking(Location location, Hotels bookedHotel, LocalDate checkInDate, LocalDate checkOutDate) {
        super(location);
        this.bookedHotel = bookedHotel;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }


    public int calculateDays(){
        return checkOutDate.getDayOfYear()-checkInDate.getDayOfYear();
    }
    
    @Override
    public double totalPrice(){
        return bookedHotel.getPricePerDay()*calculateDays();
    }
    



    @Override
    public String toString() {
        return  "CheckInDate: " + checkInDate + "\t\t\t CheckOutDate: "
                + checkOutDate + "\n" + bookedHotel.toString() + "\n";
    }


   public Hotels getBookedHotel() {
       return bookedHotel;
   }


   public void setBookedHotel(Hotels bookedHotel) {
       this.bookedHotel = bookedHotel;
   }

   public LocalDate getCheckInDate() {
       return checkInDate;
   }

   public void setCheckInDate(LocalDate checkInDate) {
       this.checkInDate = checkInDate;
   }

   public LocalDate getCheckOutDate() {
       return checkOutDate;
   }

   public void setCheckOutDate(LocalDate checkOutDate) {
       this.checkOutDate = checkOutDate;
   }





}
