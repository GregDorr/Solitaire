package Solitare;

import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.input.MouseEvent;

/**
 * @author Greg
 * @version 1.0
 * Created by Greg on 11/6/2016.
 */
public class PickCardListener implements EventHandler<MouseEvent>{

    private MainScene scene;

    public PickCardListener(MainScene scene){
        this.scene = scene;
    }

    @Override
    public void handle(MouseEvent event) {
         /* drag was detected, start a drag-and-drop gesture*/
        /* allow any transfer mode */
        int index = scene.getPick().size();
        ImageCard source = (ImageCard) scene.getPick().get(index-1);
        scene.tempCard = source;
        Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
        //the actual image being dragged
        scene.db = source.startDragAndDrop(TransferMode.MOVE);
        scene.db.setDragView(source.getImage());
        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();
        content.putString(source.getCard().toString());
        db.setContent(content);
        source.removeEventHandler(event.getEventType(),this);
        event.consume();
    }



}
