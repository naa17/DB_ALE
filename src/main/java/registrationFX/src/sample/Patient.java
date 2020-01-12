//Patient class: has all details as entered in registration

package registrationFX.src.sample;

public class Patient {

    private String name;
    private String contact;
    private String email;
    private String password;
    private String doctorName;
    private String doctorContact;
    private String diabetesType;
    private String insulinType;
    private String insulinAdmin;
    //added logbookType field, not used in the end
    private String logbookType;

    public Patient(){
    }

    public  String getName() {
        return name;
    }
    public void setName(String Name) {
        this.name = Name;
    }

    public String getContact() {
        return contact;
    }
    public void setContact(String Contact) {
        this.contact = Contact;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String Password) {
        this.password = Password;
    }

    public String getDoctorName() {
        return doctorName;
    }
    public void setDoctorName(String DoctorName) {
        this.doctorName = DoctorName;
    }

    public String getDoctorContact() {
        return doctorContact;
    }
    public void setDoctorContact(String DoctorContact) {
        this.doctorContact = DoctorContact;
    }

    public String getDiabetesType() {
        return diabetesType;
    }
    public void setDiabetesType(String DiabetesType) {
        this.diabetesType = DiabetesType;
    }

    public String getInsulinType() {
        return insulinType;
    }
    public void setInsulinType(String InsulinType) {
        this.insulinType = InsulinType;
    }

    public String getInsulinAdmin() {
        return insulinAdmin;
    }
    public void setInsulinAdmin(String InsulinAdmin) {
        this.insulinAdmin = InsulinAdmin;
    }

    public String getLogbookType() {
        return logbookType;
    }
    public void setLogbookType(String Name) {
        this.name = logbookType;
    }

    public Patient(String name, String contact, String email, String password,String doctorName, String doctorContact, String diabetesType,
                   String insulinType, String insulinAdmin)
    {
        this.name=name;
        this.contact=contact;
        this.email=email;
        this.password=password;
        this.doctorName=doctorName;
        this.doctorContact= doctorContact;
        this.diabetesType= diabetesType;
        this.insulinType= insulinType;
        this.insulinAdmin= insulinAdmin;
    }

}