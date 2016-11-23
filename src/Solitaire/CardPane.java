package Solitaire;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

/**
 * @author Greg
 * @version 1.0
 * 10/11/2016.
 *
 * This class extends StackPane inorder to make it easier to layout cards ontop of each other.
 * This class lays out the cards so they are covering most of the card behind it.
 *
 */
public class CardPane extends StackPane {

    //keep track of the number of cards
    private int numCards;
    private final int stackNumber;
    private int counter;

    /**
     * Default Constructor that accepts no parameters.
     */
    public CardPane(int stackNumber){
        super();
        super.setAlignment(Pos.TOP_CENTER);
        numCards = 0;
        counter = 0;
        this.stackNumber = stackNumber;
    }

    /**
     * This method adds an node to the list and spaces it out
     * inorder for the cards to look like they are stacked up.
     * @param newNode to be added to the pane
     */
    public void add(Node newNode){
        super.getChildren().add(newNode);
        numCards++;
        super.layoutChildren();
    }

    /**
     *
     * @param newNode
     */
    public void addHorizontal(Node newNode){
        numCards++;
        super.getChildren().add(newNode);
        StackPane.setMargin(newNode, new Insets(0, -(20*counter + (5*counter)), 0, 0));
        super.layoutChildren();

        counter++;

        if(counter == 3)
            counter = 0;

    }

    /**
     *
     * @param newNode
     */
    public void addVertical(Node newNode){
        //clearing any constraints that might be on it
        StackPane.clearConstraints(newNode);
        numCards++;
        super.getChildren().add(newNode);
        StackPane.setMargin(newNode,new Insets((10*numCards + (5*numCards)),0,0,0));
        super.layoutChildren();
    }

    /**
     * This Method removes a node from the list.
     * @param newNode to be removed from the list
     */
    public void remove(Node newNode){
        super.getChildren().remove(newNode);
        numCards--;
        super.layoutChildren();
    }

    /**
     *
     * @return the card on top of the pile.
     */
    public Node getLastChild(){
        return super.getChildren().get(numCards-1);
    }

    /**Finds which card can be planned
     *@return A list of the cards
     */
    public ArrayList<ImageCard> getMoveAbleCards(){
        ArrayList<ImageCard> temp = new ArrayList<ImageCard>();
        for(int index = super.getChildren().size()-1; index >= 0; index--){
            ImageCard c1 = ((ImageCard)super.getChildren().get(index));
            if(c1.getCard().isFaceUp())
                temp.add(c1);
        }
        return temp;
    }

    /**
     *
     * @return a boolean if it's empty
     */
    public boolean isEmpty(){
        return super.getChildren().isEmpty();
    }

    /**
     * @return StackNumber
     */
    public int getStackNumber(){
        return stackNumber;
    }

    /**
     * @return the number of cards in the stack
     */
    public int getNumCards(){return numCards;}

    /**
     *
     */
    public ArrayList<ImageCard> getCardsBelow(ImageCard c1){
        ArrayList<ImageCard> temp = getMoveAbleCards();
        int index = temp.indexOf(c1);

        ArrayList<ImageCard>returnCards = new ArrayList<ImageCard>();

        if(index == temp.size())
            return null;
        else{

            for(int i = 0; i < index; i++){
                returnCards.add(temp.get(i));
            }
            return returnCards;
        }
    }


}
