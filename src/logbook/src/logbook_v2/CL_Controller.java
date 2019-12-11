package sample;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class CL_Controller {
    @FXML private TextField preB_gluc;
    @FXML private TextField postB_gluc;
    @FXML private TextField preL_gluc;
    @FXML private TextField postL_gluc;
    @FXML private TextField preD_gluc;
    @FXML private TextField postD_gluc;
    @FXML private TextField bed_gluc;
    @FXML private TextField preB_carbs;
    @FXML private TextField postB_carbs;
    @FXML private TextField preL_carbs;
    @FXML private TextField postL_carbs;
    @FXML private TextField preD_carbs;
    @FXML private TextField postD_carbs;
    @FXML private TextField bed_carbs;
    @FXML private TextField preB_ins;
    @FXML private TextField postB_ins;
    @FXML private TextField preL_ins;
    @FXML private TextField postL_ins;
    @FXML private TextField preD_ins;
    @FXML private TextField postD_ins;
    @FXML private TextField bed_ins;

    @FXML

    public void addVals(javafx.event.ActionEvent actionEvent) {



        double preB_glucVal = Double.parseDouble(preB_gluc.getText());
        System.out.println(preB_glucVal);

        double postB_glucVal = Double.parseDouble(postB_gluc.getText());
        double preL_glucVal = Double.parseDouble(preL_gluc.getText());
        double postL_glucVal= Double.parseDouble(postL_gluc.getText());
        double preD_glucVal= Double.parseDouble(preD_gluc.getText());
        double postD_glucVal= Double.parseDouble(postD_gluc.getText());
        double bed_glucVal= Double.parseDouble(bed_gluc.getText());
        double preB_carbsVal= Double.parseDouble(preB_carbs.getText());
        double postB_carbsVal= Double.parseDouble(postB_carbs.getText());
        double preL_carbsVal= Double.parseDouble(preL_carbs.getText());
        double postL_carbsVal= Double.parseDouble(postL_carbs.getText());
        double preD_carbsVal= Double.parseDouble(preD_carbs.getText());
        double postD_carbsVal= Double.parseDouble(postD_carbs.getText());
        double bed_carbsVal= Double.parseDouble(bed_carbs.getText());
        double preB_insVal= Double.parseDouble(preB_ins.getText());
        double postB_insVal= Double.parseDouble(postB_ins.getText());
        double preL_insVal= Double.parseDouble(preL_ins.getText());
        double postL_insVal= Double.parseDouble(postL_ins.getText());
        double preD_insVal= Double.parseDouble(preD_ins.getText());
        double postD_insVal= Double.parseDouble(postD_ins.getText());
        double bed_insVal= Double.parseDouble(bed_ins.getText());


    }
}

