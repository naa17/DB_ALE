package comprehensive_logbook.src.sample;

import javafx.beans.property.SimpleStringProperty;

public class Today {
    public SimpleStringProperty Gluc;
    public SimpleStringProperty Carb;
    public SimpleStringProperty Time;
    public SimpleStringProperty Date;

    public Today(String Gluc, String Carb, String Ins, String Time, String Date)
    {
        this.Gluc = new SimpleStringProperty(Gluc);
        this.Carb = new SimpleStringProperty(Carb);
        this.Time = new SimpleStringProperty(Time);
        this.Date= new SimpleStringProperty(Date);
    }

    public String getGluc()
    {
        return Gluc.get();
    }

    public void setGluc(String Gluc)
    {
        this.Gluc.set(Gluc);
    }

    public String  getCarb()
    {
        return Carb.get();
    }

    public void setCarb(String PreB)
    {
        this.Carb.set(PreB);
    }

    public String getTime() { return Time.get();}

    public void setTime(String time){this.Time.set(time);}

    public String getDate() { return Date.get();}

    public void setDate(String time){this.Date.set(time);}
    public boolean isEmpty() {
        boolean a=false;
        if(Gluc.equals("")&&Carb.equals(""))
            a=true;
        return a;
    }
}
