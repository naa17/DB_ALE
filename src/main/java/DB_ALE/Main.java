/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package DB_ALE;
//import java.login_OOP.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends Application{


    @Override
    public void start(Stage primaryStage) throws IOException {
        try{
            URL url = new File("/Users/admin/Documents/Java/group_project/DB_ALE/src/main/java/login_OOP/src/mp_v1/mp_v1_1.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            primaryStage.setTitle("Login Page");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
        }catch (Exception e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }


}


