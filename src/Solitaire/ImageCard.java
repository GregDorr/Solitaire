package Solitaire;

import javafx.scene.image.ImageView;

/**
 * @author Greg
 * @version 1.0
 * Created by Greg on 11/6/2016.
 */
public class ImageCard extends ImageView {

    private Card card;
    private int stackNumber;

    /**
     * Constructor that accepts 2 parameters
     * @param image of the card
     * @param card the actual card
     */
    public ImageCard(ImageView image, Card card, int stackNumber){
        super(image.getImage());
        this.card = card;
        this.stackNumber = stackNumber;
    }

    /**
     * Setter: sets the Image of the card (depending if it's face up or down)
     * @param image to be the cards image
     */
    public void setImage(ImageView image){
        super.setImage(image.getImage());
    }

    /**
     * returns the card object
     * @return the card object
     */
    public Card getCard() {
        return card;
    }

    public int getStackNumber(){return stackNumber;}
    public void setStackNumber(int num){ stackNumber = num;}


    public String toString(){
        return "ImageCard[" + getCard().toString() + "]";
    }
}
