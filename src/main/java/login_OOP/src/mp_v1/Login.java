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

        String sql = "SELECT email, password FROM test WHERE email = ?";

        PreparedStatement ps =conn.prepareStatement(sql);
        ps.setString (1, email1);

        ResultSet rs = ps.executeQuery();
        try {
            System.out.println("TRYYY");


            if(rs.next()) {
                System.out.println("NEXTTTTT");
                String dbEmail = rs.getString("email"); //from table

                if (email1.equals(dbEmail)) {
                    //all good
                    System.out.println("email exists");
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


            }else {
                //Print out
                System.out.println("Invalid email - Register?");
            }


        } catch (Exception e){
        e.printStackTrace();
        }



}


    public static boolean getLogin(){return login;}
}