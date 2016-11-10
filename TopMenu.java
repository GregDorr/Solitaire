package Solitare;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

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


    /**
     * Default constructor
     */
    public TopMenu(){
        super();

        setUpFile();
        setUpHelp();

        super.getMenus().addAll(menuFile, menuHelp);
    }

    private void setUpFile(){
        menuFile = new Menu("File");
        newGame = new MenuItem("New Game");

        //TODO new game action listener
        newGame.setOnAction((ActionEvent)-> System.out.println("No"));

        exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        menuFile.getItems().addAll(newGame, exitMenuItem);
    }

    private void setUpHelp(){
        menuHelp = new Menu("Help");
    }



}

