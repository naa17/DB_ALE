//Intensive page - specifies its fxml file for the UI
package logbook_comprehensive;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher_compLog extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../../../../../lb_v2.fxml"));
        primaryStage.setTitle("Comprehensive Logbook");
        primaryStage.setScene(new Scene(root, 800, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
