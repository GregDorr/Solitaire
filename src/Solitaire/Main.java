package Solitaire;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
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
    private Stage newStage;
    private BorderPane p1;
    private int drawNum;

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Controller c1 = new Controller();

            drawNum = 1;

            //setting the top menu to the default border pane
            p1 = new BorderPane();
            TopMenu m1 = new TopMenu(this);
            p1.setTop(m1);

            //setting the stage
            stage = primaryStage;

            //adding it to the main scene
            MainScene scene = new MainScene(p1,3);

            primaryStage.setTitle("Solitaire");
            primaryStage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            //setting the cursor

            primaryStage.show();

        } catch (Exception ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setDrawNum(int num){
        drawNum = num;
    }

    public void setStage(MainScene scene){stage.setScene(scene);}
    public BorderPane getBorderPane(){return this.p1;}


    //action listener for the settings
    public void settings(final ActionEvent e){

    }

    /**
     * MAIN
     * @param args
     */
    public static void main(String[] args) {

        launch(args);
    }
}
