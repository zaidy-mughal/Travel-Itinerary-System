

public class SignUp{

    private String name;
    private String email;
    private String password;
    private String rePassword;


    public SignUp(String name, String email, String password, String rePassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.rePassword = rePassword;
    }


    public boolean checkSamePassword(){
        return password.equals(rePassword);
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRePassword() {
        return rePassword;
    }
}
