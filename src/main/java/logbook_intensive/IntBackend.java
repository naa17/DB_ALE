package logbook_intensive;

import alertsystem.JavaMail;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class IntBackend {
//
//    private static String name1 = "a h";
//    private static String name = name1.replaceAll("\\s+","");

    public static void insertToDB(Today today, ArrayList<String> names, String login_email) {

        Connection conn = null;
        Statement stmt = null;
        String name = names.get(1);
        System.out.println(name);
        final String CREATE_TABLE_SQL="insert into "+ name+" (hours, glucose, cho_grams, cho_bolus, hi_bg_bolus, basalrate, ketones_exercise, date) VALUES ('" +today.getTime()+"',"+today.getGluc()+","+today.getCHO_grams() +"," +today.getCHO_bolus()+"," +today.getHi_bolus()+","+today.getBasal_rate()+","+today.getKetones()+ ",'"+getDate()+ "');";
        System.out.println("~~~~~~~~~~~~~~~~");
        System.out.println(CREATE_TABLE_SQL);
        try {

            conn = DB_ALE.ConnectionFactory.getConnection();
            stmt = conn.createStatement();

            stmt.executeUpdate(CREATE_TABLE_SQL);

            System.out.println("Values inserted");

            JavaMail mail = new JavaMail();

            mail.mainEmail(login_email, names,3);


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

