package intensive_logbook.src.sample;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class IntBackend {
//
//    private static String name1 = "a h";
//    private static String name = name1.replaceAll("\\s+","");

    public static void insertToDB(Today today, ArrayList<String> names) {

        Connection conn = null;
        Statement stmt = null;
        String name = names.get(1);
        System.out.println(name);
        final String CREATE_TABLE_SQL="insert into "+ name+" (hours, glucose, cho_grams, cho_bolus, hi_bg_bolus, basalrate, ketones_exercise) VALUES ('" +today.getTime()+"',"+today.getGluc()+","+today.getCHO_grams() +"," +today.getCHO_bolus()+"," +today.getHi_bolus()+","+today.getBasal_rate()+","+today.getKetones()+");";
        System.out.println("~~~~~~~~~~~~~~~~");
        System.out.println(CREATE_TABLE_SQL);
        try {

            conn = intensive_logbook.src.sample.ConnectionFactory.getConnection();
            stmt = conn.createStatement();

            stmt.executeUpdate(CREATE_TABLE_SQL);

            System.out.println("Values inserted");

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

