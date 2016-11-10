package Solitare;

import java.util.ArrayList;

/**
 * @author Greg
 * @version 1.0
 * Created by Greg on 10/1/2016.
 */
public class CardStack {

    private ArrayList<Card> stack;

    public CardStack(Card[] input){
        stack = new ArrayList<Card>(input.length);

        for(int index = 0; index<input.length; index++){
            stack.add(input[index]);
        }
    }

    public Card getCard(int index){
        return stack.get(index);
    }

    public int getLength(){
        return stack.size();
    }

    public String toString(){
        return "CardStack[" + stack + "]";
    }
}
