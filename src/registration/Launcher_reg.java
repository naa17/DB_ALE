package registration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher_reg extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("registration.fxml"));
        primaryStage.setTitle("Registration Page");
        primaryStage.setScene(new Scene(root, 650, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
