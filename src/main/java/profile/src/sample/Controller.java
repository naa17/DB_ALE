package profile.src.sample;

import comprehensive_logbook.src.sample.PatientDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login_OOP.src.mp_v1.Controller_mp_v1;
import registrationFX.src.sample.registrationBackend;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller implements Initializable {

   @FXML
   public TextField Nametxt;
   public TextField Emailtxt;
   public TextField doctorNametxt;
   public TextField doctorEmailtxt;
   public TextField diabTypetxt;
   public TextField insTypetxt;
   public TextField insAdmintxt;
   public TextField passwordtxt;
   public TextField contactxt;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        buildData();
    }

    public void update(javafx.event.ActionEvent actionEvent) {
        System.out.println("updating");
        String name= Nametxt.getPromptText();
        String contact=contactxt.getPromptText();
        String email=Emailtxt.getPromptText();
        String password=passwordtxt.getPromptText();
        String doctorName=doctorNametxt.getPromptText();
        String doctorContact=doctorEmailtxt.getPromptText();
        String diabType=diabTypetxt.getPromptText();
        String insType=insTypetxt.getPromptText();
        String insAdmin=insAdmintxt.getPromptText();

        if(!Nametxt.getText().isEmpty())
        {name=Nametxt.getText();}
        if(!contactxt.getText().isEmpty())
        {contact=contactxt.getText();}
        if(!Emailtxt.getText().isEmpty())
        {email=Emailtxt.getText();}
        if(!passwordtxt.getText().isEmpty())
        {password=passwordtxt.getText();}
        if(!doctorNametxt.getText().isEmpty())
        {doctorName=doctorNametxt.getText();}
        if(!doctorEmailtxt.getText().isEmpty())
        {doctorContact=doctorEmailtxt.getText();}
        if(!diabTypetxt.getText().isEmpty())
        {diabType=diabTypetxt.getText();}
        if(!insTypetxt.getText().isEmpty())
        {insType=insTypetxt.getText();}
        if(!insAdmintxt.getText().isEmpty())
        {insAdmin=insAdmintxt.getText();}

        Patient p = new Patient(name, contact, email, password, doctorName, doctorContact, diabType, insType, insAdmin);
    }


    public void buildData() {
        //ConnectionFactory objDbClass = new ConnectionFactory();
        Connection con = ConnectionFactory.getConnection();
        try {
            String login_email = Controller_mp_v1.email1;
            String SQL = "Select * from patientsfulldetails  WHERE email LIKE '"+ login_email+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                Nametxt.setPromptText(rs.getString("name"));
                Emailtxt.setPromptText(rs.getString("email"));
                passwordtxt.setPromptText(rs.getString("password"));
                doctorNametxt.setPromptText(rs.getString("doctorname"));
                doctorEmailtxt.setPromptText(rs.getString("doctorcontact"));
                diabTypetxt.setPromptText(rs.getString("diabetestype"));
                insTypetxt.setPromptText(rs.getString("insulintype"));
                insAdmintxt.setPromptText(rs.getString("insulinadmin"));
                contactxt.setPromptText(rs.getString("contact"));

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

    }


    public void goLogbook(javafx.event.ActionEvent actionEvent) throws Exception
    {

        try {
                String login_email = Controller_mp_v1.email1;
                registrationFX.src.sample.Patient p = PatientDAO.getDetailsForEmail(login_email);
                if (registrationBackend.logbookType(p).equals("simple")) {
                    System.out.println("simple");
                    URL url2 = new File("src\\main\\java\\lb_v1_2.fxml").toURI().toURL();
                    Parent root2 = FXMLLoader.load(url2);
                    Stage window2 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    window2.setTitle("Simple Logbook Page");
                    window2.setScene(new Scene(root2, 800, 600));
                    window2.show();
                }
                else if (registrationBackend.logbookType(p).equals("comprehensive")){
                    System.out.println("comprehensive");
                    URL url2 = new File("src\\main\\java\\lb_v2.fxml").toURI().toURL();
                    Parent root3 = FXMLLoader.load(url2);
                    Stage window3 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    window3.setTitle("Comprehensive Logbook Page");
                    window3.setScene(new Scene(root3, 800, 600));
                    window3.show();
                }

                else if (registrationBackend.logbookType(p).equals("intensive")){
                    System.out.println("intensive");
                    URL url2 = new File("src\\main\\java\\lb_v3.fxml").toURI().toURL();
                    Parent root3 = FXMLLoader.load(url2);
                    Stage window3 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    window3.setTitle("Intensive Logbook Page");
                    window3.setScene(new Scene(root3, 1000, 1200));
                    window3.show();
                }


        }catch(Exception e){
            e.printStackTrace();
            //System.out.println("JUPP NOT VORWKKING");    // prints standard error
        }


    }




}
