package Solitaire;

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

    private Stage stage;
    private BorderPane p1;

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            //setting the top menu to the default border pane
            p1 = new BorderPane();
            TopMenu m1 = new TopMenu(this);
            p1.setTop(m1);

            //setting the stage
            stage = primaryStage;

            //adding it to the main scene
            MainScene scene = new MainScene(p1);
            primaryStage.setTitle("Solitaire");
            primaryStage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            //setting the cursor

            primaryStage.show();

        } catch (Exception ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //public void setStage(MainScene scene){stage.setScene(scene);}
    //public BorderPane getBorderPane(){return this.p1;}

    /**
     * MAIN
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
