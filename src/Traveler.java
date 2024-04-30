import java.util.ArrayList;
import java.io.Serializable;

public class Traveler implements Serializable{
        private String name;
        private String contactInformation;
        private String nationality;
        private String CNIC;
        private String passportNumber;
        private Location location;
        private ArrayList<Trip> trip;



        public Traveler(String name, String contactInformation, String nationality, String cNIC, String passportNumber,
                Location location, ArrayList<Trip> trip) {
            this.name = name;
            this.contactInformation = contactInformation;
            this.nationality = nationality;
            CNIC = cNIC;
            this.passportNumber = passportNumber;
            this.location = location;
            this.trip = trip;
        }


        public String getName() {
            return name;
        }


        public void setName(String name) {
            this.name = name;
        }


        public String getContactInformation() {
            return contactInformation;
        }


        public void setContactInformation(String contactInformation) {
            this.contactInformation = contactInformation;
        }


        public String getNationality() {
            return nationality;
        }


        public void setNationality(String nationality) {
            this.nationality = nationality;
        }


        public String getCNIC() {
            return CNIC;
        }


        public void setCNIC(String CNIC) {
            this.CNIC = CNIC;
        }


        public String getPassportNumber() {
            return passportNumber;
        }


        public void setPassportNumber(String passportNumber) {
            this.passportNumber = passportNumber;
        }





        public Location getLocation() {
            return location;
        }




        public void setLocation(Location location) {
            this.location = location;
        }


        public ArrayList<Trip> getTrip() {
            return trip;
        }


        public void setTrip(ArrayList<Trip> trip) {
            this.trip = trip;
        }

        

        
    
}
