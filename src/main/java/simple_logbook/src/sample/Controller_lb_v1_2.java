package simple_logbook.src.sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import login_OOP.src.mp_v1.Controller_mp_v1;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller_lb_v1_2 implements Initializable {

    @FXML
    public TableView<Today_v1_2> table;
    public TableColumn<Today_v1_2, String> Gluc;
    public TableColumn<Today_v1_2, String> Carb;
    public TextField Gluctxt;
    public TextField Carbtxt;
    public Button btn;
    public Button load;
    public Button plot_today;
    public Button profile;
    public LineChart lineChart;

    // public ObservableList<Today_v1_2> data1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Gluc.setCellValueFactory(new PropertyValueFactory<Today_v1_2, String>("Gluc"));
        Carb.setCellValueFactory(new PropertyValueFactory<Today_v1_2, String>("Carb"));
        //inputTable.setItems(data);
        table.setEditable(true);
        Gluc.setCellFactory(TextFieldTableCell.forTableColumn());
        Carb.setCellFactory(TextFieldTableCell.forTableColumn());

    }





    // load values from database into table - from the same day
    public void loadAdd(ActionEvent actionEvent) {
        System.out.println("load");
        // load previous values from same from databse
        buildData();
    }

    public String findTable(String login_email) {

        Connection conn = null;
        Statement stmt = null;
        System.out.println("Table is here");

        try {

            conn = sample.ConnectionFactory.getConnection();
            //stmt = conn.createStatement();

            System.out.println("YOU in try catch");
            stmt = conn.createStatement();
            String sql = "Select name FROM patientsfulldetails Where email like '" + login_email + "'";
            System.out.println("RS Sucksss");
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("uhm");
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                String name = rs.getString("name").replaceAll("\\s+", "");
                System.out.println(name);
                return name;


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
        Connection con = sample.ConnectionFactory.getConnection();
        ObservableList<Today_v1_2> data1 =table.getItems();
        try {
            String login_email = Controller_mp_v1.email1;
            System.out.println(login_email);
            String login_name = findTable(login_email);
            String SQL = "Select * from " + login_name  ;

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                sample.Usermaster cm = new sample.Usermaster();
                cm.gluc.set(rs.getInt("glucose"));
                cm.carbs.set(rs.getInt("carbs"));
                cm.time.set(rs.getString("timesofday"));
                String gluc = String.valueOf(cm.getGluc());
                String carbs = String.valueOf(cm.getCarbs());
                String time = cm.getTime();
                // class today instantiated with strings from database
                Today_v1_2 today = new Today_v1_2(gluc, carbs, time);
                data1.add(today);
            }
            // add list of today class to table
            table.setItems(data1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

    }

// add new values to the table
public void btnAdd(ActionEvent actionEvent) {
    System.out.println("YES");

    // If 1st row is empty new day - prebreakfast class
    // If 2nd row - postbreakfast
    // etc

    ObservableList<Today_v1_2> items = table.getItems();

    // add a different time of day value depending on cells filled in table

    String time="";
    if(items.isEmpty())
    {
        // pre breakfast
        time="preBreakfast";
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

    Today_v1_2 newToday = new Today_v1_2(Gluctxt.getText(), Carbtxt.getText(), time);
    table.getItems().add(newToday);

    String login_email = Controller_mp_v1.email1;
    String login_name = findTable(login_email);
    logbookBackend.insertToDB(newToday, login_name);



}                 

// editing table values
    public void EditValue(TableColumn.CellEditEvent<Today_v1_2, String> TodayStringCellEditEvent) {

        Today_v1_2 Today = table.getSelectionModel().getSelectedItem();
        Today.setGluc(TodayStringCellEditEvent.getNewValue());
        Today.setCarb(TodayStringCellEditEvent.getNewValue());
      }

    


// plot
    public void plotToday(ActionEvent actionEvent)
    {
        System.out.println("plot");

        XYChart.Series<String, Number> series1= new XYChart.Series<>();
        XYChart.Series<String, Number> series= new XYChart.Series<>();

        series1.setName("Recommended values");
        series.setName("Today values");

        ObservableList<Today_v1_2> items = table.getItems();
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

    public void accessProfile(ActionEvent actionEvent)
    {
        System.out.println("Access profile");
    }


}

