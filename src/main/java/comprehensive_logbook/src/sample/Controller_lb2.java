package comprehensive_logbook.src.sample;

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
import login_OOP.src.mp_v1.Controller_mp_v1;
import registrationFX.src.sample.Registration_Controller;

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


public class Controller_lb2 implements Initializable {

    @FXML
    public TableView<Today_comp> table;
    public TableColumn<Today_comp, String> Gluc;
    public TableColumn<Today_comp, String> Carb;
    public TableColumn<Today_comp, String> Ins;
    public TextField Gluctxt;
    public TextField Carbtxt;
    public TextField Instxt;
    public Button btn;
    public Button load;
    public Button plot_today;
    public Button profile;
    public LineChart lineChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Gluc.setCellValueFactory(new PropertyValueFactory<Today_comp,String>("Gluc"));
        Carb.setCellValueFactory(new PropertyValueFactory<Today_comp,String>("Carb"));
        Ins.setCellValueFactory(new PropertyValueFactory<Today_comp, String>("Ins"));
        table.setEditable(true);


        Gluc.setCellFactory(TextFieldTableCell.forTableColumn());
        Carb.setCellFactory(TextFieldTableCell.forTableColumn());
        Ins.setCellFactory(TextFieldTableCell.forTableColumn());

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

            conn = comprehensive_logbook.src.sample.ConnectionFactory.getConnection();
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
        //ConnectionFactory objDbClass = new ConnectionFactory();
        Connection con = comprehensive_logbook.src.sample.ConnectionFactory.getConnection();
        ObservableList<Today_comp> data1 =table.getItems();
        try {
            String login_email = Controller_mp_v1.email1;
            if (isNullOrEmpty((login_email))){
                login_email = Registration_Controller.emailReg;
            }
            ArrayList<String> login_names = findTable(login_email);
            String name = login_names.get(1);
            String date = getDate();
            String SQL = "Select * from " + name + " where date like '"+date+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                Usermaster cm = new Usermaster();
                cm.gluc.set(rs.getInt("glucose"));
                cm.carbs.set(rs.getInt("carbs"));
                cm.time.set(rs.getString("timesofday"));
                cm.ins.set(rs.getInt("insulin"));
                String gluc = String.valueOf(cm.getGluc());
                String carbs = String.valueOf(cm.getCarbs());
                String time = cm.getTime();
                String ins = String.valueOf(cm.getIns());
                // class today instantiated with strings from database
                Today_comp today = new Today_comp(gluc, carbs, ins, time, getDate());
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
    public String getDate(){
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

        ObservableList<Today_comp> items = table.getItems();

        // add a different time of day value depending on cells filled in table
        String time="";

        if(items.isEmpty())
        {
           time="prebreakfast";
        }

        else if(items.size()==1)
        {
            time="postBreakfast";
        }

        else if(items.size()==2)
        {
            time="preLunch";
        }

        else if(items.size()==3)
        {
            time="postLunch";
        }

        else if(items.size()==4)
        {
            time="preDinner";
        }

        else if(items.size()==5)
        {
            time="postDinner";
        }

        else if(items.size()==6)
        {
            time="bedtime";
        }

        Today_comp newToday= new Today_comp(Gluctxt.getText(), Carbtxt.getText(), Instxt.getText(), time, getDate());
        table.getItems().add(newToday);

        String login_email = Controller_mp_v1.email1;
        if (isNullOrEmpty((login_email))){
            login_email = Registration_Controller.emailReg;
        }
        ArrayList<String> login_names = findTable(login_email);
        System.out.println("~~~~~~~~~~~~~~~~");
        System.out.println("entering insert to db");
        compBackend.insertToDB(newToday, login_names, login_email);
    }
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }



// editing table values

    public void EditValue(TableColumn.CellEditEvent<Today_comp, String> TodayStringCellEditEvent) {

        Today_comp Today = table.getSelectionModel().getSelectedItem();
        Today.setGluc(TodayStringCellEditEvent.getNewValue());
        Today.setCarb(TodayStringCellEditEvent.getNewValue());
        Today.setIns(TodayStringCellEditEvent.getNewValue());
    }


    // plot
    public void plotToday(ActionEvent actionEvent)
    {
        System.out.println("plot");

        XYChart.Series<String, Number> series1= new XYChart.Series<>();
        XYChart.Series<String, Number> series= new XYChart.Series<>();

        series1.setName("Recommended values");
        series.setName("Today values");

        ObservableList<Today_comp> items = table.getItems();
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
        URL urlp = new File("src\\main\\java\\calendar_c.fxml").toURI().toURL();
        Parent root1 = FXMLLoader.load((urlp));
        System.out.println("YEAH YOU HERE");
        Stage window1 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window1.setTitle("Past entries");
        window1.setScene(new Scene(root1, 800, 800));
        window1.show();
    }

}

