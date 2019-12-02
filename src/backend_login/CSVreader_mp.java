package backend_login;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVreader_mp {
    public static List<String> readCSV (String fileName) {
        List <String> email_pass = new ArrayList<String>();
        File csvFile = new File(fileName);
        String line;

        try (BufferedReader csvReader = new BufferedReader(new FileReader(csvFile))) {
            while ((line = csvReader.readLine()) != null) {
                String[] values = line.split(",");
                email_pass.add(0, values[0]);
                email_pass.add(1, values[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();    // prints standard error
        }

        return email_pass;
    }
}
