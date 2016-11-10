package Solitare;

import javafx.scene.image.ImageView;

/**
 * @author Greg
 * @version 1.0
 * Created on 9/27/2016.
 *
 * This class represents each individual card.
 */
public class Card {
    private int cardNum;
    private String suit;
    private String color;
    private boolean faceUp;             //if the card is move able or not
    private ImageView cardFace;
    private final ImageView cardBack;

    /**
     * Default Constructor that accepts no parameters
     */
    public Card(){
        cardNum = 0;
        suit = null;
        color = null;
        faceUp = false;
        cardFace = null;
        cardBack = null;
    }

    /**
     * Constructor that takes in 4 arguments
     * @param num -- the number of the card
     * @param suit -- the suit of the card
     * @param color -- the color of the card
     * @param cardFace -- the image of the face
     */
    public Card(int num, String suit, String color, ImageView cardFace){
        cardNum = num;
        this.suit = suit;
        this.color = color;
        this.cardFace = cardFace;
        this.cardBack = new ImageView("Cards/b1fv.png");
        faceUp = false;
    }

    /**
     * Checks to see weither a card can stack ontop of another
     *
     * @param c2 a second card to compare
     * @return true if the card can stack on c2. otherwise return false
     */
    public boolean isStackable(Card c2){
        //if card 2 is less than card 1
        if((c2.getCardNum()-this.getCardNum()) == -1){
            //if the colors aren't equal
            if(!(c2.getColor().equals(this.getColor()))){
                return true;
            }
            else return false;
        }
        else return false;
    }

    /**
     * returns a string version of the object
     * @return String version of the object
     */
    public String toString(){
        return ("Card[Number: " + cardNum + ", Suit: " + suit + "]");
    }

    //SETTERS AND GETTERS
    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    public ImageView getCardFace() {
        return cardFace;
    }

    public void setCardFace(ImageView cardFace) {
        this.cardFace = cardFace;
    }

    public ImageView getCardBack() {return cardBack;}

    public String getColor(){ return color; }

    public void setColor(String color){ this.color = color;}
}
