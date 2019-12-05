package logbook.src.logbook_v1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Cell;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.beans.value.ObservableIntegerValue;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller_lb_v1_1 implements Initializable
{
    @FXML
    public TableView<Today> inputTable;
    public TableColumn<Today, String> PreB;
    public TableColumn<Today, String> PostB;
    public TableColumn<Today, String> PreL;
    public TableColumn<Today, String> PostL;
    public TableColumn<Today, String> PreD;
    public TableColumn<Today, String> PostD;
    public TextField PreBinput;
    public TextField PostBinput;
    public TextField PreLinput;
    public TextField PostLinput;
    public TextField PreDinput;
    public TextField PostDinput;
    public Button button_input;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        PreB.setCellValueFactory(new PropertyValueFactory<Today,String>("PreB"));
        PostB.setCellValueFactory(new PropertyValueFactory<Today,String>("PostB"));
        PreL.setCellValueFactory(new PropertyValueFactory<Today,String>("PreL"));
        PostL.setCellValueFactory(new PropertyValueFactory<Today,String>("PostL"));
        PreD.setCellValueFactory(new PropertyValueFactory<Today,String>("PreD"));
        PostD.setCellValueFactory(new PropertyValueFactory<Today,String>("PostD"));
        //inputTable.setItems(data);
        inputTable.setEditable(true);
        PreB.setCellFactory(TextFieldTableCell.forTableColumn());
        PostB.setCellFactory(TextFieldTableCell.forTableColumn());
        PreL.setCellFactory(TextFieldTableCell.forTableColumn());
        PostL.setCellFactory(TextFieldTableCell.forTableColumn());
        PreD.setCellFactory(TextFieldTableCell.forTableColumn());
        PostD.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    //private ObservableList<Today> data = FXCollections.observableArrayList(
            //new Today(1,2,3,4,5,6 )
    //);


    public void buttonAdd(ActionEvent actionEvent) {
        System.out.println("YES");
        //Today Today = new Today(11,22,33,44,55,66);
        Today Today = new Today(PreBinput.getText(), PostBinput.getText(), PreLinput.getText(), PostLinput.getText(),
               PreDinput.getText(), PostDinput.getText());
        inputTable.getItems().add(Today);
        //Integer PreB = PreBinput.getText();
    }

    public void EditValue(TableColumn.CellEditEvent<Today, String> TodayStringCellEditEvent) {
        Today Today = inputTable.getSelectionModel().getSelectedItem();
        Today.setPreB(TodayStringCellEditEvent.getNewValue());
        Today.setPostB(TodayStringCellEditEvent.getNewValue());
        Today.setPreL(TodayStringCellEditEvent.getNewValue());
        Today.setPostL(TodayStringCellEditEvent.getNewValue());
        Today.setPreD(TodayStringCellEditEvent.getNewValue());
        Today.setPostD(TodayStringCellEditEvent.getNewValue());
    }
}
