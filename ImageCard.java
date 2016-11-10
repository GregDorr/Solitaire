package Solitare;

import javafx.scene.image.ImageView;

/**
 * @author Greg
 * @version 1.0
 * Created by Greg on 11/6/2016.
 */
public class ImageCard extends ImageView {

    private Card card;

    /**
     * Constructor that accepts 2 parameters
     * @param image of the card
     * @param card the actual card
     */
    public ImageCard(ImageView image, Card card){
        super(image.getImage());
        this.card = card;
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

    /**
     *
     * @return a string version of this object
     */
    public String toString(){
        return "ImageCard[" + getCard().toString() + "]";
    }
}
