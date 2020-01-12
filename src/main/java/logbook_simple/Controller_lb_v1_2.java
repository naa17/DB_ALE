//Simple logbook page controller
//Specifies what actions to take when the buttons are pushed

package logbook_simple;

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
import DB_ALE.Today;
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

public class Controller_lb_v1_2 implements Initializable {

    @FXML
    public TableView<Today> table;
    public TableColumn<Today, String> Gluc;
    public TableColumn<Today, String> Carb;

    public TextField Gluctxt;
    public TextField Carbtxt;
    public Button btn;
    public Button load;
    public Button plot_today;
    public Button profile;
    public LineChart lineChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Gluc.setCellValueFactory(new PropertyValueFactory<Today, String>("Gluc"));
        Carb.setCellValueFactory(new PropertyValueFactory<Today, String>("Carb"));

        //inputTable.setItems(data);
        table.setEditable(true);
        Gluc.setCellFactory(TextFieldTableCell.forTableColumn());
        Carb.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    // load values from database into table - from the same day
    public void loadAdd(ActionEvent actionEvent) {
        // load previous values from same day from database
        buildData();
    }

    public ArrayList<String> findTable(String login_email) {

        Connection conn = null;
        Statement stmt = null;

        try {
//            Establishing the database connection
            conn = DB_ALE.ConnectionFactory.getConnection();
//            Creating the statement
            stmt = conn.createStatement();
//          SQL string
            String sql = "Select name FROM patientsfulldetails Where email like '" + login_email + "'";
//            Executing the SQL string
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<String> names = new ArrayList<String>();
//            Looping through the result set - it returns the name from the patient
            while (rs.next()) {
                names.add(rs.getString("name")); //original name - entered at registration
                names.add(rs.getString("name").replaceAll("\\s+", "")); //name wihtout spaces
                return names;
            }
            rs.close();
            conn.close();
//            closing the connection and the result set.
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Displaying current date's values into UI
    public void buildData() {
        Connection con = DB_ALE.ConnectionFactory.getConnection();
        ObservableList<Today> data1 =table.getItems();
        try {
//            The row of the user in the database table containing all patients and their details
//            is retrieved by querying the table from their email.
//            Their email is retrieved from the last place it was saved which can be one of two options.
//            If the user is registering from the first time, the place is the registration controller.
//            In this case Controller_mp_v1.email1 is null.
//            If they are logging in, the place is the login controller.
            String login_email = Controller_mp_v1.email1;
            if (isNullOrEmpty((login_email))){
                login_email = Registration_Controller.emailReg;
            }

            ArrayList<String> login_names = findTable(login_email);
            String login_name = login_names.get(1);
            String today_Date = getDate();
            String SQL = "Select * from " + login_name +" WHERE date like '"+ today_Date +"'";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
//            Looping through the result set - it returns glucos, carbs and time data from today
            while (rs.next()) {
                Usermaster cm = new Usermaster();
                cm.gluc.set(rs.getInt("glucose"));
                cm.carbs.set(rs.getInt("carbs"));
                cm.time.set(rs.getString("timesofday"));

                Today today = new Today(String.valueOf(cm.getGluc()), String.valueOf(cm.getCarbs()),cm.getTime(), getDate());

                // class today instantiated with strings from database
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

    // add new values to the table, to their corresponding time
    public void btnAdd(ActionEvent actionEvent) throws Exception {

        // If 1st row is empty new day - prebreakfast class
        // If 2nd row - postbreakfast
        // etc

        ObservableList<Today> items = table.getItems();

        // add a different time of day value depending on last filled cell
        String time="";
        if(items.isEmpty())
        {
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

//        Instantiating the today object - contains user inputted values
        Today newToday = new Today(Gluctxt.getText(), Carbtxt.getText(), time, getDate());
        table.getItems().add(newToday);
        String login_email = Controller_mp_v1.email1;
        if (isNullOrEmpty((login_email))){
            login_email = Registration_Controller.emailReg;
        }

        ArrayList<String> login_names = findTable(login_email);
//        Insertig user inputted values into the logbook table in the database
        logbookBackend.insertToDB(newToday, login_names, login_email);
    }

//    Checking if a string is null or empty
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }

// editing table values
    public void EditValue(TableColumn.CellEditEvent<Today, String> TodayStringCellEditEvent) {

        Today Today = table.getSelectionModel().getSelectedItem();
        Today.setGluc(TodayStringCellEditEvent.getNewValue());
        Today.setCarb(TodayStringCellEditEvent.getNewValue());
      }

    


// plotting today's and recommended values
    public void plotToday(ActionEvent actionEvent)
    {

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

    //Go to the profile page
    public void accessProfile(ActionEvent actionEvent) throws Exception
    {
        URL urlp = new File("src\\main\\java\\profile.fxml").toURI().toURL();
        Parent root1 = FXMLLoader.load((urlp));
        Stage window1 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window1.setTitle("Profile Page");
        window1.setScene(new Scene(root1, 800, 800));
        window1.show();
    }

    //go to the calendar page
    public void goCalendar(ActionEvent actionEvent) throws Exception
    {
        URL urlp = new File("src\\main\\java\\calendar_s.fxml").toURI().toURL();
        Parent root1 = FXMLLoader.load((urlp));
        Stage window1 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window1.setTitle("Past entries");
        window1.setScene(new Scene(root1, 800, 800));
        window1.show();
    }

}

