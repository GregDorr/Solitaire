package Solitare;

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
    /**
     * Default Constructor that accepts no parameters.
     */
    public CardPane(){
        super();
        super.setAlignment(Pos.TOP_CENTER);
        numCards = 0;
    }

    /**
     * This method adds an node to the list and spaces it out
     * inorder for the cards to look like they are stacked up.
     * @param newNode to be added to the pane
     */
    public void add(Node newNode){
        super.getChildren().add(newNode);
        numCards++;
        StackPane.setMargin(newNode,new Insets((10*numCards),0,0,0));
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
}
