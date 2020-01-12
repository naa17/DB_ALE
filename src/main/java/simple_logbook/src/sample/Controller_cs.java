package simple_logbook.src.sample;

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

import java.sql.SQLException;
import java.time.LocalDate;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller_cs implements  Initializable{


        @FXML
        public TableView<Today_simple> table;
        public TableColumn<Today_simple, String> Gluc;
        public TableColumn<Today_simple, String> Carb;
        public Button plot_today;
        public Button logBtn;
        public DatePicker calendar;
        public LineChart lineChart;

        @Override
        public void initialize(URL location, ResourceBundle resources) {

            Gluc.setCellValueFactory(new PropertyValueFactory<Today_simple,String>("Gluc"));
            Carb.setCellValueFactory(new PropertyValueFactory<Today_simple,String>("Carb"));

        }

        public void pickDate(ActionEvent actionEvent) {
            LocalDate date = calendar.getValue();
            String chosen_day = String.valueOf(date);
            System.out.println(chosen_day);
            buildData(chosen_day);
        }


        public void buildData(String day) {

            Connection con = ConnectionFactory_s.getConnection();
            ObservableList<Today_simple> data1 =table.getItems();
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
                    cm.carbs.set(rs.getInt("carbs"));
                    cm.time.set(rs.getString("timeofday"));
                    String gluc = String.valueOf(cm.getGluc());
                    String carbs = String.valueOf(cm.getCarbs());
                    String time = cm.getTime();
                    // class today instantiated with strings from database
                    Today_simple today = new Today_simple(gluc, carbs, time, day);
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

            ObservableList<Today_simple> items = table.getItems();
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


