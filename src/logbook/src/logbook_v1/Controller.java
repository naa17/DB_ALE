package logbook_v1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;


// FXML Controller class


public class Controller implements Initializable
{



    @FXML
    public TableView<Today> inputTable;
    //public TableColumn<Today, String> gluc_carb;
    public TableColumn<Today, Integer> PreB;
    public TableColumn<Today, Integer> PostB;
    public TableColumn<Today, Integer> PreL;
    public TableColumn<Today, Integer> PostL;
    public TableColumn<Today, Integer> PreD;
    public TableColumn<Today, Integer> PostD;
    public TextField PreBinput;
    public TextField PostBinput;
    public TextField PreLinput;
    public TextField PostLinput;
    public TextField PreDinput;
    public TextField PostDinput;
    public TextField gluc_carb_input;
    public Button button_input;
    public Button button;


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        PreB.setCellValueFactory(new PropertyValueFactory<Today,Integer>("PreB"));
        PostB.setCellValueFactory(new PropertyValueFactory<Today,Integer>("PostB"));
        PreL.setCellValueFactory(new PropertyValueFactory<Today,Integer>("PreL"));
        PostL.setCellValueFactory(new PropertyValueFactory<Today,Integer>("PostL"));
        PreD.setCellValueFactory(new PropertyValueFactory<Today,Integer>("PreD"));
        PostD.setCellValueFactory(new PropertyValueFactory<Today,Integer>("PostD"));
        //gluc_carb.setCellValueFactory(new PropertyValueFactory<Today,String>("gluc_carb"));
        inputTable.setItems(data);

        //inputTable.setEditable(true);
        //PreB.setCellValueFactory(TextFieldTableCell.forTableColumn());

    }

    private ObservableList<Today> data = FXCollections.observableArrayList(
       new Today("1" , "2" , "3" , "4" , "5", "6" ));
        // new Today("Carbs Eaten"  , 11, 22, 33, 44, 55, 66)
        //);

    //final ObservableList<Today> data = FXCollections.observableArrayList(
           // new Today("Blood Glucose", 1 , 2 , 3 , 4 , 5, 6 ),
             // new Today("Carbs Eaten"  , 11, 22, 33, 44, 55, 66)
    //);

    //@FXML
    //public void jup(ActionEvent actionEvent) {

                //System.out.println("YUP");


        //}



    //public void buttonAdd(ActionEvent actionEvent) {
       //System.out.println("YUP");
       //Today today = new Today("HEYYOO", 111, 222, 333, 444, 555, 666);
       //inputTable.getItems().add(today);

    //}


    //public void buttonAdd(ActionEvent actionEvent) {
        //Today today = new Today(gluc_carb_input.getText(), gluc_input.getText(), carb_input.getText(), gluc_input2.getText(), carb_input2.getText(), gluc_input3.getText(), carb_input3.getText());
        //Today today = new Today("HEYYOO", "111", "222", "333", "444", "555", "666");
        //inputTable.getItems().add(today);
    //}
}
