package logbook_intensive;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import login.Controller_mp_v1;
import registration.Registration_Controller;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller_ins implements Initializable {

    @FXML
    public TableView<Today_ins> table;
    public TableColumn<Today_ins, String> Gluc;
    public TableColumn<Today_ins, String> CHO_grams;
    public TableColumn<Today_ins, String> CHO_bolus;
    public TableColumn<Today_ins, String> hi_bolus;
    public TableColumn<Today_ins, String> basal_rate;
    public TableColumn<Today_ins, String> ketones;
    public TextField Gluctxt;
    public TextField CHOgtxt;
    public TextField CHObtxt;
    public TextField hitxt;
    public TextField basaltxt;
    public TextField ketonestxt;
    public Button add;
    public Button load;
    public Button plot;
    public Button profile;
    public LineChart lineChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Gluc.setCellValueFactory(new PropertyValueFactory<Today_ins,String>("Gluc"));
        CHO_grams.setCellValueFactory(new PropertyValueFactory<Today_ins,String>("CHO_grams"));
        CHO_bolus.setCellValueFactory(new PropertyValueFactory<Today_ins,String>("CHO_bolus"));
        hi_bolus.setCellValueFactory(new PropertyValueFactory<Today_ins,String>("hi_bolus"));
        basal_rate.setCellValueFactory(new PropertyValueFactory<Today_ins,String>("basal_rate"));
        ketones.setCellValueFactory(new PropertyValueFactory<Today_ins,String>("Ketones"));
         //inputTable.setItems(data);
        table.setEditable(true);
        Gluc.setCellFactory(TextFieldTableCell.forTableColumn());
        CHO_grams.setCellFactory(TextFieldTableCell.forTableColumn());
        CHO_bolus.setCellFactory(TextFieldTableCell.forTableColumn());
        hi_bolus.setCellFactory(TextFieldTableCell.forTableColumn());
        basal_rate.setCellFactory(TextFieldTableCell.forTableColumn());
        ketones.setCellFactory(TextFieldTableCell.forTableColumn());
    }



    // load values from database into table - from the same day
    public void loadAdd(ActionEvent actionEvent)
    {
        System.out.println("load");
        // load previous values from same from databse
        buildData();
    }

    public ArrayList<String> findTable(String login_email) {

        Connection conn = null;
        Statement stmt = null;
        System.out.println("Table is here");

        try {

            conn = DB_ALE.ConnectionFactory.getConnection();
            //stmt = conn.createStatement();

            System.out.println("YOU in try catch");
            stmt = conn.createStatement();
            String sql = "Select name FROM patientsfulldetails Where email like '" + login_email + "'";
            System.out.println("RS Sucksss");
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<String> names = new ArrayList<String>();
            System.out.println("uhm");
            while (rs.next()) {
                System.out.println(rs.getString("name"));
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


    public void buildData() {
        //DB_ALE.ConnectionFactory objDbClass = new DB_ALE.ConnectionFactory();
        Connection con = DB_ALE.ConnectionFactory.getConnection();
        ObservableList<Today_ins> data1 =table.getItems();
        try {
            String login_email = Controller_mp_v1.email1;
            if (isNullOrEmpty((login_email))){
                login_email = Registration_Controller.emailReg;
            }
            ArrayList<String> login_names = findTable(login_email);
            String name = login_names.get(1);
            String SQL = "Select * from " +name +" where date like '"+getDate()+"'";
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
                Today_ins today = new Today_ins(gluc, CHO_Grams, CHO_bolus, hi_bolus, basal_rate, ketones, time, getDate());
                data1.add(today);
            }
            // add list of today class to table
            table.setItems(data1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

    }

    // Function from https://dzone.com/articles/getting-current-date-time-in-java
    public static String getDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate today = LocalDate.now();

        return (formatter.format(today));
    }
    // end of reference
    // add new values to the table
    public void btnAdd(ActionEvent actionEvent) {
        System.out.println("YES");

        // If 1st row is empty new day - prebreakfast class
        // If 2nd row - postbreakfast
        // etc

        ObservableList<Today_ins> items = table.getItems();
        // add a different time of day value depending on cells filled in table

        String time="";
        if(items.isEmpty())
        {
            // pre breakfast
            time="6 AM";
        }
        else if(items.size()==1)
        {
            time="7 AM";
        }
        else if(items.size()==2)
        {
            time="8 AM";
        }
        else if(items.size()==3)
        {
            time="9 AM";
        }
        else if(items.size()==4)
        {
            time="10 AM";
        }
        else if(items.size()==5)
        {
            time="11 AM";
        }
        else if(items.size()==6)
        {
            time="12 PM";
        }
        else if(items.size()==7)
        {
            time="1 PM";
        }
        else if(items.size()==8)
        {
            time="2 PM";
        }
        else if(items.size()==9)
        {
            time="3 PM";
        }
        else if(items.size()==10)
        {
            time="4 PM";
        }
        else if(items.size()==11)
        {
            time="5 PM";
        }
        else if(items.size()==12)
        {
            time="6 PM";
        }
        else if(items.size()==13)
        {
            time="7 PM";
        }
        else if(items.size()==14)
        {
            time="8 PM";
        }
        else if(items.size()==15)
        {
            time="9 PM";
        }
        else if(items.size()==16)
        {
            time="10 PM";
        }
        else if(items.size()==17)
        {
            time="11 PM";
        }
        Today_ins newToday= new Today_ins(Gluctxt.getText(), CHOgtxt.getText(), CHObtxt.getText(), hitxt.getText(), basaltxt.getText(), ketonestxt.getText(), time, getDate());
        table.getItems().add(newToday);

        String login_email = Controller_mp_v1.email1;
        if (isNullOrEmpty((login_email))){
            login_email = Registration_Controller.emailReg;
        }
        ArrayList<String> login_names = findTable(login_email);
        IntBackend.insertToDB(newToday, login_names, login_email);
    }
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }

// editing table values

    public void EditValue(TableColumn.CellEditEvent<Today_ins, String> TodayStringCellEditEvent) {

        Today_ins Today = table.getSelectionModel().getSelectedItem();
        Today.setGluc(TodayStringCellEditEvent.getNewValue());
        Today.setCHO_grams(TodayStringCellEditEvent.getNewValue());
        Today.setCHO_bolus(TodayStringCellEditEvent.getNewValue());
        Today.setHi_bolus(TodayStringCellEditEvent.getNewValue());
        Today.setBasal_rate(TodayStringCellEditEvent.getNewValue());
        Today.setKetones(TodayStringCellEditEvent.getNewValue());
    }


    // plot
    public void plotToday(ActionEvent actionEvent)
    {
        System.out.println("plot");

        XYChart.Series<String, Number> series1= new XYChart.Series<>();
        XYChart.Series<String, Number> series= new XYChart.Series<>();

        series1.setName("Recommended values");
        series.setName("Today values");

        ObservableList<Today_ins> items = table.getItems();
        series1.getData().add(new XYChart.Data<>("6 am",70));
        series1.getData().add(new XYChart.Data<>("7 am",75));
        series1.getData().add(new XYChart.Data<>("8 am",80));
        series1.getData().add(new XYChart.Data<>("9 am",115));
        series1.getData().add(new XYChart.Data<>("10 am",80));
        series1.getData().add(new XYChart.Data<>("11 am",90));
        series1.getData().add(new XYChart.Data<>("12 pm",75));
        series1.getData().add(new XYChart.Data<>("1 pm",108));
        series1.getData().add(new XYChart.Data<>("2 pm",90));
        series1.getData().add(new XYChart.Data<>("3 pm",95));
        series1.getData().add(new XYChart.Data<>("4 pm",100));
        series1.getData().add(new XYChart.Data<>("5 pm",97));
        series1.getData().add(new XYChart.Data<>("6 pm",90));
        series1.getData().add(new XYChart.Data<>("7 pm",110));
        series1.getData().add(new XYChart.Data<>("8 pm",100));
        series1.getData().add(new XYChart.Data<>("9 pm",110));
        series1.getData().add(new XYChart.Data<>("10 pm",90));
        series1.getData().add(new XYChart.Data<>("11 pm",80));


        ArrayList<String> hours = new ArrayList<String>();
        hours.add("6 am");
        hours.add("7 am");
        hours.add("8 am");
        hours.add("9 am");
        hours.add("10 am");
        hours.add("11 am");
        hours.add("12 pm");
        hours.add("1 pm");
        hours.add("2 pm");
        hours.add("3 pm");
        hours.add("4 pm");
        hours.add("5 pm");
        hours.add("6 pm");
        hours.add("7 pm");
        hours.add("8 pm");
        hours.add("9 pm");
        hours.add("10 pm");
        hours.add("11 pm");


        for (int i=0;i<items.size();i++)
        {
            double gluc= Double.valueOf(items.get(i).getGluc());
            series.getData().add(new XYChart.Data<>(hours.get(i),gluc));
        }

        lineChart.getData().add(series);
        lineChart.getData().add(series1);


    }

    public void accessProfile(ActionEvent actionEvent) throws Exception
    {
        System.out.println("YOU going to profile");
        URL urlp = new File("src\\main\\java\\profile.fxml").toURI().toURL();
        Parent root1 = FXMLLoader.load((urlp));
        System.out.println("YEAH YOU HERE");
        Stage window1 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window1.setTitle("Profile Page");
        window1.setScene(new Scene(root1, 800, 800));
        window1.show();
    }

    public void goCalendar(ActionEvent actionEvent) throws Exception
    {
        System.out.println("YOU going to past entries");
        URL urlp = new File("src\\main\\java\\calendar_i.fxml").toURI().toURL();
        Parent root1 = FXMLLoader.load((urlp));
        System.out.println("YEAH YOU HERE");
        Stage window1 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window1.setTitle("Past entries");
        window1.setScene(new Scene(root1, 1000, 1000));
        window1.show();
    }


}

