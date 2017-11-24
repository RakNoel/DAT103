package deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Semaphore;

/**
 * This singleton class will create a deck.deck and
 * shuffle it.
 *
 * @author RakNoel
 * @version 1.0
 * @since 23.11.17
 */
public class deck {
    private static deck singleInstance = null;
    private static ArrayList<card> deck;

    private static Semaphore sem;

    private deck() {
        sem = new Semaphore(1, true); //Should rather be a reentrantLock!
        deck = new ArrayList<>();

        for (int i = 0; i < 4; i++)
            for (short j = 1; j <= 13; j++)
                deck.add(new card(j, Suit.toSuit(i)));

        Collections.shuffle(deck);
    }

    public static deck getInstance() {
        if (singleInstance == null) singleInstance = new deck();
        return singleInstance;
    }

    //KRITISK REGION
    public card draw() throws IllegalStateException {
        try {
            sem.acquire();
            if (deck.size() == 0) throw new IllegalStateException("deck.deck is empty");
            return deck.remove(0);
        } catch (InterruptedException e) {
            return null;
        } finally {
            sem.release();
        }
    }

    //KRITISK REGION
    public boolean isEmpty() {
        synchronized (sem){

        }

        try {
            sem.acquire();
            return deck.size() <= 0;
        } catch (InterruptedException e) {
            return true;
        } finally {
            sem.release();
        }
    }
}

class card implements Comparable<card> {
    private final short number;
    private final Suit suit;

    public card(short number, Suit suit) {
        assert (number > 0 && number <= 13);

        this.number = number;
        this.suit = suit;
    }

    public short getNumber() {
        return number;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public int compareTo(card card) {
        if (this.getSuit().toInt() != card.getSuit().toInt()) {
            return Integer.compare(this.getSuit().toInt(), card.getSuit().toInt());
        } else {
            return Integer.compare(this.getNumber(), card.getNumber());
        }
    }

    @Override
    public String toString() {
        return this.suit + " " + this.getNumber();
    }
}

enum Suit {
    HEART, DIAMOND, SPADE, CLOVER;

    public static Suit toSuit(int i) {
        if (i == 0) return HEART;
        else if (i == 1) return DIAMOND;
        else if (i == 2) return SPADE;
        else return CLOVER;
    }

    public int toInt() {
        if (this == HEART) return 0;
        else if (this == DIAMOND) return 1;
        else if (this == SPADE) return 2;
        else return 3;
    }
}