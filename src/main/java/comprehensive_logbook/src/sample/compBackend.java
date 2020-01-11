package comprehensive_logbook.src.sample;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class compBackend {
//
//    private static String name1 = "a h";
//    private static String name = name1.replaceAll("\\s+","");

    public static void insertToDB(Today_ins today, ArrayList<String> names) {


        System.out.println("~~~~~~~~~~~~~~~~");
        System.out.println("inside insert to db");
        System.out.println(today);
        System.out.println(names);
        Connection conn = null;
        Statement stmt = null;
        String name = names.get(1);
        final String CREATE_TABLE_SQL="insert into "+ name+" (glucose, carbs, insulin, timesofday) VALUES ("+today.getGluc()+","+today.getCarb() +"," +today.getIns()+",'" +today.getTime()+"');";
        System.out.println("~~~~~~~~~~~~~~~~");
        System.out.println(CREATE_TABLE_SQL);
        try {

            conn = comprehensive_logbook.src.sample.ConnectionFactory.getConnection();
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
