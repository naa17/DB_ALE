package logbook_comprehensive;

import alertsystem.JavaMail;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CompBackend {
//
//    private static String name1 = "a h";
//    private static String name = name1.replaceAll("\\s+","");

    public static void insertToDB(Today_ins today, ArrayList<String> names, String login_email) {


        System.out.println("~~~~~~~~~~~~~~~~");
        System.out.println("inside insert to db");
        System.out.println(today);
        System.out.println(names);
        Connection conn = null;
        Statement stmt = null;
        String name = names.get(1);
        String date = getDate();
        final String CREATE_TABLE_SQL="insert into "+ name+" (glucose, carbs, insulin, timesofday, date) VALUES ("+today.getGluc()+","+today.getCarb() +"," +today.getIns()+",'" +today.getTime()+"','"+getDate()+"');";
        System.out.println("~~~~~~~~~~~~~~~~");
        System.out.println(CREATE_TABLE_SQL);
        try {

            conn = DB_ALE.ConnectionFactory.getConnection();
            stmt = conn.createStatement();

            stmt.executeUpdate(CREATE_TABLE_SQL);

            System.out.println("Values inserted");

            JavaMail mail = new JavaMail();

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
    public static String getDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate today = LocalDate.now();

        return (formatter.format(today));
    }
    // end of reference
}
