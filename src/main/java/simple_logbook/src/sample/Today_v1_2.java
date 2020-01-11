package simple_logbook.src.sample;

import javafx.beans.property.SimpleStringProperty;

public class Today_v1_2 {
    public SimpleStringProperty Gluc;
    public SimpleStringProperty Carb;
    public SimpleStringProperty Time;
    public  SimpleStringProperty Date;

    /*
    public Today_v1_2()
    {
        this.Gluc=null;
        this.Carb=null;
    }
    */

    public Today_v1_2(String Gluc, String Carb, String Time, String Date)
    {
        this.Gluc = new SimpleStringProperty(Gluc);
        this.Carb = new SimpleStringProperty(Carb);
        this.Time = new SimpleStringProperty(Time);
        this.Date=new SimpleStringProperty(Date);
    }

    public Today_v1_2()
    {}
    public String getGluc()
    {
        return Gluc.get();
    }

    public void setGluc(String glucose)
    {
        this.Gluc.set(glucose);
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

    public void setDate(String day){this.Date.set(day);}



    public boolean isEmpty() {
        boolean a=false;
        if(Gluc.equals("")&&Carb.equals(""))
            a=true;
        return a;
    }
}
