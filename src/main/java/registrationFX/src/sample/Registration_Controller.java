package registrationFX.src.sample;
import comprehensive_logbook.src.sample.PatientDAO;
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

    @FXML
    private void SubmitDetails(ActionEvent event) throws IOException, SQLException {
        event.consume();

        List<String> patientDetails = new ArrayList<String>();
        patientDetails = getPatientDetails();
        int filled = 1;

        Patient p = new Patient(patientDetails.get(0), patientDetails.get(1), patientDetails.get(2), patientDetails.get(3),
                patientDetails.get(4), patientDetails.get(5), patientDetails.get(6), patientDetails.get(7), patientDetails.get(8));

        emailReg = p.getEmail();

        for (int i = 0; i < patientDetails.size(); i++) {
            System.out.println(patientDetails.get(i));

            if (patientDetails.get(i).isEmpty()) {
                System.out.println("Please fill in all values and submit again");
                filled = 0;
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Unfilled textfields");
                alert.setContentText("Please fill in all values and submit again");

                alert.showAndWait();

            }

        }


        if (filled == 1 && isValidEmailAddress(email.getText()) && isValidEmailAddress(doctorContact.getText())) {
            System.out.println("ready to get rollinnn");
            //String name_no_spaces = patient_Name.replaceAll("\\s+", "");
            //System.out.println(name_no_spaces);
            if (registrationBackend.regCheckEmailIsUnique(patientDetails.get(2))){
                registrationBackend.createLogbook(p);
                registrationBackend.registerPatient(p);
                System.out.println("we rollinnn");

                try {
                    Patient p1 = PatientDAO.getDetailsForEmail(email.getText());
                    if (registrationBackend.logbookType(p1).equals("simple")) {
                        System.out.println("simple");
                        URL url2 = new File("src\\main\\java\\lb_v1_2.fxml").toURI().toURL();
                        Parent root2 = FXMLLoader.load(url2);
                        Stage window2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window2.setTitle("Simple Logbook Page");
                        window2.setScene(new Scene(root2, 800, 600));
                        window2.show();
                    }
                    if (registrationBackend.logbookType(p).equals("comprehensive")){
                        System.out.println("comprehensive");
                        URL url2 = new File("src\\main\\java\\lb_v2.fxml").toURI().toURL();
                        Parent root3 = FXMLLoader.load(url2);
                        Stage window3 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window3.setTitle("Comprehensive Logbook Page");
                        window3.setScene(new Scene(root3, 800, 600));
                        window3.show();
                    }

                    if (registrationBackend.logbookType(p).equals("intensive")){
                        System.out.println("intensive");
                        URL url2 = new File("src\\main\\java\\lb_v3.fxml").toURI().toURL();
                        Parent root3 = FXMLLoader.load(url2);
                        Stage window3 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window3.setTitle("Intensive Logbook Page");
                        window3.setScene(new Scene(root3, 1000, 1200));
                        window3.show();
                    }

                }catch(Exception e){
                    e.printStackTrace();
                    //System.out.println("JUPP NOT VORWKKING");    // prints standard error
                }
            }
;


        } else if(!isValidEmailAddress(email.getText())|| !isValidEmailAddress(doctorContact.getText())){
            showAlert("Invalid email address","The email you entered is not a valid email");

        }
    }

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

    //The below function was gotten from: https://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
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