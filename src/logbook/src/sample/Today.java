package sample;

import javafx.beans.property.SimpleStringProperty;

public class Today
{
    private SimpleStringProperty bloodgluc;
    private SimpleStringProperty carbseat;

    public Today(String bloodgluc, String carbseat)
    {
        this.bloodgluc = new SimpleStringProperty(bloodgluc);
        this.carbseat = new SimpleStringProperty(carbseat);
    }

    public SimpleStringProperty getBloodgluc()
    {
        return bloodgluc;
    }


}
