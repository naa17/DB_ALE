//Login page Controller
//Specifies what actions to take when buttons are pushed.
package login;

import DB_ALE.Patient;
import logbook_comprehensive.PatientDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import registration.RegistrationBackend;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller_mp_v1 {
        @FXML
        public TextField emailField;
        @FXML
        public TextField passwordField;
        @FXML
        public Button login;

        public static String email1;

        public static boolean Register = false;


        //Log in button
        @FXML
        public void handle(ActionEvent actionEvent) throws IOException, SQLException {
            String email = emailField.getText();
            String passw = passwordField.getText();
            email1 = email;
            List<String> loginDetails = new ArrayList<String>();
//            saving (email, password) pair into a string list
            loginDetails = MakeList(email, passw);
            int filled=1;

            // Checking all fields are filled - warning error
            for(int i=0; i<loginDetails.size();i++) {
                if (loginDetails.get(i).isEmpty()) {
                    String header = "Unfilled textfields";
                    String content = "Please fill in all values and submit again";
                    showAlert(header, content);
                    filled = 0;
                }
            }

            if(filled==1)
            //if the text fields are filled
            {
                Login.checkLogin(loginDetails);

//                objectToJson.objectToJson(verificationDAO.getDetailsForEmail(email));

                if(Login.getLogin()) {
                    //if the password and email exist and match
                    try {
                        //create a new Patient object. Stores all their information
                        Patient p = PatientDAO.getDetailsForEmail(email1);

                        //checking which logbook corresponds to the logged user-redirecting to their logbook page
                        if (RegistrationBackend.logbookType(p).equals("simple")) {
                            URL url2 = new File("src\\main\\java\\lb_v1_2.fxml").toURI().toURL();
                            Parent root2 = FXMLLoader.load(url2);
                            Stage window2 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            window2.setTitle("Simple Logbook Page");
                            window2.setScene(new Scene(root2, 800, 600));
                            window2.show();
                        } else if (RegistrationBackend.logbookType(p).equals("comprehensive")) {
                            URL url2 = new File("src\\main\\java\\lb_v2.fxml").toURI().toURL();
                            Parent root3 = FXMLLoader.load(url2);
                            Stage window3 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            window3.setTitle("scr\\main\\java\\Comprehensive Logbook Page");
                            window3.setScene(new Scene(root3, 800, 600));
                            window3.show();
                        } else if (RegistrationBackend.logbookType(p).equals("intensive")) {
                            URL url2 = new File("src\\main\\java\\lb_v3.fxml").toURI().toURL();
                            Parent root3 = FXMLLoader.load(url2);
                            Stage window3 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            window3.setTitle("Intensive Logbook Page");
                            window3.setScene(new Scene(root3, 1000, 1200));
                            window3.show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                }else {
            }

        }

        //Go to registration page when registration button is clicked
//    Specifies the registration page fxml file directory
        public void regButton(ActionEvent actionEvent) throws Exception{
            Register = true;

            if (getRegister()) {
                URL url1 = new File("src\\main\\java\\registration.fxml").toURI().toURL();
                Parent root1 = FXMLLoader.load((url1));
                Stage window1 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                window1.setTitle("Registration Page");
                window1.setScene(new Scene(root1, 800, 800));
                window1.show();
            }else {
//                System.out.println("Registration not working...");
            }
        }

        public static boolean getRegister(){
            return Register;
        }

        //Save email and password into a string list
        private List<String> MakeList(String email, String passw)
        {
            List<String> loginDetails = new ArrayList<String>();
            loginDetails.add(email);
            loginDetails.add(passw);
            System.out.println(loginDetails);
            return loginDetails;
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






