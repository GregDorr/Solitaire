package sample;


import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * Created by Greg on 11/5/2016.
 */
public class MainScene extends Scene {

    private final int HEIGHT = 600;
    private final int WIDTH = 400;

    //bottom most node
    private BorderPane root;

    //layout panes
    private GridPane backGrid;
    private GridPane cardGrids;
    private GridPane topGrid;

    //main card stacks
    private CardPane[] stacks;

    //final stacks and deck
    private StackPane draw;
    private StackPane pick;
    private StackPane[] finalSpot;

    //deck
    private Deck deck = new Deck();

    public MainScene(BorderPane root){
        super(root, 600, 400);

        //setting the root pane
        this.root = root;

        //setting up a grid layout
        backGrid = new GridPane();
        cardGrids = new GridPane();
        topGrid = new GridPane();

        //initializing the cardPanes
        stacks = new CardPane[7];

        //initializing the StackPanes
        draw = new StackPane();
        pick = new StackPane();
        finalSpot = new StackPane[4];

        //initializing a deck
        deck = new Deck();

        setUpStacks();
        setUpGrids();

        root.setCenter(backGrid);
    }

    private void setUpStacks(){
        for(int index = 0; index < stacks.length; index++){

            //initializing the a stack and getting cards
            stacks[index] = new CardPane();
            Card[] temp = deck.deal(index+1);

            //adding all the cards
            for(int i = 1; i < temp.length; i++){
                stacks[index].add(temp[i].getCardBack());
            }

            //add first last one on the top
            stacks[index].add(temp[0].getCardFace());
        }
    }

    private void setUpGrids(){

        //top grids
        topGrid.addColumn(0, draw);
        topGrid.addColumn(1, pick);
        for(int index = 0; index < finalSpot.length; index++){
            finalSpot[index] = new StackPane();
            topGrid.addColumn(index+2, finalSpot[index]);
        }

        for(int index = 0; index < stacks.length; index++) {
            cardGrids.addColumn(index, stacks[index]);
        }

        backGrid.addRow(0,topGrid);
        backGrid.addRow(1,cardGrids);
    }


}
