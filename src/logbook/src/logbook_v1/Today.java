package logbook.src.logbook_v1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Today
{
    //private SimpleStringProperty gluc_carb;
    private SimpleStringProperty PreB;
    private SimpleStringProperty PostB;
    private SimpleStringProperty PreL;
    private SimpleStringProperty PostL;
    private SimpleStringProperty PreD;
    private SimpleStringProperty PostD;


    Today(String PreB, String PostB ,String PreL, String PostL, String PreD, String PostD)
    {
        //this.gluc_carb = new SimpleStringProperty(gluc_carb);
        this.PreB = new SimpleStringProperty(PreB);
        this.PostB = new SimpleStringProperty(PostB);
        this.PreL = new SimpleStringProperty(PreL);
        this.PostL = new SimpleStringProperty(PostL);
        this.PreD = new SimpleStringProperty(PreD);
        this.PostD = new SimpleStringProperty(PostD);

    }


    public String getPreB()
    {
        return PreB.get();
    }

    public void setPreB(String PreB)
    {
        this.PreB.set(PreB);
    }

    public String getPostB()
    {
        return PostB.get();
    }

    public void setPostB(String PostB)
    {
        this.PostB.set(PostB);
    }

    public String getPreL()
    {
        return PreL.get();
    }

    public void setPreL(String PreL)
    {
        this.PreL.set(PreL);
    }

    public String getPreD()
    {
        return PreD.get();
    }

    public void setPreD(String PreD)
    {
        this.PreD.set(PreD);
    }

    public String getPostD()
    {
        return PostD.get();
    }

    public void setPostD(String PostD)
    {
        this.PostD.set(PostD);
    }


    public String getPostL()
    {
        return PostL.get();
    }

    public void setPostL(String PostD)
    {
        this.PostL.set(String.valueOf(PostL));
    }

    //public String getGluc_carb()
    //{
    ///    return gluc_carb.get();
    //}

    //public void setGluc_carb(SimpleStringProperty gluc_carb)
    //{
       // this.gluc_carb.set(String.valueOf(gluc_carb));
   // }


}
