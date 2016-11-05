package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Greg on 9/27/2016.
 */
public class Card implements Comparable<Card> {
    private int cardNum;
    private String suit;
    private String color;
    private boolean faceUp;
    private ImageView cardFace;
    private ImageView cardBack;

    //default constructor
    public Card(){
        cardNum = 0;
        suit = null;
        color = null;
        faceUp = false;
        cardFace = null;
        cardBack = null;
    }

    //constructor that doesn't accept images
    public Card(int num, String suit, String color) {
        cardNum = num;
        this.suit = suit;
        this.color = color;
        cardFace = null;
        cardBack = null;
    }

    //Constructor that accepts all aruments
    public Card(int num, String suit, String color, ImageView cardFace){
        cardNum = num;
        this.suit = suit;
        this.color = color;
        this.cardFace = cardFace;
        this.cardBack = new ImageView("Cards/b1fv.png");
        faceUp = false;

    }

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

    public ImageView getCardBack() {
        return cardBack;
    }

    public void setCardBack(ImageView cardBack) {
        this.cardBack = cardBack;
    }

    public String getColor(){ return color; }

    public void setColor(String color){ this.color = color;}

    public String toString(){
        return ("Card[Number: " + cardNum + ", Suit: " + suit + "]");
    }


    @Override
    public int compareTo(Card c2) {

        return 0;
    }

    public boolean equals(Card c2) {
        //the suit and number need to be the same
        if(suit.equals(c2.getSuit())) {
            if (cardNum == c2.getCardNum())
                return true;
        }
        //default return
        return false;
    }
}
