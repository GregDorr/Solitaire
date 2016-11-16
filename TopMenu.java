package Solitaire;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

/**
 * @author Greg
 * @version 1.0
 * Created by Greg on 11/5/2016.
 *
 * This class is the top menu game
 */
public class TopMenu extends MenuBar {

    private Menu menuFile;
    private Menu menuHelp;
    private MenuItem exitMenuItem;
    private MenuItem newGame;

    private Main main;

    /**
     * Default constructor
     */
    public TopMenu(Main main){
        super();

        this.main = main;

        //initializing the menu items
        menuFile = new Menu("File");
        menuHelp = new Menu("Help");

        setUpFile();
        setUpHelp();

        super.getMenus().addAll(menuFile, menuHelp);
    }

    private void setUpFile(){
        newGame = new MenuItem("New Game");
        //TODO new game action listener
       /* newGame.setOnAction((ActionEvent)-> {
            BorderPane p1 = new BorderPane();
            p1.setTop(new TopMenu(main));
            main.setStage(new MainScene(p1));
        });
        */
        exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        menuFile.getItems().addAll(newGame, exitMenuItem);
    }

    private void setUpHelp(){

    }



}

