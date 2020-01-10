package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

// https://stackoverflow.com/questions/18497699/populate-a-tableview-using-database-in-javafx

public class Usermaster{

    public SimpleStringProperty time = new SimpleStringProperty();
    public SimpleDoubleProperty gluc = new SimpleDoubleProperty();
    public SimpleDoubleProperty carbs = new SimpleDoubleProperty();

/*
    public SimpleIntegerProperty prebgluc = new SimpleIntegerProperty();
    public SimpleIntegerProperty prebcarb = new SimpleIntegerProperty();
    public SimpleIntegerProperty postbgluc = new SimpleIntegerProperty();
    public SimpleIntegerProperty postbcarb = new SimpleIntegerProperty();
    public SimpleIntegerProperty prelgluc = new SimpleIntegerProperty();
    public SimpleIntegerProperty prelcarb = new SimpleIntegerProperty();
    public SimpleIntegerProperty postlgluc = new SimpleIntegerProperty();
    public SimpleIntegerProperty postlcarb = new SimpleIntegerProperty();
    public SimpleIntegerProperty predgluc = new SimpleIntegerProperty();
    public SimpleIntegerProperty predcarb = new SimpleIntegerProperty();
    public SimpleIntegerProperty postdgluc = new SimpleIntegerProperty();
    public SimpleIntegerProperty postdcarb = new SimpleIntegerProperty();
    public SimpleIntegerProperty bedgluc = new SimpleIntegerProperty();
    public SimpleIntegerProperty bedcarb = new SimpleIntegerProperty();


    public int getPreBgluc() {
        return prebgluc.get();
    }
    public int getPreBcarb() {
        return prebcarb.get();
    }
    public int getPostBgluc() {
        return postbgluc.get();
    }
    public int getPostBcarb() {
        return postbcarb.get();
    }
    public int getPreLgluc() {
        return prelgluc.get();
    }
    public int getPreLcarb()
    {
        return prelcarb.get();
    }
    public int getPostLgluc()
    {
        return postlgluc.get();
    }
    public int getPostLcarb()
    {
        return postlcarb.get();
    }
    public int getPreDgluc()
    {
        return predgluc.get();
    }
    public int getPreDcarb()
    {
        return predcarb.get();
    }
    public int getPostDgluc()
    {
        return postdgluc.get();
    }
    public int getPostDcarb()
    {
        return postdcarb.get();
    }
    public int getBedGluc()
    {
        return bedgluc.get();
    }
    public int getBedCarb()
    {
        return bedcarb.get();
    }

 */
    public String getTime(){ return time.get();}
    public double getGluc(){ return gluc.get();}
    public double getCarbs(){ return carbs.get();}

}