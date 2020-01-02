package login_OOP.src.mp_v1;

import java.sql.*;
import java.util.*;

public class Login {
    public Login() {
    }
    public static boolean login = false;

    public static void checkLogin(List<String> list) throws SQLException {
        String email1 = list.get(0);
        String password = list.get(1);

        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement ps =conn.prepareStatement("SELECT email, password from test WHERE email = ?");
        ps.setString (1, email1);

        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            String dbEmail = rs.getString("email"); //from table

            if (email1.equals(dbEmail)) {
                //all good
                System.out.println("email exists");
            }
        } else {
            //Print out
            System.out.println("Invalid email - Register?");
        }
    
        //Checking for password
        String dbPwd = rs.getString("password"); //from table
        if (password.equals(dbPwd)) {
            //Redirect to next page
            System.out.println("User credentials match.");
            login = true;

        } else {
            //Print out "Invalid password!"
            System.out.println("Email and password do not match.");
        }
    }


    public static boolean getLogin(){return login;}
}