//Simple logbook methods
package logbook_simple;

import DB_ALE.Today;
import alertsystem.JavaMail;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class logbookBackend {

//    Inserting values into the user's logbook table in the database
    public static void insertToDB(Today today, ArrayList<String> names, String login_email) {

        Connection conn = null;
//        Creating a statement
        Statement stmt = null;
        final String CREATE_TABLE_SQL="insert into "+ names.get(1)+" (glucose, carbs, timesofday, date) VALUES ("+today.getGluc()+","+today.getCarb() +",'" +today.getTime()+"','"+getDate()+"');";

        try {
//          Establishing a connection to the database
            conn =  DB_ALE.ConnectionFactory.getConnection();
            stmt = conn.createStatement();
//
            stmt.executeUpdate(CREATE_TABLE_SQL);

            JavaMail mail = new JavaMail();
//          Alert system feature - only checks post meal values
            if (today.getTime().substring(0,2).equals("po")){
                mail.mainEmail(login_email, names, 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
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
// Function from https://dzone.com/articles/getting-current-date-time-in-java
    public static String getDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate today = LocalDate.now();

        return (formatter.format(today));
    }
    // end of reference

}