package simple_logbook.src.sample;

import javafx.beans.property.SimpleStringProperty;

public class Today_v1_2 {
    public SimpleStringProperty Gluc;
    public SimpleStringProperty Carb;
    public SimpleStringProperty Time;

    /*
    public Today_v1_2()
    {
        this.Gluc=null;
        this.Carb=null;
    }
    */

    public Today_v1_2(String Gluc, String Carb, String Time)
    {
        this.Gluc = new SimpleStringProperty(Gluc);
        this.Carb = new SimpleStringProperty(Carb);
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

    public boolean isEmpty() {
        boolean a=false;
        if(Gluc.equals("")&&Carb.equals(""))
            a=true;
        return a;
    }
}
