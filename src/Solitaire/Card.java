package Solitaire;

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
     * Constructor that takes in 4 arguments
     * @param num the number of the card
     * @param suit the suit of the card
     * @param color the color of the card
     * @param cardFace the image of the face
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
    public boolean isStackableOn(Card c2){
        //if card 2 is less than card 1
        if((this.getCardNum()-c2.getCardNum()) == -1){
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

    /**
     * @param c2 another card to compare against
     * @return true if the cards are equal. false if they are not.
     */
    public boolean equals(Card c2){
        if(this.cardNum == c2.getCardNum()){
            if(this.suit == c2.getSuit())
                return true;
        }
        return false;
    }

    /**
     *
     * @return The number of the card
     */
    public int getCardNum() {
        return cardNum;
    }

    /**
     *
     * @return The suit of the card
     */
    public String getSuit() {
        return suit;
    }

    /**
     *
     * @return true if the card is face up. false if face down
     */
    public boolean isFaceUp() {
        return faceUp;
    }

    /**
     * Sets the card face up or face down.
     * @param faceUp true if the card is face up, false if it's face down
     */
    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    /**
     *
     * @return The image of the cards face
     */
    public ImageView getCardFace() {
        return cardFace;
    }

    /**
     *
     * @return The image of the back of the card
     */
    public ImageView getCardBack() {return cardBack;}

    /**
     * This method returns red if it's a diamond or hearts
     * and returns black if it's a spades or clubs.
     * @return The color of the card
     */
    public String getColor(){ return color; }
}
