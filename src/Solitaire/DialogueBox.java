package Solitaire;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by Greg on 11/26/2016.
 */
public class DialogueBox extends Scene {

    //Main Border Pane
    private BorderPane pane;

    //the link back to main
    private MainScene main;

    //stage it's on
    private Stage stage;

    //buttons
    private Button newGame;
    private Button exit;
    private Button AutoComplete;

    //Label
    private Label dialogue;

    //gridlayout
    private GridPane grid;

    public DialogueBox(BorderPane pane, Stage stage, MainScene main){
        super(pane,300,200);

        //assigining the pane
        this.pane = pane;

        //assigning the main
        this.main = main;

        //assigning the stage
        this.stage = stage;

        //initializing the buttons
        newGame = new Button("New Game");
        newGame.setOnAction((ActionEvent event)->newGameListener(event));

        exit = new Button("Exit");
        exit.setOnAction((ActionEvent event)-> Platform.exit());

        AutoComplete = new Button("Auto-Complete");
        AutoComplete.setOnAction((ActionEvent event)->AutoCompleteListener(event));

        //initializing the label
        dialogue = new Label("Congrats you have Won!\nWould you like to Auto-Complete?");

        //gridpane
        grid = new GridPane();

        grid.add(newGame,0,0);
        grid.add(AutoComplete,1,0);
        grid.add(exit,2,0);

        //adding to the main pane
        pane.setCenter(dialogue);
        pane.setBottom(grid);
    }




    private void newGameListener(ActionEvent event){
       /*
        BorderPane p1 = new BorderPane();
        p1.setTop(new TopMenu(main));
        MainScene s1 = new MainScene(p1,1,main);
        main.setStage(s1);
        stage.close();
        */
    }

    private void AutoCompleteListener(ActionEvent event){
        //main.finish(14,true);
        stage.close();
    }
}
