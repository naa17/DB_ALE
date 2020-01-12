package registration;

import DB_ALE.ConnectionFactory;
import DB_ALE.Patient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import registration.RegistrationBackend;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class RegistrationBackendTest {

    RegistrationBackend regBack = new RegistrationBackend();
    Connection conn;
    Patient p;
    PreparedStatement prepStat;
    String userSQL;
    ResultSet rs;

    @Before
    public void setUp()
    {
        conn = ConnectionFactory.getConnection();
        p = new Patient();
        userSQL = "INSERT INTO patientsfulldetails" +
            "  (name, contact, email, password, doctorName, doctorContact, diabetesType, insulinType, insulinAdmin) VALUES " +
            " (?, ?, ?, ?, ?,?,?,?,?);";

        Connection conn = ConnectionFactory.getConnection();

        String sql = "SELECT email FROM patientsfulldetails WHERE email = ?";

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ps.setString(1, "p@gmail.com");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ResultSet rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPrepStat() throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {

        Method method = RegistrationBackend.class.getDeclaredMethod("prepStat", Connection.class, PreparedStatement.class, Patient.class, String.class);
        method.setAccessible(true);
        assertEquals(true, method.invoke(regBack, conn, prepStat, p, userSQL) instanceof PreparedStatement);
    }
    @After
    public void after() throws SQLException {
        conn.close();
    }

}