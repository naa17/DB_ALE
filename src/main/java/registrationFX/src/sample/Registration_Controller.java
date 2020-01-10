package registrationFX.src.sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
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


    @FXML
    private void SubmitDetails(ActionEvent event) throws IOException, SQLException {
        event.consume();
        String patient_Name = PatientName.getText();
        String patient_Contact = PatientContact.getText();
        String patient_Email = email.getText();
        String patient_Password = password.getText();
        String doctor_Name = doctorName.getText();
        String doctor_Contact = doctorContact.getText();
        String diabetes_Type = diabType.getValue().toString();
        String insulin_Type = insType.getValue().toString();
        String insulin_Admin = insAdmin.getValue().toString();
        int filled = 1;

        List<String> patientDetails = new ArrayList<String>();
        patientDetails.add(patient_Name);
        patientDetails.add(patient_Contact);
        patientDetails.add(patient_Email);
        patientDetails.add(patient_Password);
        patientDetails.add(doctor_Name);
        patientDetails.add(doctor_Contact);
        patientDetails.add(diabetes_Type);
        patientDetails.add(insulin_Type);
        patientDetails.add(insulin_Admin);


        Patient p = new Patient(patientDetails.get(0), patientDetails.get(1), patientDetails.get(2), patientDetails.get(3),
                patientDetails.get(4), patientDetails.get(5), patientDetails.get(6), patientDetails.get(7), patientDetails.get(8));


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


        if (filled == 1) {
            System.out.println("ready to get rollinnn");
            //String name_no_spaces = patient_Name.replaceAll("\\s+", "");
            //System.out.println(name_no_spaces);
            if (registrationBackend.regCheckEmailIsUnique(patientDetails.get(2))){
                registrationBackend.createLogbook(p);
                registrationBackend.registerPatient(p);
                System.out.println("we rollinnn");
            }


            //File file = new File("RegistrationDetails.csv");
            //if (file.createNewFile()) {
            //System.out.println("Done");

            //FileWriter csvWriter = new FileWriter("RegistrationDetails.csv");
            //for (int j = 0; j < patientDetails.size(); j++) {
            //csvWriter.append(String.join(",", patientDetails.get(j)));
            //csvWriter.append("\n");
            //}
            //csvWriter.flush();
            //csvWriter.close();

            // List<String> registration = CSVreader_mp.readCSV("RegistrationDetails.csv");


            //Patient patient= new Patient(patient_Name, patient_Contact, patient_Email, patient_Password,
            //doctor_Name, doctor_Contact, diabetes_Type, insulin_Type, insulin_Admin);


        } else {
            System.out.println("You have a problem :(");

        }

    }

}







