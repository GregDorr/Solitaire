package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 * Created by Greg on 10/11/2016.
 */
public class CardPane extends StackPane {

    private int numCards;

    public CardPane(){
        super();
        super.setAlignment(Pos.TOP_CENTER);
        numCards = 0;
    }

    public void add(Node newNode){
        super.getChildren().add(newNode);
        numCards++;
        StackPane.setMargin(newNode,new Insets((10*numCards),0,0,0));
        super.layoutChildren();
    }

    public void remove(Node newNode){
        super.getChildren().remove(newNode);
        numCards--;
    }




}
