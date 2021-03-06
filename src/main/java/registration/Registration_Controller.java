//Registration Controller
//Specifies actions of page buttons.
package registration;
import logbook_comprehensive.PatientDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Registration_Controller {
    @FXML
    private TextField PatientName;
    @FXML
    private TextField PatientContact;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField doctorName;
    @FXML
    private TextField doctorContact;
    @FXML
    private ChoiceBox diabType;
    @FXML
    private ChoiceBox insType;
    @FXML
    private ChoiceBox insAdmin;

    public static String emailReg;

//    Registration submit button action: registers the patient into the database (table patientsfulldetails), and creates their logbook table
    @FXML
    private void SubmitDetails(ActionEvent event) throws IOException, SQLException {
        event.consume();

        List<String> patientDetails = new ArrayList<String>();
        patientDetails = getPatientDetails();
        int filled = 1;

        DB_ALE.Patient p = new DB_ALE.Patient(patientDetails.get(0), patientDetails.get(1), patientDetails.get(2), patientDetails.get(3),
                patientDetails.get(4), patientDetails.get(5), patientDetails.get(6), patientDetails.get(7), patientDetails.get(8));

        emailReg = p.getEmail();

        for (int i = 0; i < patientDetails.size(); i++) {

//            Checking that the registration text fields are filled
            if (patientDetails.get(i).isEmpty()) {
//                System.out.println("Please fill in all values and submit again");
                filled = 0;
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Unfilled textfields");
                alert.setContentText("Please fill in all values and submit again");

                alert.showAndWait();

            }

        }

//      If the text fields are filled, and the email addresses (for doctor, and patient) are of correct email syntax
        if (filled == 1 && isValidEmailAddress(email.getText()) && isValidEmailAddress(doctorContact.getText())) {

//            Checking that that the patient email is uniqua, a.k.a not yet registered on the database
            if (RegistrationBackend.regCheckEmailIsUnique(patientDetails.get(2))){
//                If it is unique
                RegistrationBackend.createLogbook(p); //a corresponding logbooktable  is created int the database
                RegistrationBackend.registerPatient(p); //the patient is registered on the database

//                Redirecting to corresponding next page: Logbook page
                try {
                    DB_ALE.Patient p1 = PatientDAO.getDetailsForEmail(email.getText());
                    if (RegistrationBackend.logbookType(p1).equals("simple")) {
                        URL url2 = new File("src\\main\\java\\lb_v1_2.fxml").toURI().toURL();
                        Parent root2 = FXMLLoader.load(url2);
                        Stage window2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window2.setTitle("Simple Logbook Page");
                        window2.setScene(new Scene(root2, 800, 600));
                        window2.show();
                    }
                    if (RegistrationBackend.logbookType(p).equals("comprehensive")){
                        System.out.println("comprehensive");
                        URL url2 = new File("src\\main\\java\\lb_v2.fxml").toURI().toURL();
                        Parent root3 = FXMLLoader.load(url2);
                        Stage window3 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window3.setTitle("Comprehensive Logbook Page");
                        window3.setScene(new Scene(root3, 800, 600));
                        window3.show();
                    }
                    if (RegistrationBackend.logbookType(p).equals("intensive")){
                        System.out.println("intensive");
                        URL url2 = new File("src\\main\\java\\lb_v3.fxml").toURI().toURL();
                        Parent root3 = FXMLLoader.load(url2);
                        Stage window3 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window3.setTitle("Intensive Logbook Page");
                        window3.setScene(new Scene(root3, 1000, 1200));
                        window3.show();
                    }

                }catch(Exception e){
                    e.printStackTrace();// prints standard error
                }
            }
        } else if(!isValidEmailAddress(email.getText())|| !isValidEmailAddress(doctorContact.getText())){
//            The email address syntax is invalid
            showAlert("Invalid email address","The email you entered is not a valid email");

        }
    }

//    Creating a string list containing all patient details from the registration page
    private List<String> getPatientDetails() {
        List<String> patientDetails = new ArrayList<String>();
        patientDetails.add(PatientName.getText());
        patientDetails.add(PatientContact.getText());
        patientDetails.add(email.getText());
        patientDetails.add(password.getText());
        patientDetails.add(doctorName.getText());
        patientDetails.add(doctorContact.getText());
        patientDetails.add(diabType.getValue().toString());
        patientDetails.add(insType.getValue().toString());
        patientDetails.add(insAdmin.getValue().toString());
        return patientDetails;
    }

    //This function checks to see if the recipient's email address is valid or not
    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    //This function creates alerts and pop-up windows
    private static void showAlert(String header, String content)
    {
        Alert alert1 = new Alert(Alert.AlertType.WARNING);
        alert1.setTitle("Warning Dialog");
        alert1.setHeaderText(header);
        alert1.setContentText(content);
        alert1.showAndWait();
    }
}