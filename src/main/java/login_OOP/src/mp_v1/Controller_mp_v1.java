package login_OOP.src.mp_v1;

//import com.sun.deploy.security.SelectableSecurityManager;
import comprehensive_logbook.src.sample.PatientDAO;
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
import registrationFX.src.sample.Patient;
import registrationFX.src.sample.registrationBackend;

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



        @FXML
        public void handle(ActionEvent actionEvent) throws IOException, SQLException {
            String email = emailField.getText();
            String passw = passwordField.getText();
            email1 = email;
            List<String> loginDetails = new ArrayList<String>();
            loginDetails = MakeList(email, passw);
            int filled=1;

            // Check empty text fields and warning error
            for(int i=0; i<loginDetails.size();i++) {
                if (loginDetails.get(i).isEmpty()) {
                    System.out.println("Please fill in all values and submit again");
                    String header = "Unfilled textfields";
                    String content = "Please fill in all values and submit again";
                    showAlert(header, content);
                    filled = 0;
                }
            }

            if(filled==1)
            {
                Login.checkLogin(loginDetails);

                objectToJson.objectToJson(verificationDAO.getDetailsForEmail(email));
                System.out.println("JSONised data: COMPLETE :D");

                if(Login.getLogin()) {
                    try {
                        Patient p = PatientDAO.getDetailsForEmail(email1);

                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println(p.getInsulinAdmin());
                        System.out.println(p.getInsulinType());
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println(registrationBackend.logbookType(p));
                        if (registrationBackend.logbookType(p).equals("simple")) {
                            System.out.println("YOU HAVE SAFELY LOGGED IN O.O");
                            URL url2 = new File("src\\main\\java\\lb_v1_2.fxml").toURI().toURL();
                            Parent root2 = FXMLLoader.load(url2);
                            Stage window2 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            window2.setTitle("Simple Logbook Page");
                            window2.setScene(new Scene(root2, 800, 600));
                            window2.show();
                        } else if (registrationBackend.logbookType(p).equals("comprehensive")) {
                            System.out.println("comprehensive");
                            URL url2 = new File("src\\main\\java\\lb_v2.fxml").toURI().toURL();
                            Parent root3 = FXMLLoader.load(url2);
                            Stage window3 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            window3.setTitle("scr\\main\\java\\Comprehensive Logbook Page");
                            window3.setScene(new Scene(root3, 800, 600));
                            window3.show();
                        } else if (registrationBackend.logbookType(p).equals("intensive")) {
                            System.out.println("intensive");
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

                }else System.out.println("You have a problem :(");

            }

        public void regButton(ActionEvent actionEvent) throws Exception{
            Register = true;
            System.out.println(Register);

            if (getRegister()) {
                System.out.println("YOU IN REGISTER");
                URL url1 = new File("src\\main\\java\\registration.fxml").toURI().toURL();
                Parent root1 = FXMLLoader.load((url1));
                System.out.println("YEAH YOU HERE");
                Stage window1 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                window1.setTitle("Registration Page");
                window1.setScene(new Scene(root1, 800, 800));
                window1.show();
            }else {System.out.println("YUp it's not working...");}
        }

        public static boolean getRegister(){
            return Register;
        }

        private List<String> MakeList(String email, String passw)
        {   // List with strings
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






