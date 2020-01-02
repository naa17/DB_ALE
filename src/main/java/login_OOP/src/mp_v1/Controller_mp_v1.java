package login_OOP.src.mp_v1;

//import com.sun.deploy.security.SelectableSecurityManager;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.*;
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


        @FXML
        public void handle(ActionEvent actionEvent) throws IOException, SQLException {
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
                        Login.checkLogin(email_pass);

                        if (login_OOP.src.mp_v1.Login.getLogin()){
                            System.out.println("YOU HAVE SAFELY LOGGED IN O.O");
                            //URL url2 = new File("/Users/admin/Documents/Java/group_project/DB_ALE/src/main/java/login_OOP/src/mp_v1/lb1_v2.fxml").toURI().toURL();
                            //Parent root2 = FXMLLoader.load(url2);
                            //Stage window2 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                            //window2.setTitle("Logbook Page");
                            //window2.setScene(new Scene(root2, 650, 400));
                            //window2.show();
                        }else {System.out.println("YUPPP NOT VORKING -.-");}


                        }else System.out.println("You have a problem :(");

                }

        }
    }




