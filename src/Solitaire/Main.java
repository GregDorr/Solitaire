package Solitaire;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
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

    private MainScene scene;
    private Stage stage;
    private BorderPane p1;
    private int drawNum;
    private FinalAnimation win;

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            drawNum = 1;
            //setting the top menu to the default border pane
            p1 = new BorderPane();
            TopMenu m1 = new TopMenu(this);
            p1.setTop(m1);

            BorderPane p2 = new BorderPane();
            TopMenu m2 = new TopMenu(this);
            p1.setTop(m2);

            stage = primaryStage;

            //setting the stage
            //adding it to the main scene
            scene = new MainScene(p1,3,this);
            stage.setTitle("Solitaire");
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            //setting the cursor
            stage.show();

            Stop[] stops = {new Stop(1, Color.GREEN),new Stop(0,Color.BLACK)};
            scene.setFill(new RadialGradient(0,0,337.5,250,200,false, CycleMethod.REFLECT,stops));


            //14 solves whole thing
            //TODO
           //scene.robotFinish(14,false);

        } catch (Exception ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setDrawNum(int num){drawNum = num;}
    public void TurnOnWinAnimation(){
        stage.setScene(win);
    }
    public MainScene getScene(){
        return scene;
    }
    public void setScene(MainScene newScene){this.scene = newScene;}

    public void setStage(Scene scene){stage.setScene(scene);}

    public int getDrawNum(){return drawNum;}

    /**
     * MAIN
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
