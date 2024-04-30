import java.io.*;
import java.nio.file.Paths;
import javafx.scene.control.Label;
import java.util.Scanner;

public class Login {
    private String email;
    private String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public boolean checkInput(Label label){

        try (Scanner loginScanner = new Scanner(Paths.get("signUpInfo.txt"))){
            while (loginScanner.hasNext()) {
                String name = loginScanner.next();
                String emaily = loginScanner.next();
                String passy = loginScanner.next();
                if(email.equals(emaily)&& password.equals(passy)){
                    return true;
                }
            }
            return false;

        } catch (IOException ex) {
            label.setText("Login Data File issue!");
            return false;
        }
    }



    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }



}
