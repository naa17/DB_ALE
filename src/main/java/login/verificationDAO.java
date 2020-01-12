//Verification data access object

package login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class verificationDAO implements DAO {
    //creates a new Verification object (email, password) from the database
    private static Verification createVerification(ResultSet rs) throws SQLException {
        Verification p = new Verification();
        try {
            p.setEmail(rs.getString("email"));
            p.setPassword(rs.getString("password"));
        }catch(SQLException ex){}
        return p;
    }

//    Creates a verification object from a list of strings
    public static Verification createPatient(List<String> listname) {
        Verification p = new Verification();
        p.setEmail(listname.get(0));
        p.setPassword(listname.get(1));
        return p;
    }

    //returns Verification objects corresponding to all database existing patients
    public List<Verification> getDetails() {
        String sql = "Select * from patientsfulldetails order by name";
        List<Verification> list = new ArrayList<>();
        try {
//            Registering the driver
            Class.forName(DRIVER);
//            Establishing the connection with the database. This uses the interface's details
            Connection con = DriverManager.getConnection
                    (DB_URL, USER, PASS);
//            Creating the statement
            Statement stmt = con.createStatement();
//            Executing the SQL string. This returns a result set
            ResultSet rs = stmt.executeQuery(sql);
//            Looping through the result set which returns (email, password) and storing them into a string list
            while (rs.next()) {
                Verification p = createVerification(rs);
                list.add(p);
            }
            rs.close();
            con.close();
//            Closing the result set and connection
        } catch (ClassNotFoundException | SQLException ex) {
        }
        return list;
    }

    //returns Verification object list containing all patients that have matching email in the DB
    public static List<Verification> getDetailsForEmail(String email) {
        String sql = "Select * from patientsfulldetails where email like '%" +
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