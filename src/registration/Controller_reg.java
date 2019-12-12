package registration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sun.management.snmp.jvminstr.JvmRTBootClassPathEntryImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;



public class Controller_reg {
    @FXML private TextField PatientName;
    @FXML private TextField PatientContact;
    @FXML private TextField email;
    @FXML private TextField password;
    @FXML private TextField doctorName;
    @FXML private TextField doctorContact;
    @FXML private ChoiceBox diabType;
    @FXML private ChoiceBox insType;
    @FXML private ChoiceBox insAdmin;

    @FXML
    private void SubmitDetails(ActionEvent event) throws IOException {
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

        for (int i = 0; i < patientDetails.size(); i++) {
            System.out.println(patientDetails.get(i));

            if (patientDetails.get(i) != "") {
                System.out.println("Please fill in all values and submit again");
                break;
            }

            else {
                File file = new File("RegistrationDetails.csv");
                if (file.createNewFile()) {
                    System.out.println("Done");

                    FileWriter csvWriter = new FileWriter("RegistrationDetails.csv");
                    for (int j = 0; j < patientDetails.size(); j++) {
                        csvWriter.append(String.join(",", patientDetails.get(i)));
                        csvWriter.append("\n");
                    }
                    csvWriter.flush();
                    csvWriter.close();

                    // List<String> registration = CSVreader_mp.readCSV("RegistrationDetails.csv");

                } else System.out.println("You have a problem :(");
            }


        }
    }
}