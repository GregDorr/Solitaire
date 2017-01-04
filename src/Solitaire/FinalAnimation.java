package Solitaire;

import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.util.Duration;

/**
 * Created by Greg on 11/22/2016.
 */
public class FinalAnimation extends Scene {

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
    private CardPane pick;
    private CardPane[] finalSpot;

    //deck
    private Deck deck;

    public FinalAnimation(BorderPane root){
        super(root, 675, 400);
        super.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        //setting the root pane
        this.root = root;
        root.setId("ROOT");


        //setting the cursor
        this.setCursor(Cursor.HAND);

        //setting up a grid layout
        backGrid = new GridPane();
        cardGrids = new GridPane();
        topGrid = new GridPane();

        //initializing the cardPanes
        stacks = new CardPane[7];

        //initializing the StackPanes
        draw = new StackPane();
        pick = new CardPane(8);
        finalSpot = new CardPane[4];

        //setting up ids for CSS
        draw.setId("DrawStack");
        pick.setId("PickStack");

        //initializing a deck
        deck = new Deck();

        setUpGrids();
        setUpFinal();

        //set the
        root.setCenter(backGrid);
    }

    private void setUpGrids() {
        //setting the size of the draw and pick field
        draw.setPrefSize(92.0,116.0);
        pick.setPrefSize(112.0,136.0);

        pick.setPadding(new Insets(0,0,0,0));

        //top grids
        topGrid.addColumn(0, draw);
        topGrid.addColumn(1, pick);

        for (int index = 0; index < finalSpot.length; index++) {
            finalSpot[index] = new CardPane((index+9));
            finalSpot[index].setPrefSize(72.0,96.0);
            finalSpot[index].setId("FinalSpots");
            topGrid.addColumn(index + 2, finalSpot[index]);
        }

        //for spacing out the rows
        ColumnConstraints[] columns = new ColumnConstraints[7];
        ColumnConstraints col = new ColumnConstraints();
        col.setHgrow(Priority.ALWAYS);
        col.setFillWidth(true);


        cardGrids.setPrefWidth(root.getWidth());
        cardGrids.setPrefHeight(root.getHeight());

        backGrid.addRow(0, topGrid);
        backGrid.addRow(1, cardGrids);
        backGrid.setAlignment(Pos.CENTER);
        backGrid.getColumnConstraints().add(col);

    }


    private void setUpFinal(){
        for(int index = 0; index < 13; index++){
            Card newHearts = new Card((index+1), "Hearts", "red",deck.findCard((index+1),"H"));
            Card newSpades = new Card((index+1), "Spades", "black",deck.findCard((index+1),"S"));
            Card newDiamonds = new Card((index+1),"Diamonds","red",deck.findCard((index+1),"D"));
            Card newClubs = new Card((index+1),"Clubs", "black",deck.findCard((index+1),"C"));

            ImageCard c1 = new ImageCard(newClubs.getCardFace(),newClubs,9);
            ImageCard c2 =new ImageCard(newHearts.getCardFace(),newHearts,10);
            ImageCard c3 =new ImageCard(newSpades.getCardFace(),newSpades,11);
            ImageCard c4 =new ImageCard(newDiamonds.getCardFace(),newDiamonds,12);

            applyAnimation(c1);
            applyAnimation(c2);
            applyAnimation(c3);
            applyAnimation(c4);

            finalSpot[0].add(c1);
            finalSpot[1].add(c2);
            finalSpot[2].add(c3);
            finalSpot[3].add(c4);
        }
    }

    private void applyAnimation(Node card){
        Path path = new Path();
        path.getElements().add(new MoveTo(20,20));
        path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
        path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setPath(path);
        pathTransition.setNode(card);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pathTransition.play();
    }
}
