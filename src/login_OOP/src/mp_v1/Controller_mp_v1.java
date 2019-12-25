package mp_v1;

import com.sun.deploy.security.SelectableSecurityManager;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



    public class Controller_mp_v1 {
        @FXML
        public TextField emailField;
        @FXML
        public TextField passwordField;
        @FXML
        public Button login;


        @FXML
        public void handle(ActionEvent actionEvent) throws IOException {

            String email = emailField.getText();
            String passw = passwordField.getText();
            System.out.println(email);
            System.out.println(passw);
            int filled=1;

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
                    // CLASS DECLARATION BELOW
                    Verification verification = new Verification(email, passw);

                    // CSV code below - delete if OOP is successful
                    File file = new File("logindetails.csv");
                    if (file.createNewFile()){
                        System.out.println("Done");

                        FileWriter csvWriter = new FileWriter("logindetails.csv");
                        csvWriter.append(loginDetails.get(0));
                        csvWriter.append(",");
                        csvWriter.append(loginDetails.get(1));

                        csvWriter.flush();
                        csvWriter.close();

                        List<String> email_pass = CSVreader_mp.readCSV("logindetails.csv");

                    }else System.out.println("You have a problem :(");
                }



        }
    }




