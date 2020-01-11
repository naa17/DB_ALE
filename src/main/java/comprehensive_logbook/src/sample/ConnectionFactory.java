package comprehensive_logbook.src.sample;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    public static Connection getConnection() {

        try {

            URI dbUri = new URI("postgres://izhceqwtylsafx:a08a6c57d5e074d7f40765b544ac1ca27216bbef5ce8eeba3e31c3ab5a8de82a@ec2-54-217-243-19.eu-west-1.compute.amazonaws.com:5432/d9j82vpkevcs4r");



            String username = dbUri.getUserInfo().split(":")[0];

            String password = dbUri.getUserInfo().split(":")[1];

            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?ssl=true&sslmode=require&sslfactory=org.postgresql.ssl.NonValidatingFactory";

//            String dbUrl = "jdbc:postgresql://ec2-54-217-243-19.eu-west-1.compute.amazonaws.com:5432/d9j82vpkevcs4r?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory&sslmode=require";

            Class.forName("org.postgresql.Driver");



            return DriverManager.getConnection(dbUrl, username, password);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

}
