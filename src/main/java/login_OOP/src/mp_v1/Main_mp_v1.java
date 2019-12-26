package login_OOP.src.mp_v1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main_mp_v1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("mp_v1_1.fxml"));
            primaryStage.setTitle("Main Page");
            primaryStage.setScene(new Scene(root, 800, 500));
            primaryStage.show();
        } catch (Exception e){
            System.out.println("Something went wrong.");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}