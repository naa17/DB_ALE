package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main_lb_v1_2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../../../../../lb_v1_2.fxml"));
        primaryStage.setTitle("Simple Logbook");
        primaryStage.setScene(new Scene(root, 650, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

