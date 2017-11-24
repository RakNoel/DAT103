package deck;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class wil represent the player threads
 * and will tro to take a deck.card as fast as it can.
 * and IF deck.deck is empty print its cards.
 *
 * @author RakNoel
 * @version 1.0
 * @since 23.11.17
 */
public class playerThread extends Thread {
    private ArrayList<card> hand = new ArrayList<>();
    private deck Deck = deck.getInstance();
    private static ArrayList<card> newdeck = new ArrayList<>();

    @Override
    public void run() {
        try {
            while (!Deck.isEmpty()) {
                hand.add(Deck.draw());
                Thread.sleep(10);
            }

            Collections.sort(hand);
            System.out.printf("Hand: %s Size: %d %n",hand, hand.size());
            newdeck.addAll(hand);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<card> getNewdeck() {
        return newdeck;
    }
}
