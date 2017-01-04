package Solitaire;

import javafx.application.Platform;
import javafx.event.ActionEvent;
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
    private Menu settings;
    private MenuItem exitMenuItem;
    private MenuItem newGame;
    private MenuItem singleCard;
    private MenuItem tripleCard;
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

        //new game
        newGame = new MenuItem("New Game");
        newGame.setOnAction((ActionEvent event)->newGame(event));

        //settings
        settings = new Menu("Setting");
        singleCard = new MenuItem("One Card");
        singleCard.setOnAction((ActionEvent event)->changeNumCards(event,1));
        tripleCard = new MenuItem("Three Cards");
        tripleCard.setOnAction((ActionEvent event)->changeNumCards(event,3));
        settings.getItems().addAll(singleCard,tripleCard);

        //exit
        exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        menuFile.getItems().addAll(newGame, settings, exitMenuItem);
    }

    private void setUpHelp(){

    }


    private void changeNumCards(final ActionEvent event, int num){
        main.setDrawNum(num);
        newGame(event);
    }

    private void newGame(final ActionEvent event){
        BorderPane p1 = new BorderPane();
        p1.setId("ROOT");
        p1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        p1.setTop(new TopMenu(main));
        main.setStage(new MainScene(p1,main.getDrawNum(),main));
    }

}