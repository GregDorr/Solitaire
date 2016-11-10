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

    CardPane fromPane;
    ImageCard moveCard;
    boolean canStack = false;

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
            Card[] temp = deck.deal(index+1);

            //adding all the cards
            for(int i = 1; i < temp.length; i++){
                //creating a new ImageCard and adding an action listener
                ImageCard c2 = new ImageCard(temp[i].getCardBack(),temp[i]);
                //adding the image card to the stack
                stacks[index].add(c2);
                System.out.println(temp[i].getCardFace().getBoundsInParent().toString());
            }

            //add the first card last inorder to have it be on top face up
            ImageCard c1 = new ImageCard(temp[0].getCardFace(),temp[0]);
            //changing the status of the card to be up
            c1.getCard().setFaceUp(true);
            //adding event listener
            //addCard[Number: 7, Suit: Spades] the card
            stacks[index].add(c1);


            stacks[index].setOnMouseClicked((MouseEvent event) -> flipCardOver(event));


            //adding a drop listener
            //LAMBDA EXPRESSION

            //dragDetected
            stacks[index].setOnDragDetected((MouseEvent event) -> dragDetected(event));

            //dragOver
            stacks[index].setOnDragOver((DragEvent event) -> dragOver(event));

            //dragDrop
            stacks[index].setOnDragDropped((DragEvent event) -> dragDropped(event));



        }
    }

    /**
     * flips the card over if there isn't any card on top of it
     * @param event
     */
    private void flipCardOver(final MouseEvent event){
        CardPane p1 =(CardPane)event.getSource();

        if(p1.getMoveAbleCards().size() == 0){
            ImageCard c1 = ((ImageCard)p1.getLastChild());

            //flipping the card to face up.
            c1.getCard().setFaceUp(true);
            c1.setImage(c1.getCard().getCardFace());

        }
    }

    /**
     * DragDetected
     * @param event
     */
    private void dragDetected(final MouseEvent event){

        ArrayList<ImageCard> src = ((CardPane)event.getSource()).getMoveAbleCards();

        //TODO remove output
        System.out.println(src);

        if(src.size() != 0){
            fromPane = (CardPane)event.getSource();
            moveCard = src.get(0);


            Dragboard db = startDragAndDrop(TransferMode.MOVE);
            startDragAndDrop(TransferMode.MOVE);
            db.setDragView(moveCard.getImage());


            ClipboardContent content = new ClipboardContent();
            content.putString(src.get(0).getCard().toString());
            db.setContent(content);
        }


        event.consume();

    }

    /**
     * DRAGOVEREVENT
     * @param event
     */
    private void dragOver(final DragEvent event){
        //Dragboard db = event.getDragboard();
        //casting to a card

        //getting the pane it's currently in
        CardPane toPane = (CardPane) event.getSource();

        //get the current card it's comparing it
        ImageCard currentCard = (ImageCard)toPane.getLastChild();

        //checking if the moveCard can stack on the current card
        if(moveCard.getCard().isStackableOn(currentCard.getCard())){
            canStack = true;
            event.acceptTransferModes(TransferMode.MOVE);

        }

        event.consume();
    }



    /**
     * drop
     * @param event
     */
    private void dragDropped(final DragEvent event){

        CardPane toPane = (CardPane) event.getSource();

        //if the card can stack it'll move it
        if(canStack){
            fromPane.remove(moveCard);
            toPane.add(moveCard);
        }


        event.setDropCompleted(true);
        event.consume();
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
