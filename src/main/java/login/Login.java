package login;

import javafx.scene.control.Alert;

import java.sql.*;
import java.util.*;

public class Login {

    public Login() {

    }

    public static boolean passwordExist = false;

    //checks the Login credentials are correct
    public static void checkLogin(List<String> list) throws SQLException
    {
        String email1 = list.get(0);
        String password = list.get(1);
        ResultSet rs = makeConnection(email1);

        try {

            if(rs.next()) {
                //Checking for password
                String dbPwd = rs.getString("password"); //from table
                if (password.equals(dbPwd)) {
                    //Redirect to next page
//                    System.out.println("User credentials match.");
                    passwordExist = true;
                } else {
                    //Print out "Invalid password!"
                    String header = "Invalid password";
                    String content = "Please fill in the correct password and submit again";
                    showAlert(header, content);
                }


            }else {
                String header = "Invalid email";
                String content = "Please fill in a different email and submit again";
                showAlert(header, content);
            }


        } catch (Exception e){
        e.printStackTrace();
        }
    }

    //Returns email, password pair for existing email in database
    public static ResultSet makeConnection(String email1) throws SQLException {
        Connection conn = DB_ALE.ConnectionFactory.getConnection();

        String sql = "SELECT email, password FROM patientsfulldetails WHERE email = ?";

        PreparedStatement ps =conn.prepareStatement(sql);
        ps.setString (1, email1);

        return ps.executeQuery();
    }

    //Check the password given the email
    private static boolean checkPassword(ResultSet rs, String password) throws SQLException {
        String dbPwd = rs.getString("password"); //from table
        if (password.equals(dbPwd))
        {
            //Redirect to next page
            System.out.println("User credentials match.");
            passwordExist = true;
        }
        return passwordExist;
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

    public static boolean getLogin(){return passwordExist;}
}

