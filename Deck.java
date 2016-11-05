package sample;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Greg on 9/27/2016.
 */
public class Deck {

    private ArrayList<Card> deck;

    public Deck(){
        deck = new ArrayList<Card>(52);

        createDeck();
        shuffleDeck();
    }

    private void createDeck(){
        //creating the deck
        for(int index = 0; index < 13; index++){
            Card newHearts = new Card((index+1), "Hearts", "red",findCard((index+1),"H"));
            Card newSpades = new Card((index+1), "Spades", "black",findCard((index+1),"S"));
            Card newDiamonds = new Card((index+1),"Diamonds","red",findCard((index+1),"D"));
            Card newClubs = new Card((index+1),"Clubs", "black",findCard((index+1),"C"));

            deck.add(newHearts);
            deck.add(newSpades);
            deck.add(newDiamonds);
            deck.add(newClubs);
        }
    }

    //shuffles the deck
    public void shuffleDeck() {

        //Shuffles 7 times inorder for it to be random
        for(int index = 0; index < 7; index++){
            //using java Collections
            Collections.shuffle(deck);
        }

    }

    //method to re-add cards from discard pile
    public void reAdd(Card[] input) {
        for(int index = 0; index < input.length; index++)
        {
            deck.add(input[index]);
        }

        //shuffle the incoming deck
        shuffleDeck();
    }

    //deals the proper number of cards into a it's stack
    public Card[] deal(int stackNum){
        Card[] stack = new Card[stackNum];

        for(int index = 0; index < stackNum; index++){
            stack[index]=deck.remove(0);
        }

        return stack;
    }

    @Override
    public String toString() {
        return "Deck [deck=" + deck + "]";
    }

    private ImageView findCard(int num, String suit) {
        String path = "Cards/" + suit + new Integer(num).toString() + ".png";
        System.out.println(path);
        return new ImageView(path);
    }
}
