package Solitare;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;

import java.util.ArrayList;


/**
 * @author Greg
 * @version 1.0
 * Created by Greg on 11/5/2016.
 */
public class MainScene extends Scene {

    Dragboard db;

    //temp card
    ImageCard tempCard = null;

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

    /**
     *
     * @param root
     */
    public MainScene(BorderPane root){
        super(root, 675, 400);

        //setting the root pane
        this.root = root;
        root.setId("ROOT");


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

        //setting up ids for CSS
        draw.setId("DrawStack");
        pick.setId("PickStack");

        //initializing a deck
        deck = new Deck();

        setUpStacks();
        setUpGrids();
        setUpDraw();


        //set the
        root.setTop(new TopMenu());
        root.setCenter(backGrid);
    }

    /**
     *
     */
    private void setUpDraw(){
        //getting the cards to add to the stack
        Card[] temp = deck.deal(24);

        //adding the bottom image
        ImageView v1 = new ImageView("Cards/Bottom.png");
        v1.setOnMouseClicked(new DrawCardsListener(this));
        draw.getChildren().add(v1);

        //adding add the cards
        for(int index =0; index < temp.length; index++){
            ImageCard c1 = new ImageCard(temp[index].getCardBack(),temp[index]);
            //creating a new event handler
            c1.setOnMouseClicked(new DrawCardsListener(this));
            //adding the card to the stack
            draw.getChildren().add(c1);
        }
    }

    /**
     *
     * @param list
     */
    public void resetUpDraw(ObservableList<Node> list){

        System.out.println(list.size());
        int num = list.size();

        for(int index =0; index < num; index++){
            //getting each node
            ImageCard c1 = (ImageCard)list.get(index);

            //re-adding the event handler
            c1.setOnMouseClicked(new DrawCardsListener(this));

            //flipping the card back over
            c1.getCard().setFaceUp(false);
            c1.setImage(c1.getCard().getCardBack());

            //adding the card to the stack
            draw.getChildren().add(c1);
        }
        //clearing the old stack
        pick.getChildren().clear();
    }

    //gets the draw cards pile
    public ObservableList<Node> getDraw(){
        return draw.getChildren();
    }
    //sets the draw card pile
    public void setDraw(ObservableList<Node> pile){
        draw.getChildren().clear();
        draw.getChildren().addAll(pile);
    }

    //gets the pick cards pile
    public ObservableList<Node> getPick(){
        return pick.getChildren();
    }
    //sets the pick card pile
    public void setPick(ObservableList<Node> pile){
        pick.getChildren().clear();
        pick.getChildren().addAll(pile);
    }

    /**
     * Sets up the stacks of cards
     */
    private void setUpStacks(){
        for(int index = 0; index < stacks.length; index++){
            //initializing the a stack and getting cards
            stacks[index] = new CardPane();
            stacks[index].setId("CardStacks");

            //adding a drop listener
            //LAMBDA EXPRESSION
            //dragdetected
            stacks[index].setOnDragDetected((MouseEvent event) -> {
                //dragDectected
                dragDetected(event);
            });

            //drag over
            stacks[index].setOnDragOver((DragEvent event) -> {
                //call the dragOver method
                dragOver(event);
            });

            //dragDrop
            stacks[index].setOnDragDropped((DragEvent event) -> {
                //call the dragDropped method
                dragDropped(event);
            });

            Card[] temp = deck.deal(index+1);
            //adding all the cards
            for(int i = 1; i < temp.length; i++){
                stacks[index].add(new ImageCard(temp[i].getCardBack(),temp[i]));
                System.out.println(temp[i].getCardFace().getBoundsInParent().toString());
            }
            //add the first card last inorder to have it be on top face up
            ImageCard c1 = new ImageCard(temp[0].getCardFace(),temp[0]);
            //changing the status of the card to be up
            c1.getCard().setFaceUp(true);
            //adding event listener
            //addCard[Number: 7, Suit: Spades] the card
            stacks[index].add(c1);
        }
    }

    /**
     * DragDetected
     * @param event
     */
    private void dragDetected(final MouseEvent event){

        /*
        DragBoard db = event.getSource();
        //getting the pane it's from
        CardPane p1 = (CardPane)event.getSource();

        //checking what cards you can grab from a stack
        ArrayList<ImageCard> temp = p1.getMoveAbleCards();
        //TODO remove print line
        System.out.println(temp);

        ImageCard source = temp.get(0);
        db = source.startDragAndDrop(TransferMode.MOVE);
        //the actual image being dragged
        db = source.startDragAndDrop(TransferMode.MOVE);
        db.setDragView(source.getImage());
        ClipboardContent content = new ClipboardContent();
        content.putString(source.getCard().toString());
        db.setContent(content);
        */

         /* drag was detected, start a drag-and-drop gesture*/
        /* allow any transfer mode */
        //getting the pane it's from
        CardPane p1 = (CardPane)event.getSource();

        //checking what cards you can grab from a stack
        ArrayList<ImageCard> temp = p1.getMoveAbleCards();
        //TODO remove print line
        System.out.println(temp);

        ImageCard source = temp.get(0);

        //the actual image being dragged
        db = source.startDragAndDrop(TransferMode.MOVE);
        db.setDragView(source.getImage());
        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();
        content.putString(source.getCard().toString());
        db.setContent(content);
    }

    /**
     * DRAGOVEREVENT
     * @param event
     */
    private void dragOver(final DragEvent event){
        //Dragboard db = event.getDragboard();
        //casting to a card
        CardPane pane = (CardPane) event.getSource();
        ImageCard c1 = ((ImageCard)(pane).getLastChild());
        System.out.println(c1.getCard().isStackable(tempCard.getCard()));

        if(c1.getCard().isStackable(tempCard.getCard())) {
            pane.add(tempCard);
            pick.getChildren().remove(tempCard);
            tempCard = null;
        }
    }

    /**
     * drop
     * @param event
     */
    private void dragDropped(final DragEvent event){

       // event.setDropCompleted(true);
    }

    /**
     *
     */
    private void setUpGrids() {
        //setting the size of the draw and pick field
        draw.setPrefSize(72.0,96.0);
        pick.setPrefSize(72.0,96.0);

        //top grids
        topGrid.addColumn(0, draw);
        topGrid.addColumn(1, pick);

        for (int index = 0; index < finalSpot.length; index++) {
            finalSpot[index] = new StackPane();
            finalSpot[index].setPrefSize(72.0,96.0);
            finalSpot[index].setId("FinalSpots");
            topGrid.addColumn(index + 2, finalSpot[index]);
        }

        finalSpot[0].getChildren().add(new ImageView("Cards/ClubsBackground.png"));
        finalSpot[1].getChildren().add(new ImageView("Cards/HeartsBackground.png"));
        finalSpot[2].getChildren().add(new ImageView("Cards/SpadesBackground.png"));
        finalSpot[3].getChildren().add(new ImageView("Cards/DiamondsBackground.png"));

        //for spacing out the rows
        ColumnConstraints[] columns = new ColumnConstraints[7];
        ColumnConstraints col = new ColumnConstraints();
        col.setHgrow(Priority.ALWAYS);
        col.setFillWidth(true);

        //card grids
        for (int index = 0; index < stacks.length; index++) {
            cardGrids.addColumn(index, stacks[index]);
            columns[index] = new ColumnConstraints();
            columns[index].setPercentWidth(50);
            columns[index].setHgrow(Priority.ALWAYS);
            cardGrids.getColumnConstraints().add(columns[index]);
        }


        cardGrids.setPrefWidth(root.getWidth());
        cardGrids.setPrefHeight(root.getHeight());

        backGrid.addRow(0, topGrid);
        backGrid.addRow(1, cardGrids);
        backGrid.setAlignment(Pos.CENTER);
        backGrid.getColumnConstraints().add(col);

        //TODO remove test grid lines
        backGrid.setGridLinesVisible(true);
        cardGrids.setGridLinesVisible(true);
        topGrid.setGridLinesVisible(true);
    }

    //setters and getters
    //getters
    public CardPane[] getStacks() {
        return stacks;
    }

    public void setStacks(CardPane[] stacks) {
        this.stacks = stacks;
    }

    public StackPane[] getFinalSpot() {
        return finalSpot;
    }

    public void setFinalSpot(StackPane[] finalSpot) {
        this.finalSpot = finalSpot;
    }
}
