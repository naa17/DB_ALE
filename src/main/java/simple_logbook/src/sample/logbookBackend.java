package simple_logbook.src.sample;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class logbookBackend {
//
//    private static String name1 = "a h";
//    private static String name = name1.replaceAll("\\s+","");

    public static void insertToDB(Today_v1_2 today, String name) {

        Connection conn = null;
        Statement stmt = null;
        final String CREATE_TABLE_SQL="insert into "+ name+" (glucose, carbs, timesofday) VALUES ("+today.getGluc()+","+today.getCarb() +",'" +today.getTime()+"');";

        try {

            conn = sample.ConnectionFactory.getConnection();
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