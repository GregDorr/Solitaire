package Solitare;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * @author Greg
 * @version 1.0
 * Created by Greg on 11/6/2016.
 */
public class DrawCardsListener implements EventHandler<MouseEvent> {

    private MainScene scene;

    public DrawCardsListener(MainScene scene){
        this.scene = scene;
    }

    @Override
    public void handle(MouseEvent event) {
        //getting the list of cards from the main scene
        ObservableList<Node> draw = scene.getDraw();
        ObservableList<Node> pick = scene.getPick();

        System.out.println("Draw size: " + draw.size() + " Pick size: " + pick.size());

        //TODO fix the restacking part
        //checking if theres just one card left
        //if there is, it'll readd the action listener
        if(draw.size() == 1){
            if(pick.size() != 0){
                ImageView v1 = (ImageView)event.getSource();
                v1.removeEventHandler(event.getEventType(),this);
                scene.resetUpDraw(pick);
            }
        }

        else {
            //casting event into which card
            ImageCard c1 = ((ImageCard) event.getSource());

            //removing from one adding to the other
            draw.remove(c1);
            pick.add(c1);

            //resetting the cards
            //scene.setDraw(draw);
            //scene.setPick(pick);

            //flipping the card face up
            c1.getCard().setFaceUp(true);
            c1.setImage(c1.getCard().getCardFace());

            //remove the action listener
            c1.removeEventHandler(event.getEventType(), this);

            //TODO add pick action listener
            c1.setOnDragDetected(new PickCardListener(scene));

        }
    }
}
