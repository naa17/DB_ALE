package login_OOP.src.mp_v1;

import javafx.scene.control.Alert;

import java.sql.*;
import java.util.*;

public class Login {
    public Login()
    {

    }
    public static boolean passwordExist = false;

    public static void checkLogin(List<String> list) throws SQLException
    {
        String email1 = list.get(0);
        String password = list.get(1);
        ResultSet rs = makeConnection(email1);

        try {
            System.out.println("TRYYY");

            if(rs.next()) {

                //Checking for password
                String dbPwd = rs.getString("password"); //from table
                if (password.equals(dbPwd)) {
                    //Redirect to next page
                    System.out.println("User credentials match.");
                    passwordExist = true;
                } else {
                    //Print out "Invalid password!"
                    String header = "Invalid password";
                    String content = "Please fill in the correct password and submit again";
                    showAlert(header, content);
                }


            }else {
                //Print out
                //Print out different email
                String header = "Invalid email";
                String content = "Please fill in a different email and submit again";
                showAlert(header, content);
            }


        } catch (Exception e){
        e.printStackTrace();
        }



    }

    //Making the connection
    public static ResultSet makeConnection(String email1) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();

        String sql = "SELECT email, password FROM patientsfulldetails WHERE email = ?";

        PreparedStatement ps =conn.prepareStatement(sql);
        ps.setString (1, email1);

        return ps.executeQuery();
    }

    //Check the password
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

