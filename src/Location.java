import java.io.Serializable;

public class Location implements Serializable{
    private String locationStreetNumber;
    private String locationCity;
    private String locationCountry;


    public Location(String locationCity, String locationCountry){
        this.locationCity = locationCity;
        this.locationCountry = locationCountry;
    }



    public Location(String locationStreetNumber, String locationCity, String locationCountry) {
        this.locationStreetNumber = locationStreetNumber;
        this.locationCity = locationCity;
        this.locationCountry = locationCountry;
    }



    public String getLocationCity() {
        return locationCity;
    }



    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }



    public String getLocationCountry() {
        return locationCountry;
    }



    public void setLocationCountry(String locationCountry) {
        this.locationCountry = locationCountry;
    }



    public String getLocationStreetNumber() {
        return locationStreetNumber;
    }



    public void setLocationStreetNumber(String locationStreetNumber) {
        this.locationStreetNumber = locationStreetNumber;
    }

    
}
