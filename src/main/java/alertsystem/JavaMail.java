package alertsystem;

import alertsystem.EmailSender;
import alertsystem.ConnectionFactory;
/*
import org.postgresql.core.ConnectionFactory;
*/

import java.sql.Connection;
import java.sql.PreparedStatement;

public class JavaMail {
    public static void main(String[] args) throws Exception {
        /*Connection conn = ConnectionFactory.getConnection();

        PreparedStatement ps =conn.prepareStatement("SELECT email, password from test WHERE email = ?");
*/
        int BGC_Average_upper, BGC_Average_lower, myBGC;
        BGC_Average_upper = 4;
        BGC_Average_lower = 7;
        myBGC = 40;
        if (myBGC>BGC_Average_upper || myBGC<BGC_Average_lower){
            EmailSender.sendMail("krithika.balaji17@imperial.ac.uk", "Krithika Balaji");
        }
        else{
            System.out.println("Your BGC levels are fine! no email has been sent!");
        }
    }
}
