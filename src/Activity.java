import java.io.Serializable;

public abstract class Activity implements Serializable{
    private Location location;
    

    public Activity(Location location) {
        this.location = location;
    }

    protected abstract double totalPrice();

    public Location getLocation() {
        return location;
    }

}


// String errormsg = cnicTextField.getText();
            // if(errormsg.matches("\\d{13}") &&
            // contactTextField.getText().matches("\\d{11}") &&
            // passportTextField.getText().matches("\\d{13}")){
            // error.setText("SuccessFully Submitted");
            // bookTrip(borderPane);
            // }
            // else{
            // error.setText("Invalid Arguments");
            // }