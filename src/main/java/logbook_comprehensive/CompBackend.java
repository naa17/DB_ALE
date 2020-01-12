//Intensive logbook page methods
package logbook_comprehensive;

import alertsystem.JavaMail;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CompBackend {
    //    Inserting values into the user's logbook table in the database
    public static void insertToDB(Today_comp today, ArrayList<String> names, String login_email) {

        Connection conn = null;
        Statement stmt = null;
        String name = names.get(1);
        String date = getDate();
        final String CREATE_TABLE_SQL="insert into "+ name+" (glucose, carbs, insulin, timesofday, date) VALUES ("+today.getGluc()+","+today.getCarb() +"," +today.getIns()+",'" +today.getTime()+"','"+getDate()+"');";
        try {

            conn = DB_ALE.ConnectionFactory.getConnection();
            stmt = conn.createStatement();

            stmt.executeUpdate(CREATE_TABLE_SQL);

            JavaMail mail = new JavaMail();
//          Alert system feature - only checks post meal values
            if (today.getTime().substring(0,2).equals("po")){
                mail.mainEmail(login_email, names, 2);
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
//    Getting current date - returns it
    public static String getDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate today = LocalDate.now();

        return (formatter.format(today));
    }
    // end of reference
}
