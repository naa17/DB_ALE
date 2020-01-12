//Calendar page for Simple logbook

package logbook_simple;

import DB_ALE.ConnectionFactory;
import DB_ALE.Patient;
import DB_ALE.Today;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logbook_comprehensive.PatientDAO;
import login.Controller_mp_v1;
import registration.RegistrationBackend;
import registration.Registration_Controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller_calen implements  Initializable {
    @FXML
    public TableView<Today> table;
    public TableColumn<Today, String> Gluc;
    public TableColumn<Today, String> Carb;
    public Button plot_today;
    public Button logBtn;
    public DatePicker calendar;
    public LineChart lineChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Gluc.setCellValueFactory(new PropertyValueFactory<Today, String>("Gluc"));
        Carb.setCellValueFactory(new PropertyValueFactory<Today, String>("Carb"));

    }

    public void pickDate(ActionEvent actionEvent) {
        LocalDate date = calendar.getValue();
        String chosen_day = String.valueOf(date);
        System.out.println(chosen_day);
        buildData(chosen_day);
    }

    //Loading current date's values in the UI
    public void buildData(String day) {

        Connection con = ConnectionFactory.getConnection();
        ObservableList<Today> data1 = table.getItems();
        try {
            //email of the patient, taken from last place where it was saved
            //if the user is new, and comes from registration, email is taken from the registration controller
            //else it is taken from the login controller
            String login_email = Controller_mp_v1.email1;//from login
            if (isNullOrEmpty((login_email))) {
                login_email = Registration_Controller.emailReg;//from registration
            }

            //getting the name of the patient
            ArrayList<String> login_names = findTable(login_email);

            String SQL = "Select * from " + login_names.get(1) + " where date like '" + day + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                Usermaster cm = new Usermaster();
                cm.gluc.set(rs.getInt("glucose"));
                cm.carbs.set(rs.getInt("carbs"));
                cm.time.set(rs.getString("timesofday"));
                String gluc = String.valueOf(cm.getGluc());
                String carbs = String.valueOf(cm.getCarbs());
                String time = cm.getTime();
                // class today instantiated with strings from database
                Today today = new Today(gluc, carbs, time, day);
                data1.add(today);
            }
            // add list of today class to table
            table.setItems(data1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

    }

    //checking if a string is Null
    public static boolean isNullOrEmpty(String str) {
        if (str != null && !str.isEmpty())
            return false;
        return true;
    }

    //Returns name of registered patient given their email
    public ArrayList<String> findTable(String login_email) {

        Connection conn = null;
        Statement stmt = null;
        System.out.println("Table is here");

        try {

            conn = ConnectionFactory.getConnection();
            stmt = conn.createStatement();
            String sql = "Select name FROM patientsfulldetails Where email like '" + login_email + "'";
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<String> names = new ArrayList<String>();

            while (rs.next()) {
                System.out.println(rs.getString("name"));
                names.add(rs.getString("name"));
                names.add(rs.getString("name").replaceAll("\\s+", ""));
                return names;
            }
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // plot the value and recommended ones
    public void plotToday(ActionEvent actionEvent) {
        System.out.println("plot");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        series1.setName("Recommended values");
        series.setName("Today values");

        ObservableList<Today> items = table.getItems();
        series1.getData().add(new XYChart.Data<>("8 am", 90));
        series1.getData().add(new XYChart.Data<>("9 am", 180));
        series1.getData().add(new XYChart.Data<>("12 am", 100));
        series1.getData().add(new XYChart.Data<>("1 pm", 180));
        series1.getData().add(new XYChart.Data<>("6 pm", 100));
        series1.getData().add(new XYChart.Data<>("7 pm", 180));
        series1.getData().add(new XYChart.Data<>("9 pm", 120));

        ArrayList<String> hours = new ArrayList<String>();
        hours.add("8 am");
        hours.add("9 am");
        hours.add("12 am");
        hours.add("1 pm");
        hours.add("6 pm");
        hours.add("7 pm");
        hours.add("9 pm");

        for (int i = 0; i < items.size(); i++) {
            double gluc = Double.valueOf(items.get(i).getGluc());
            series.getData().add(new XYChart.Data<>(hours.get(i), gluc));
        }

        lineChart.getData().add(series);
        lineChart.getData().add(series1);
    }

    //Return to logbook from calendar page
    public void logbook(javafx.event.ActionEvent actionEvent) throws Exception {

        try {
            String login_email = Controller_mp_v1.email1;
            if (isNullOrEmpty((login_email))) {
                login_email = Registration_Controller.emailReg;
            }

            Patient p = PatientDAO.getDetailsForEmail(login_email);

            //determine which logbook page to go to
            if (RegistrationBackend.logbookType(p).equals("simple")) {
                URL url2 = new File("src\\main\\java\\lb_v1_2.fxml").toURI().toURL();
                Parent root2 = FXMLLoader.load(url2);
                Stage window2 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window2.setTitle("Simple Logbook Page");
                window2.setScene(new Scene(root2, 800, 600));
                window2.show();
            } else if (RegistrationBackend.logbookType(p).equals("comprehensive")) {
                System.out.println("comprehensive");
                URL url2 = new File("src\\main\\java\\lb_v2.fxml").toURI().toURL();
                Parent root3 = FXMLLoader.load(url2);
                Stage window3 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window3.setTitle("Comprehensive Logbook Page");
                window3.setScene(new Scene(root3, 800, 600));
                window3.show();
                nextPage("lb_v1_2.fxml", actionEvent, "Simple Logbook Page");
            } else if (RegistrationBackend.logbookType(p).equals("intensive")) {
                System.out.println("intensive");
                URL url2 = new File("src\\main\\java\\lb_v3.fxml").toURI().toURL();
                Parent root3 = FXMLLoader.load(url2);
                Stage window3 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window3.setTitle("Intensive Logbook Page");
                window3.setScene(new Scene(root3, 1000, 1200));
                window3.show();
                nextPage("lb_v3.fxml", actionEvent, "Intensive Logbook Page");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void nextPage(String fxml, ActionEvent event, String title) throws IOException {
        URL url2 = new File(fxml).toURI().toURL();
        Parent root2 = FXMLLoader.load(url2);
        Stage window2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window2.setTitle(title);
        window2.setScene(new Scene(root2, 800, 600));
        window2.show();
    }

}


