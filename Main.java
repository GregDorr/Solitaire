package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            //BorderPane root = FXMLLoader.load(Main.class.getResource("/sample/sample.fxml"));
            primaryStage.setTitle("Solitaire");
            primaryStage.setScene(new MainScene(new BorderPane()));
            primaryStage.show();


        } catch (Exception ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }


    }



    public static void main(String[] args) {

        launch(args);

    }
}
