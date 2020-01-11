package profile.src.sample;

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

    public Patient()
    {

    }

    public String getName(){return this.name;}
    public void setName(String name){this.name=name;}
    public String getContact(){return this.contact;}
    public void setContact(String c){this.contact=c;}
    public String getEmail(){return this.email;}
    public void setEmail(String email){this.email=email;}
    public String getPassword(){return this.password;}
    public void setPassword(String pw){this.password=pw;}
    public String getDoctorName(){return this.doctorName;}
    public void setDoctorName(String dname){this.doctorName=dname;}
    public String getDoctorContact(){return this.doctorContact;}
    public void setDiabetesType(String dType){this.diabetesType=dType;}
    public String getInsulinType(){return this.insulinType;}
    public void setInsulinType(String insTYPE){this.insulinType=insTYPE;}
    public String getInsulinAdmin(){return this.insulinAdmin;}
    public void setInsulinAdmin(String insA){this.insulinAdmin=insA;}




}

