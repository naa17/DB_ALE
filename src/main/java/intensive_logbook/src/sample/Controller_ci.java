package intensive_logbook.src.sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import login_OOP.src.mp_v1.Controller_mp_v1;
import registrationFX.src.sample.Registration_Controller;
import simple_logbook.src.sample.ConnectionFactory_s;

import java.sql.SQLException;
import java.time.LocalDate;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller_ci implements  Initializable{


    @FXML
    public TableView<Today> table;
    public TableColumn<Today, String> Gluc;
    public TableColumn<Today, String> CHO_grams;
    public TableColumn<Today, String> CHO_bolus;
    public TableColumn<Today, String> hi_bolus;
    public TableColumn<Today, String> basal_rate;
    public TableColumn<Today, String> ketones;
    public Button plot_today;
    public Button logBtn;
    public DatePicker calendar;
    public LineChart lineChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Gluc.setCellValueFactory(new PropertyValueFactory<Today,String>("Gluc"));
        CHO_grams.setCellValueFactory(new PropertyValueFactory<Today,String>("CHO_grams"));
        CHO_bolus.setCellValueFactory(new PropertyValueFactory<Today,String>("CHO_bolus"));
        hi_bolus.setCellValueFactory(new PropertyValueFactory<Today,String>("hi_bolus"));
        basal_rate.setCellValueFactory(new PropertyValueFactory<Today,String>("basal_rate"));
        ketones.setCellValueFactory(new PropertyValueFactory<Today,String>("Ketones"));
    }

    public void pickDate(ActionEvent actionEvent) {
        LocalDate date = calendar.getValue();
        String chosen_day = String.valueOf(date);
        System.out.println(chosen_day);
        buildData(chosen_day);
    }


    public void buildData(String day) {

        Connection con = ConnectionFactory.getConnection();
        ObservableList<Today> data1 =table.getItems();
        try {
            String login_email = Controller_mp_v1.email1;
            if (isNullOrEmpty((login_email))){
                login_email = Registration_Controller.emailReg;
            }
            ArrayList<String> login_names = findTable(login_email);
            String SQL = "Select * from "+login_names.get(1)+" where date like '"+day+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                Usermaster cm = new Usermaster();
                cm.gluc.set(rs.getInt("glucose"));
                cm.CHO_grams.set(rs.getInt("cho_grams"));
                cm.CHO_bolus.set(rs.getInt("cho_bolus"));
                cm.hi_bolus.set(rs.getInt("hi_bg_bolus"));
                cm.basal_rate.set(rs.getInt("basalrate"));
                cm.ketones.set(rs.getInt("ketones_exercise"));
                cm.time.set(rs.getString("hours"));
                String gluc = String.valueOf(cm.getGluc());
                String CHO_Grams = String.valueOf(cm.getCHO_grams());
                String CHO_bolus = String.valueOf(cm.getCHO_bolus());
                String hi_bolus = String.valueOf(cm.getHi_bolus());
                String basal_rate = String.valueOf(cm.getBasal_rate());
                String ketones = String.valueOf(cm.getKetones());
                String time = cm.getTime();
                // class today instantiated with strings from database
                Today today = new Today(gluc, CHO_Grams, CHO_bolus, hi_bolus, basal_rate, ketones, time, day);
                data1.add(today);
            }
            // add list of today class to table
            table.setItems(data1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

    }

    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }

    public ArrayList<String> findTable(String login_email) {

        Connection conn = null;
        Statement stmt = null;
        System.out.println("Table is here");

        try {

            conn = ConnectionFactory_s.getConnection();
            //stmt = conn.createStatement();

            System.out.println("YOU in try catch");
            stmt = conn.createStatement();
            String sql = "Select name FROM patientsfulldetails Where email like '" + login_email + "'";
            System.out.println("RS Sucksss");
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("uhm");
            ArrayList<String> names = new ArrayList<String>();

            System.out.println("~~~~BURNA BOYYYY~~~~~");
            System.out.println(sql);
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                //String name = rs.getString("name").replaceAll("\\s+", "");
                names.add(rs.getString("name"));
                names.add(rs.getString("name").replaceAll("\\s+", ""));
                System.out.println(names);
                return names;
            }
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // add new values to the table


    // plot
    public void plotToday(ActionEvent actionEvent)
    {
        System.out.println("plot");

        XYChart.Series<String, Number> series1= new XYChart.Series<>();
        XYChart.Series<String, Number> series= new XYChart.Series<>();

        series1.setName("Recommended values");
        series.setName("Today values");

        ObservableList<Today> items = table.getItems();
        series1.getData().add(new XYChart.Data<>("8 am",90));
        series1.getData().add(new XYChart.Data<>("9 am",180));
        series1.getData().add(new XYChart.Data<>("12 am",100));
        series1.getData().add(new XYChart.Data<>("1 pm",180));
        series1.getData().add(new XYChart.Data<>("6 pm",100));
        series1.getData().add(new XYChart.Data<>("7 pm",180));
        series1.getData().add(new XYChart.Data<>("9 pm",120));

        ArrayList<String> hours = new ArrayList<String>();
        hours.add("8 am");
        hours.add("9 am");
        hours.add("12 am");
        hours.add("1 pm");
        hours.add("6 pm");
        hours.add("7 pm");
        hours.add("9 pm");


        for (int i=0;i<items.size();i++)
        {
            double gluc= Double.valueOf(items.get(i).getGluc());
            series.getData().add(new XYChart.Data<>(hours.get(i),gluc));
        }

        lineChart.getData().add(series);
        lineChart.getData().add(series1);


    }

    public void logbook(ActionEvent actionEvent)
    {
        System.out.println("going to logbook");
    }


}


