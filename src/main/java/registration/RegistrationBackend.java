//Registration page methods
package registration;

import javafx.scene.control.Alert;
import login.DAO;

import java.sql.*;

public class RegistrationBackend implements DAO {
    public RegistrationBackend() {
    }

//    Checking that the patient email is unique
//    Input: user entered email from the registration page
//    Output: Boolean. False if the email already exists
//    True if email has not yet been registered --> Can proceed to be registered.
    public static boolean regCheckEmailIsUnique(String inputEmail) throws SQLException {

        Connection conn = DB_ALE.ConnectionFactory.getConnection();

        String sql = "SELECT email FROM patientsfulldetails WHERE email = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, inputEmail);

        ResultSet rs = ps.executeQuery();
        try {
            if (rs.next()) {
                //if enters here, email does already exist in
                showAlert("Email not unique","This email is already registered. Please try with another email account.");
                return false;
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //email has not yet been registered --> Can proceed to be registered.
        return true;
    }

    //This method instantiates the SQL strings needed to be executed to create the logbook table
    //Input: Object from Patient class
    //Void method: no output.
    public static void createLogbook(DB_ALE.Patient p) {

        //Deleting spaces from 'name surname' pair
        String name1 = p.getName();
        String name = name1.replaceAll("\\s+", "");

        //Simple Logbook
        if (logbookType(p).equals("simple")) {
            final String CREATE_TABLE_SQL = "CREATE TABLE " + name + " ("

                    + "date VARCHAR(255),"
                    + "timesofday VARCHAR(255),"
                    + "glucose numeric,"
                    + "carbs numeric)";

            createLogbookType(CREATE_TABLE_SQL, name);
        }
        //comprehensive logbook
        if (logbookType(p).equals("comprehensive")) {
            final String CREATE_TABLE_SQL = "CREATE TABLE " + name + " ("

                    + "date VARCHAR(255),"
                    + "timesofday VARCHAR(255),"
                    + "glucose numeric,"
                    + "carbs numeric,"
                    + "insulin numeric)";

            createLogbookType(CREATE_TABLE_SQL, name);
        }

        //intensive logbook
        if (logbookType(p).equals("intensive")) {
            final String CREATE_TABLE_SQL = "CREATE TABLE " + name + " ("

                    + "date VARCHAR(255),"
                    + "hours VARCHAR(255),"
                    + "glucose numeric,"
                    + "cho_grams numeric,"
                    + "cho_bolus numeric,"
                    + "hi_bg_bolus numeric,"
                    + "basalrate numeric, "
                    + "ketones_exercise numeric)";

            createLogbookType(CREATE_TABLE_SQL, name);
        }
    }

//    This method creates the corresponding logbook depending on insulin details
    public static void createLogbookType(String sql, String name){
        Connection conn = null;
        Statement stmt = null;

        try {
//            Creating a connection
            conn = DB_ALE.ConnectionFactory.getConnection();
//            Creating a statement
            stmt = conn.createStatement();
//            Executing the SQL string
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close connection
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //registers the patient in the database
    public static void registerPatient(DB_ALE.Patient p) {

        String INSERT_USER_SQL = "INSERT INTO patientsfulldetails" +
                "  (name, contact, email, password, doctorName, doctorContact, diabetesType, insulinType, insulinAdmin) VALUES " +
                " (?, ?, ?, ?, ?,?,?,?,?);";

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = DB_ALE.ConnectionFactory.getConnection();

            preparedStatement = prepStat(conn, preparedStatement, p, INSERT_USER_SQL);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("User registered");
    }

//    Prepares the SQL statement to be executed
    private static PreparedStatement prepStat(Connection conn, PreparedStatement preparedStatement, DB_ALE.Patient p, String INSERT_USER_SQL) throws SQLException {
        preparedStatement = conn.prepareStatement(INSERT_USER_SQL);
        preparedStatement.setString(1, p.getName());
        preparedStatement.setString(2, p.getContact());
        preparedStatement.setString(3, p.getEmail());
        preparedStatement.setString(4, p.getPassword());
        preparedStatement.setString(5, p.getDoctorName());
        preparedStatement.setString(6, p.getDoctorContact());
        preparedStatement.setString(7, p.getDiabetesType());
        preparedStatement.setString(8, p.getInsulinType());
        preparedStatement.setString(9, p.getInsulinAdmin());

        return preparedStatement;
    }


    //determining the logbook type from user input in registration
    public static String logbookType(DB_ALE.Patient p){
        if((p.getInsulinType().equals("No insulin intake")) && (p.getInsulinAdmin().equals("No insulin intake"))){
            return "simple";
        }
        else if((p.getInsulinAdmin().equals("Insulin pump"))){
            return "intensive";
        }
        else{
            return "comprehensive";
        }
    }

    public static void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
