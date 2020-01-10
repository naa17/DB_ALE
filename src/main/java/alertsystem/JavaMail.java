package alertsystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaMail {

    //This is the main function that is run
    public static void main(String[] args) throws Exception {
        System.out.println("hi1");
        System.out.println("h345678jui98888111111");

        String strDat = "SELECT postbglu, postlglu, postdglu from testvalues WHERE id = (SELECT MAX(id) FROM testvalues)";

        boolean sent = sendEmail("krithika.balaji17@imperial.ac.uk", "Krithika", strDat);
        String str;
        if(sent == true)
        {
            str = "Message has been sent successfully!";
            System.out.println(str);
        }
        else{
            str = "Message has failed to send. Please input the correct recipient email address.";
            System.out.println(str);
        }

    }


    //This function sends an email if it needs to be sent and the email address is valid
    public static boolean sendEmail(String recipient, String recipient_name, String str) throws Exception
    {
        boolean needToSend, hasSent, emailIsValid;

        // Function checks to see if the recipient's email address is valid or not
        emailIsValid = isValidEmailAddress(recipient);
        System.out.println(recipient + ": Is email valid? " + emailIsValid);

        if (emailIsValid == true){
            //Function checks to see if any values from the logbook are out of bounds
            needToSend = checkValues(str);

            if (needToSend == true)
            {
                hasSent = EmailSender.sendMail(recipient, recipient_name);
            }
            else {
                hasSent = false;
            }
            return hasSent;
        }
        else{
            hasSent = false;
            return hasSent;
        }
    }

    // This function checks the database and returns a true or false depending on whether on not an email needs to be sent
    public static boolean checkValues(String str) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        //Create the sql string selecting the post breakfast, lunch and dinner BGC for the last entry

        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(str);

        //Setting up values
        int BGC_Average_upper_post_b_glu, BGC_Average_lower_post_b_glu, count;
        BGC_Average_upper_post_b_glu = 5;
        BGC_Average_lower_post_b_glu = 9;
        count = 0;

        while(rs.next())
        {
            if (rs.getInt("postbglu") >BGC_Average_upper_post_b_glu || rs.getInt("postbglu")<BGC_Average_lower_post_b_glu) {
                count = count + 1;
            }
        }
        boolean value;
        value = false;
        if(count > 0)
        {
            value = true;
        }
        return value;
    }

    //This function checks to see if the recipient's email address is valid or not
    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

}
