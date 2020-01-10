package registrationFX.src.sample;

import login_OOP.src.mp_v1.DAO;

import java.sql.*;

public class registrationBackend implements DAO {
    public registrationBackend(){}

    //Input: user entered email from the registration page
    //Output: Boolean. False if the email already exists
    //True if email has not yet been registered --> Can proceed to be registered.
    public static boolean regCheckEmailIsUnique(String inputEmail) throws SQLException {

        Connection conn = sample.ConnectionFactory.getConnection();

        String sql = "SELECT email FROM patientsfulldetails WHERE email = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, inputEmail);

        ResultSet rs = ps.executeQuery();
        try {
            if (rs.next()) {
                //if enters here, email does already exist in
                System.out.println("regBackend method: Email must be unique. This email is already registered.");
                return false;
            }
            rs.close();
            conn.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        //email has not yet been registered --> Can proceed to be registered.
        return true;
    }

    //Input: Object from Patient class
    //Void method: no output.
    //This method creates the corresponding logbook depending on details
    //Only does it for simple logbook for now
    public static void createLogbook(Patient p) {

        //Simple Logbook
        if ( (p.getInsulinType().equals("No insulin intake")) & (p.getInsulinAdmin().equals("No insulin intake")) ) {
            String name1 = p.getName();
            String name = name1.replaceAll("\\s+", "");
            final String CREATE_TABLE_SQL = "CREATE TABLE " + name + " ("

                    + "timesofday VARCHAR(255),"
                    + "glucose numeric,"
                    + "carbs numeric)";



            Connection conn = null;
            Statement stmt = null;

            try {

                conn = sample.ConnectionFactory.getConnection();
                stmt = conn.createStatement();

                stmt.executeUpdate(CREATE_TABLE_SQL);

                System.out.println("Table created");

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
    }


        //registers the patient in the database
    public static void registerPatient(Patient p){
        String INSERT_USER_SQL = "INSERT INTO patientsfulldetails" +
                "  (name, contact, email, password, doctorName, doctorContact, diabetesType, insulinType, insulinAdmin) VALUES " +
                " (?, ?, ?, ?, ?,?,?,?,?);";

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            conn = sample.ConnectionFactory.getConnection();

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

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User registered");
    }


//    //Input: Object from Patient class
//    //Void method: no output.
//    //This method copies email/pwd into login verification table
//    //so user is recognized next time they login
//    public static void registerPatientInLoginTable(Patient p) throws SQLException {
//        String email = p.getEmail();
//        String pw = p.getPassword();
////        final String REG_TO_LOGIN_SQL = "INSERT INTO patientsfulldetails (email,password) \"\n" +
////                "                + \"VALUES('"+email+ "','" +pw+ ")'";
//        final String REG_TO_LOGIN_SQL = "INSERT INTO test (email,password) VALUES(?,?)";
//
//        Connection conn;
//        PreparedStatement ps = null;
//        try {
//            conn = ConnectionFactory.getConnection();
////            stmt = conn.createStatement();
//
//            ps = conn.prepareStatement(REG_TO_LOGIN_SQL);
//            ps.setString(1, email);
//            ps.setString(2, pw);
//
//            ps.executeUpdate();
//
////            ResultSet rs = ps.executeQuery();
//            System.out.println("User registered to login table");
//
//            ps.close();
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
