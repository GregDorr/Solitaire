package Solitare;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Greg
 * @version 1.0
 *
 * Launches the GUI and program
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            MainScene scene = new MainScene(new BorderPane());
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Solitaire");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
