package logbook_intensive;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

// https://stackoverflow.com/questions/18497699/populate-a-tableview-using-database-in-javafx

public class Usermaster{

    public SimpleStringProperty time = new SimpleStringProperty();
    public SimpleDoubleProperty gluc = new SimpleDoubleProperty();
    public SimpleDoubleProperty CHO_grams  = new SimpleDoubleProperty();
    public SimpleDoubleProperty CHO_bolus  = new SimpleDoubleProperty();
    public SimpleDoubleProperty hi_bolus  = new SimpleDoubleProperty();
    public SimpleDoubleProperty basal_rate  = new SimpleDoubleProperty();
    public SimpleDoubleProperty ketones  = new SimpleDoubleProperty();

    public String getTime(){ return time.get();}
    public double getGluc(){ return gluc.get();}
    public double getCHO_grams(){return CHO_grams.get();}
    public double getCHO_bolus(){return CHO_bolus.get();}
    public double getHi_bolus(){return hi_bolus.get();}
    public double getBasal_rate(){return basal_rate.get();}
    public double getKetones(){return ketones.get();}

}