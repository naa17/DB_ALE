package comprehensive_logbook.src.sample;

import javafx.beans.property.SimpleStringProperty;

public class Today {
    public SimpleStringProperty Gluc;
    public SimpleStringProperty Carb;
    public SimpleStringProperty Time;

    public Today(String Gluc, String Carb, String Ins, String Time)
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
