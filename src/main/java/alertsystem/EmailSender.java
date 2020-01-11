package alertsystem;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;



public class EmailSender
{
    public static boolean sendMail(String recipient, String recipient_name, String patientName) {
        System.out.println("Preparing to send email...");
        boolean hasSent;

        //In order to send a mail using your application - need to configure minimum 4 fields
        Session sess;
        String myAccountEmail = "dbale123123@gmail.com";
        String password = "DbA1e@@@";
        sess = doPasswordAuthentication(myAccountEmail, password);
        System.out.println(sess);
        Message message = prepareMessage(sess, myAccountEmail, recipient, recipient_name, patientName);
        System.out.println("I am entering the Transport.send(message) section...");
        try{
            Transport.send(message);
            hasSent = true;
            System.out.println("Message sent successfully!");

        } catch(MessagingException e){
            hasSent = false;
            System.out.println("Error yo");
        }

        return hasSent;
    }

    public static Session doPasswordAuthentication(String myAccountEmail, String password) {
        Properties properties = new Properties(); //used to configure the properties of the mail - it's a key-value store
        properties.put("mail.smtp.auth", "true"); //defines whether an authentication is required for the email server - for gmail it is mandatory to have a username and password
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        return session;
    }

    public static Message prepareMessage(Session session, String myAccountEmail, String recipient, String recipient_name, String patientName)
    {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            String subjectString = "Patient "+patientName + " needs your attention.";
            message.setSubject(subjectString);

            //Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            //Now set the actual message
            String messageString = "Dear Dr. " + recipient_name + ",\n\n" + patientName + "'s Blood Glucose Levels are beyond the advisory levels and requires your attention. Please find attached the document containing your patient's information.\n\nThis message has been autogenerated by DBAle so please don't reply to this email.\n\nRegards,\nDBAle";
            messageBodyPart.setText(messageString);

            //Create a multipart message
            Multipart multipart = new MimeMultipart();

            //Set text message part
            multipart.addBodyPart(messageBodyPart);

            //Working on the attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "C:\\prg3_verFri\\DB_ALE\\src\\main\\java\\alertsystem\\lectureDBP.pdf";
            FileDataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            //send the completed message parts
            message.setContent(multipart);
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}