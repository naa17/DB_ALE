package mp_v1;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



    public class Controller_mp1 {
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

            List<String> loginDetails = new ArrayList<String>();
            loginDetails.add(email);
            loginDetails.add(passw);
            System.out.println(loginDetails);

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




