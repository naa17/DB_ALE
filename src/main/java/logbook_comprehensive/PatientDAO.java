//Patient data access object
package logbook_comprehensive;

import login.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PatientDAO implements DAO {


    //creates a new Patient object from the database's details
        public static DB_ALE.Patient getDetailsForEmail(String email) {
            String sql = "Select * from patientsfulldetails where email like '%" +
                    email + "%'";
            DB_ALE.Patient p = new DB_ALE.Patient();
            Connection conn = null;
            Statement stmt = null;
            try {
                conn = DB_ALE.ConnectionFactory.getConnection();
                stmt = conn.createStatement();

                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    p.setName(rs.getString("name"));
                    p.setContact(rs.getString("contact"));
                    p.setEmail(rs.getString("password"));
                    p.setDoctorName(rs.getString("doctorname"));
                    p.setDoctorContact(rs.getString("doctorcontact"));
                    p.setDiabetesType(rs.getString("diabetestype"));
                    p.setInsulinType(rs.getString("insulintype"));
                    p.setInsulinAdmin(rs.getString("insulinadmin"));
                }
                rs.close();
                conn.close();
            } catch (SQLException ex) {
            }

            return p;
        }

    }





