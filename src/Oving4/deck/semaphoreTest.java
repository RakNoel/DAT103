package deck;

/**
 * a main class to test semaphores to lock
 * the access to a critical region
 *
 * in this test I will implement a deck.deck of
 * cards as a singleton and try to let
 * four players (threads) take cards as
 * fast as they can. BUT only one at a time
 *
 * NOW if successful they will all have
 * 13 cards left and there will be NO
 * duplicates.
 *
 * @author RakNoel
 * @version 1.0
 * @since 23.11.17
 */
public class semaphoreTest {
    public static void main(String[] args) {
        Thread p1 = new playerThread();
        Thread p2 = new playerThread();
        Thread p3 = new playerThread();
        Thread p4 = new playerThread();

        p1.start();
        p2.start();
        p3.start();
        p4.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(playerThread.getNewdeck().size());
    }
}
