package logbook_simple;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

// https://stackoverflow.com/questions/18497699/populate-a-tableview-using-database-in-javafx

public class Usermaster {

    public SimpleStringProperty time = new SimpleStringProperty();
    public SimpleDoubleProperty gluc = new SimpleDoubleProperty();
    public SimpleDoubleProperty carbs = new SimpleDoubleProperty();


    public String getTime() {
        return time.get();
    }

    public double getGluc() {
        return gluc.get();
    }

    public double getCarbs() {
        return carbs.get();
    }

}