package comprehensive_logbook.src.sample;

import javafx.beans.property.SimpleStringProperty;

public class Today_ins extends Today {

    public SimpleStringProperty Ins;

    public Today_ins(String Gluc, String Carb, String Ins, String Time, String Date) {
        super(Gluc, Carb, Ins, Time, Date);
        this.Ins= new SimpleStringProperty(Ins);
    }

    public String getIns(){ return Ins.get();}
    public void setIns(String Ins){this.Ins.set(Ins);}

}
