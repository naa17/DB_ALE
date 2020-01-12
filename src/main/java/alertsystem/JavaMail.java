package alertsystem;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JavaMail {

    //Code gotten from: https://www.youtube.com/watch?v=WKkDgLvU0m0
    public static EmailSender emailsender = new EmailSender();

    //This is the main function that is run
    public static void mainEmail(String login_email, ArrayList<String> names, int logbookType) throws Exception
    {
        //names.get(0) is name with spaces
        //names.get(1) is name without spaces

        //Initialize string statements for database
        String getGlu;
        if(logbookType != 3)
        {
             getGlu = "SELECT glucose from " + names.get(1) + " WHERE timesofday LIKE 'po%' and date LIKE '" + getDate() +"'";
        }else{
            getGlu = "SELECT glucose from " + names.get(1) + " WHERE date LIKE '" + getDate() + "'";
        }

        String getDoc = "SELECT doctorcontact, doctorname from patientsfulldetails WHERE email like '" + login_email +"'";

        //Initialize arrays
        ArrayList<String> doc = new ArrayList<>();
        ArrayList<Double> glucoseList = new ArrayList<>();

        //extract the values for doctor details and the list of glucose values
        doc = (ArrayList<String>) extractDoctor(getDoc);
        glucoseList = (ArrayList<Double>) extractGlucoseList(getGlu);
        boolean sent = sendEmail(doc.get(0), doc.get(1), glucoseList, names.get(0));
        String content, header;
        if(sent == true) {
            header = "Doctor alerted!";
            content = "Your glucose values are out of bounds - your doctor has been alerted.";
            showAlert(header, content);
            System.out.println(content);
        }
    }

    // Function from https://dzone.com/articles/getting-current-date-time-in-java
    public static String getDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate today = LocalDate.now();

        return (formatter.format(today));
    }
// end of reference


    //This function sends an email if it needs to be sent and the email address is valid
    public static boolean sendEmail(String recipient, String recipient_name, ArrayList<Double> glucose, String patientName) throws Exception
    {
        boolean hasSent = false;
        emailsender = new EmailSender();
        //Function checks to see if any values from the logbook are out of bounds
        if (checkValues(glucose)) {
            hasSent = emailsender.sendMail(recipient, recipient_name, patientName);
        }

        return hasSent;
    }

    public static List<Double> extractGlucoseList(String str) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        //Create the sql string selecting the post breakfast, lunch and dinner BGC for the last entry

        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(str);

        ArrayList<Double> postglucose = new ArrayList<>();
        double tempglu;
        while(rs.next())
        {
            tempglu = Double.valueOf(rs.getInt("glucose"));
            postglucose.add(tempglu); //This is glucose
        }
        System.out.println("postglucose values: " + postglucose);
        return postglucose;
    }

    public static ArrayList<String> extractDoctor(String str) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        //Create the sql string selecting the post breakfast, lunch and dinner BGC for the last entry

        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(str);

        ArrayList<String> doctor = new ArrayList<>();

        while(rs.next())
        {
            doctor.add(rs.getString(1)); //This is email doctor
            doctor.add(rs.getString(2)); //This is name doctor
        }
        System.out.println("doctor list: " + doctor);
        return doctor;
    }

    // This function checks the database and returns a true or false depending on whether on not an email needs to be sent
    public static boolean checkValues(ArrayList<Double> glucose)
    {
        //Setting up values
        int BGC_Average_upper_post_b_glu, BGC_Average_lower_post_b_glu, count;
        BGC_Average_upper_post_b_glu = 165;
        BGC_Average_lower_post_b_glu = 95;
        count = 0;

        for(int i = 0; i < glucose.size(); i++)
        {
            if (glucose.get(i) > BGC_Average_upper_post_b_glu || glucose.get(i) < BGC_Average_lower_post_b_glu)
            {
                count = count + 1;
            }
        }

        boolean value;
        value = false;
        if(count > 2) //If the person has 2 glucose values out of bounds
        {
            value = true;
        }

        return value;
    }
    //This function creates alerts and pop-up windows
    private static void showAlert(String header, String content)
    {
        Alert alert1 = new Alert(Alert.AlertType.WARNING);
        alert1.setTitle("Warning Dialog");
        alert1.setHeaderText(header);
        alert1.setContentText(content);
        alert1.showAndWait();
    }
}