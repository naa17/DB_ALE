//Defining verification class. Includes password and email
package login_OOP.src.mp_v1;

public class Verification {
    public String email;
    public String password;


    public Verification(){
        //this.email=email;
        //this.password=password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}