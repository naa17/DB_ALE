//This code uses prepared statements
//preferred because: Never insert user input directly into your SQL query. You are giving the user the opportunity to do anything they want in your database

import java.sql.*;
import java.util.*;

public class login {
    public login() {
    }

    public static void checkLogin(List<String> list) throws SQLException {
        String email1 = list.get(0);
        String password = list.get(1);

        Connection conn = ConnectionFactory.getConnection();

        //Checking if email exists in database
//        String queryCheckEmail = "SELECT * from test WHERE email ='%\"+email1+\"%';";
        PreparedStatement ps =conn.prepareStatement("SELECT email, password from test WHERE email = ?");
        ps.setString (1, email1);
//        String queryCheckEmail = "SELECT * from test WHERE email = ?";
        //"'%\"+email+\"%';";

//        Statement st = conn.createStatement();
//        ResultSet rs = st.executeQuery(queryCheckEmail);
        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            String dbEmail = rs.getString("email"); //from table
//            System.out.println(dbEmail== email1); is false... .equals() gives true

            if (email1.equals(dbEmail)) {
                //all good
                System.out.println("email exists");
            }
        } else {
            //Print out
            System.out.println("Invalid email - Register?");
        }

        //Checking for password
//        String queryCheckPwd = "SELECT password from test WHERE password ='%\"+password+\"%';";
//        ResultSet rspwd = st.executeQuery(queryCheckPwd);
        String dbPwd = rs.getString("password"); //from table
        if (password.equals(dbPwd)) {
                //Redirect to next page
                System.out.println("User credentials match.");

        } else {
            //Print out "Invalid password!"
            System.out.println("Email and password do not match.");
        }
    }
}
