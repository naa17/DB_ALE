//Today class for the comprehensive method - contains all data that is needed for this logbook type
package logbook_comprehensive;

import javafx.beans.property.SimpleStringProperty;

public class Today_comp extends DB_ALE.Today {

    public SimpleStringProperty Ins;

    public Today_comp(String Gluc, String Carb, String Ins, String Time, String Date) {
        super(Gluc, Carb, Time, Date);
        this.Ins= new SimpleStringProperty(Ins);
    }

    public String getIns(){ return Ins.get();}
    public void setIns(String Ins){this.Ins.set(Ins);}

}
