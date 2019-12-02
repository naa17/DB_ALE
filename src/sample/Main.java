package sample;

import backend_login.CSVreader_mp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("v2.fxml"));
        primaryStage.setTitle("Main Page Version 2");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
        List<String> email_pass = CSVreader_mp.readCSV("C:\\Users\\Sarah\\Documents\\Year 3\\Programming 3\\DB_ALE\\src\\backend_login\\exampleCSV.csv");
    }
}