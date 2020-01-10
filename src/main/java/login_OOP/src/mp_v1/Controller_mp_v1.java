package login_OOP.src.mp_v1;

//import com.sun.deploy.security.SelectableSecurityManager;
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
            System.out.println(email);
            System.out.println(passw);
            int filled=1;

            email1 = email;


            // List with strings
            List<String> loginDetails = new ArrayList<String>();
            loginDetails.add(email);
            loginDetails.add(passw);
            System.out.println(loginDetails);

            // Check empty text fields and warning error
            for(int i=0; i<loginDetails.size();i++) {
                if (loginDetails.get(i).isEmpty()) {
                    System.out.println("Please fill in all values and submit again");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("Unfilled textfields");
                    alert.setContentText("Please fill in all values and submit again");
                    filled = 0;
                    alert.showAndWait();

                }
            }

                if(filled==1)
                {
                        Login.checkLogin(loginDetails);
                        objectToJson.objectToJson(verificationDAO.getDetailsForEmail(email));
                        System.out.println("JSONised data: COMPLETE :D");



                        try {
                            if (login_OOP.src.mp_v1.Login.getLogin()){
                                System.out.println("YOU HAVE SAFELY LOGGED IN O.O");
                                URL url2 = new File("C:\\prg3_verFri\\DB_ALE\\src\\main\\java\\simple_logbook\\src\\sample\\lb_v1_2.fxml").toURI().toURL();
                                Parent root2 = FXMLLoader.load(url2);
                                Stage window2 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                                window2.setTitle("Logbook Page");
                                window2.setScene(new Scene(root2, 800, 600));
                                window2.show();

                            }

                        }catch(Exception e){
                            e.printStackTrace();
                            //System.out.println("JUPP NOT VORWKKING");    // prints standard error
                        }


                        }else System.out.println("You have a problem :(");

                }

        public void regButton(ActionEvent actionEvent) throws Exception{
            Register = true;
            System.out.println(Register);
            //System.exit(0);

            if (getRegister()) {
                System.out.println("YOU IN REGISTER");
                URL url1 = new File("C:\\prg3_verFri\\DB_ALE\\src\\main\\java\\registrationFX\\src\\sample\\registration.fxml").toURI().toURL();
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

    }






