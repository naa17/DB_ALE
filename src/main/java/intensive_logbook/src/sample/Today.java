package intensive_logbook.src.sample;

import javafx.beans.property.SimpleStringProperty;

public class Today {

    public SimpleStringProperty Gluc;
    public SimpleStringProperty CHO_grams;
    public SimpleStringProperty CHO_bolus;
    public SimpleStringProperty hi_bolus;
    public SimpleStringProperty basal_rate;
    public SimpleStringProperty ketones;
    public SimpleStringProperty Time;


    public Today(String Gluc, String CHO_grams, String CHO_bolus, String hi_bolus, String basal_rate, String ketones, String Time)
    {
        this.Gluc = new SimpleStringProperty(Gluc);
        this.CHO_grams = new SimpleStringProperty(CHO_grams);
        this.CHO_bolus = new SimpleStringProperty(CHO_bolus);
        this.hi_bolus = new SimpleStringProperty(hi_bolus);
        this.basal_rate = new SimpleStringProperty(basal_rate);
        this.ketones = new SimpleStringProperty(ketones);
        this.Time = new SimpleStringProperty(Time);
    }

    public String getGluc()
    {
        return Gluc.get();
    }

    public void setGluc(String Gluc)
    {
        this.Gluc.set(Gluc);
    }

    public String  getCHO_grams()
    {
        return CHO_grams.get();
    }

    public void setCHO_grams(String CHO_grams)    { this.CHO_grams.set(CHO_grams); }

    public String getCHO_bolus(){ return CHO_bolus.get();}

    public void setCHO_bolus(String CHO_bolus){this.CHO_bolus.set(CHO_bolus);}

    public String getHi_bolus(){return hi_bolus.get();}

    public void setHi_bolus(String hi_bolus){this.hi_bolus.set(hi_bolus);}

    public String getBasal_rate(){return basal_rate.get();}

    public void setBasal_rate(String basal_rate){this.basal_rate.set(basal_rate);}

    public String getKetones(){return ketones.get();}

    public void setKetones(String ketones){this.ketones.set(ketones);}

    public String getTime() { return Time.get();}

    public void setTime(String time){this.Time.set(time);}


}
