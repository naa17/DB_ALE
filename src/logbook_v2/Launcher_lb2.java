package logbook_v2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher_lb2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("lb2.fxml"));
        primaryStage.setTitle("Comprehensive Logbook");
        primaryStage.setScene(new Scene(root, 820, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
