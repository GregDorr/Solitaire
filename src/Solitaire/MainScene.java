package Solitaire;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
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
    ArrayList<ImageCard> cardsBelow;
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
    private CardPane pick;
    private CardPane[] finalSpot;

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
        pick = new CardPane(8);
        finalSpot = new CardPane[4];

        //setting up ids for CSS
        draw.setId("DrawStack");
        pick.setId("PickStack");

        //initializing a deck
        deck = new Deck();

        setUpStacks();
        setUpGrids();
        setUpDraw();


        //set the
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
        //TODO add custom action listener for this
        v1.setOnMouseClicked((MouseEvent event) -> drawCard(event));
        draw.getChildren().add(v1);

        //adding add the cards
        for(int index =0; index < temp.length; index++){
            ImageCard c1 = new ImageCard(temp[index].getCardBack(),temp[index], 8);
            //creating a new event handler
            c1.setOnMouseClicked((MouseEvent event) -> drawCard(event));
            //adding the card to the stack
            draw.getChildren().add(c1);
        }
    }

    private void drawCard(final MouseEvent event){
        int drawSize = draw.getChildren().size();
        int pickSize = pick.getChildren().size();

        //if the background image is all thats left.
        if(drawSize == 1){

            //resets the pick pile. and puts it back into the draw pile
            for(int index = pickSize-1; index >= 0; index--){
                ImageCard c1 =(ImageCard)pick.getChildren().get(index);

                //fliping the card back over
                c1.setImage(c1.getCard().getCardBack());
                c1.getCard().setFaceUp(false);

                //getting rid of the spacing
                StackPane.clearConstraints(c1);

                //removing drag dected listener
                c1.setOnDragDetected(null);

                //adding click lsitener
                c1.setOnMouseClicked((MouseEvent e) -> drawCard(e));

                //add to the draw stack
                draw.getChildren().add(c1);
            }

            pick.getChildren().clear();
        }

        else{
            //getting the card
            ImageCard c1 = (ImageCard)draw.getChildren().get(drawSize-1);

            //setting it face up
            c1.setImage(c1.getCard().getCardFace());
            c1.getCard().setFaceUp(true);

            //removing the action listener and adding a new one
            c1.setOnMouseClicked((MouseEvent e) -> setCardFlipAndStackListener(e));
            //adding the drag and drop listener
            c1.setOnDragDetected((MouseEvent e) -> dragDetected(e));

            //adding it to the draw stack
            pick.add(c1);
            draw.getChildren().remove(c1);
        }

        event.consume();

    }

    /**
     * Sets up the stacks of cards
     */
    private void setUpStacks(){
        for(int index = 0; index < stacks.length; index++){
            //initializing the a stack and getting cards
            stacks[index] = new CardPane(index);
            stacks[index].setId("CardStacks");
            Card[] temp = deck.deal(index+1);

            //adding all the cards
            for(int i = 1; i < temp.length; i++){
                //creating a new ImageCard and adding an action listener
                ImageCard c2 = new ImageCard(temp[i].getCardBack(),temp[i], index);

                //flip over and stack listener
                c2.setOnMouseClicked((MouseEvent event) -> setCardFlipAndStackListener(event));

                //drag detected listener
                c2.setOnDragDetected((MouseEvent event) -> dragDetected(event));

                stacks[index].addVertical(c2);
                System.out.println(temp[i].getCardFace().getBoundsInParent().toString());
            }

            //add the first card last inorder to have it be on top face up
            ImageCard c1 = new ImageCard(temp[0].getCardFace(),temp[0], index);

            //dragevent
            c1.setOnDragDetected((MouseEvent event) -> dragDetected(event));

            //flip over and final stack listener
            c1.setOnMouseClicked((MouseEvent event) -> setCardFlipAndStackListener(event));

            //changing the status of the card to be up
            c1.getCard().setFaceUp(true);
            //adding event listener
            //addCard[Number: 7, Suit: Spades] the card
            stacks[index].addVertical(c1);

            //adding a drop listener
            //LAMBDA EXPRESSION
            //dragDetected

            //dragOver
            stacks[index].setOnDragOver((DragEvent event) -> dragOver(event));

            //dragDrop
            stacks[index].setOnDragDropped((DragEvent event) -> dragDropped(event));
        }
    }

    private void putUpFinalCard(){

    }

    /**
     *
     * @param event
     */
    private void setCardFlipAndStackListener(MouseEvent event){

        System.out.println("Number of clicks: " + event.getClickCount());

        if(event.getClickCount()==2) {
            ImageCard c1 = (ImageCard) (event.getSource());
            CardPane p1;
            if(c1.getStackNumber() == 8){
               p1 = pick;
            }
            else {
                p1 = stacks[((ImageCard) event.getSource()).getStackNumber()];
            }

            String suit = c1.getCard().getSuit();
            switch(suit){
                case "Clubs":


                    if(finalSpot[0].getChildren().size() > 1){
                        //getting the previous card on the stack
                        int currentSize = finalSpot[0].getChildren().size();
                        ImageCard lastCard = (ImageCard)finalSpot[0].getChildren().get(currentSize-1);

                        if(c1.getCard().getCardNum() - lastCard.getCard().getCardNum() == 1)
                        {
                            StackPane.clearConstraints(c1);
                            finalSpot[0].getChildren().add(c1);
                            p1.remove(c1);
                        }

                    }
                    else {
                        if(c1.getCard().getCardNum() == 1) {

                            StackPane.clearConstraints(c1);
                            finalSpot[0].getChildren().add(c1);
                            p1.remove(c1);
                        }
                    }
                    event.consume();
                    break;

                case "Hearts":
                    if(finalSpot[1].getChildren().size() > 1){
                        //getting the previous card on the stack
                        int currentSize = finalSpot[1].getChildren().size();
                        ImageCard lastCard = (ImageCard)finalSpot[1].getChildren().get(currentSize-1);

                        System.out.println("IS STACKABLE: " + (c1.getCard().getCardNum() - lastCard.getCard().getCardNum() == 1));

                        if((c1.getCard().getCardNum() - lastCard.getCard().getCardNum()) == 1)
                        {
                            StackPane.clearConstraints(c1);
                            finalSpot[1].getChildren().add(c1);
                            p1.remove(c1);
                        }

                    }
                    else {
                        if(c1.getCard().getCardNum() == 1) {
                            StackPane.clearConstraints(c1);
                            finalSpot[1].getChildren().add(c1);
                            p1.remove(c1);
                        }
                    }
                    event.consume();
                    break;

                case "Spades":

                    System.out.println("NUMBER OF CHILDREN: " + finalSpot[2].getChildren().size());

                    if(finalSpot[2].getChildren().size() > 1){
                        //getting the previous card on the stack
                        int currentSize = finalSpot[2].getChildren().size();
                        ImageCard lastCard = (ImageCard)finalSpot[2].getChildren().get(currentSize-1);

                        if((c1.getCard().getCardNum() - lastCard.getCard().getCardNum()) == 1)
                        {
                            StackPane.clearConstraints(c1);
                            finalSpot[2].getChildren().add(c1);
                            p1.remove(c1);
                        }

                    }
                    else {
                        if(c1.getCard().getCardNum() == 1) {

                            StackPane.clearConstraints(c1);
                            finalSpot[2].getChildren().add(c1);
                            p1.remove(c1);
                        }
                    }
                    event.consume();
                    break;

                case "Diamonds":

                    System.out.println("NUMBER OF CHILDREN: " + finalSpot[3].getChildren().size());

                    if(finalSpot[3].getChildren().size() > 1){
                        //getting the previous card on the stack
                        int currentSize = finalSpot[3].getChildren().size();
                        ImageCard lastCard = (ImageCard)finalSpot[3].getChildren().get(currentSize-1);

                        if((c1.getCard().getCardNum() - lastCard.getCard().getCardNum()) == 1)
                        {
                            StackPane.clearConstraints(c1);
                            finalSpot[3].getChildren().add(c1);
                            p1.remove(c1);
                        }
                    }
                    else {
                        if(c1.getCard().getCardNum() == 1) {

                            StackPane.clearConstraints(c1);
                            finalSpot[3].getChildren().add(c1);
                            p1.remove(c1);
                        }
                    }
                    event.consume();
                    break;

            }
        }

        else if(event.getClickCount() == 1){
            ImageCard c1 = ((ImageCard)event.getSource());
            CardPane p1;
            if(c1.getStackNumber() == 8)
                p1 = pick;
            else
                p1 = stacks[c1.getStackNumber()];

            System.out.println(p1);

            if(p1.getMoveAbleCards().size() == 0){
                c1 = ((ImageCard)p1.getLastChild());

                //flipping the card to face up.
                c1.getCard().setFaceUp(true);
                c1.setImage(c1.getCard().getCardFace());
            }
            event.consume();
        }
        event.consume();

    }

    private Image createImage(){
        if(cardsBelow.size() > 0){
            CardPane p1 = new CardPane(999);
            p1.setPadding(new Insets(0,0,0,0));
            ImageView v1 = new ImageView(moveCard.getImage());
            p1.addVertical(v1);

            p1.setMargin(v1,new Insets(0,0,0,0));

            //p1.addVertical(moveCard);

            for(int index = cardsBelow.size()-1; index >= 0; index--){
                Image image = cardsBelow.get(index).getImage();

                ImageView v2 = new ImageView(image);
                p1.addVertical(v2);
            }

            Scene scene = new Scene(p1);
            WritableImage img = new WritableImage(72,(96+ (10*p1.getNumCards() + 5*p1.getNumCards())));
            scene.snapshot(img);
            return img;

        }
        else return moveCard.getImage();
    }



    /**
     * DragDetected
     * @param event
     */
    private void dragDetected(final MouseEvent event){

        //resetting the can stack variable
        canStack = false;

        cardsBelow = new ArrayList<ImageCard>();

        //if the card is comming rom the pick pile
        if(((ImageCard)event.getSource()).getStackNumber() == 8) {
            moveCard = (ImageCard)event.getSource();
            fromPane = pick;
        }

        //else it's coming from a stack
        else{
            ArrayList<ImageCard> src = stacks[((ImageCard)event.getSource()).getStackNumber()].getMoveAbleCards();

            if(src.size() != 0){
                ImageCard tempCard = null;
                for(int index = 0; index < src.size(); index++){
                    if(src.get(index).getCard().equals(((ImageCard) event.getSource()).getCard())){
                        tempCard = src.get(index);
                    }
                }
                //if the temp card can move it'll allow it
                if(tempCard != null){
                    fromPane = stacks[((ImageCard)event.getSource()).getStackNumber()];
                    moveCard = tempCard;
                    cardsBelow = fromPane.getCardsBelow(moveCard);
                }
            }
        }

        Dragboard db = startDragAndDrop(TransferMode.MOVE);
        startDragAndDrop(TransferMode.MOVE);


        db.setDragView(createImage());
        //db.setDragView(moveCard.getImage());



        //TODO DELETE
        ClipboardContent content = new ClipboardContent();
        content.putString(((ImageCard)event.getSource()).getCard().toString());
        db.setContent(content);

        event.consume();
    }

    /**
     * DRAGOVEREVENT
     * @param event
     */
    private void dragOver(final DragEvent event){
        //checking to see if the stack is empty or not
        CardPane toPane = (CardPane) event.getSource();

        canStack = false;

        //if it's a card stack
        if(toPane.getStackNumber() < 7){
            //checking if the stack is empty
            if (toPane.isEmpty()) {
                if (moveCard.getCard().getCardNum() == 13) {
                    canStack = true;
                    event.acceptTransferModes(TransferMode.MOVE);
                }
            } else {
                //get the current card it's comparing it
                ImageCard currentCard = (ImageCard) toPane.getLastChild();

                //checking if the moveCard can stack on the current card
                if (moveCard.getCard().isStackableOn(currentCard.getCard())) {
                    canStack = true;
                    event.acceptTransferModes(TransferMode.MOVE);
                }
            }
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
            toPane.addVertical(moveCard);
            moveCard.setStackNumber(toPane.getStackNumber());
            if(cardsBelow.size()!=0){
                for(int index = cardsBelow.size()-1; index >= 0; index--){
                    fromPane.remove(cardsBelow.get(index));
                    toPane.addVertical(cardsBelow.get(index));
                    cardsBelow.get(index).setStackNumber(toPane.getStackNumber());
                }
            }
        }
        event.setDropCompleted(true);
        event.consume();
    }

    /**
     *
     */
    private void setUpGrids() {
        //setting the size of the draw and pick field
        draw.setPrefSize(92.0,116.0);
        pick.setPrefSize(112.0,136.0);

        //top grids
        topGrid.addColumn(0, draw);
        topGrid.addColumn(1, pick);

        for (int index = 0; index < finalSpot.length; index++) {
            finalSpot[index] = new CardPane(index+9);
            finalSpot[index].setPrefSize(72.0,96.0);
            finalSpot[index].setId("FinalSpots");

            //adding the drag and drop listeners to the final spots
            //finalSpot[index].setOnDragOver((DragEvent event) -> dragOver(event));
            //finalSpot[index].setOnDragDropped((DragEvent event) -> dragDropped(event));

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

}
