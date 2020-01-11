package comprehensive_logbook.src.sample;

import login_OOP.src.mp_v1.DAO;
import registrationFX.src.sample.Patient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PatientDAO implements DAO {

        public static Patient getDetailsForEmail(String email) {
            String sql = "Select * from patientsfulldetails where email like '%" +
                    email + "%'";
            Patient p = new Patient();
            Connection conn = null;
            Statement stmt = null;
            try {
                conn = comprehensive_logbook.src.sample.ConnectionFactory.getConnection();
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





