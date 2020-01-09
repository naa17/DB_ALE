package login_OOP.src.mp_v1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class verificationDAO implements DAO{
    //creates a new Verification object (email, password) from the database
    private static Verification createVerification(ResultSet rs) {
        Verification p = new Verification();
        try {
            //p.setId(rs.getInt("id"));
            p.setEmail(rs.getString("email"));
            p.setPassword(rs.getString("password"));
        } catch (SQLException ex) {
        }
        return p;
    }

    //returns Verification objects
    // corresponding to all database existing patients
    public List<Verification> getDetails() {
        String sql = "Select * from test order by name";
        List<Verification> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection
                    (DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Verification p = createVerification(rs);
                list.add(p);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
        }
        return list;
    }

    //returns Verification object list
    //containing all patients that have matching email in the DB
    public static List<Verification> getDetailsForEmail(String email) {
        String sql = "Select * from contact where email like '%" +
                email + "%'";
        List<Verification> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection
                    (DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Verification p = createVerification(rs);
                list.add(p);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
        }
        return list;
    }
}
