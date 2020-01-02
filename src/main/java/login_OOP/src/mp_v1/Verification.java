package login_OOP.src.mp_v1;

public class Verification {
    public String email;
    public String password;
    public int id;


    public Verification(){
    }

    public int getId() {
        return id;
    }
    public void setId(int Id) {
        this.id = id;
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