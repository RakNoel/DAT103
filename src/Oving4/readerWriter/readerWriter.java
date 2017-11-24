package readerWriter;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

/**
 * Suppose we have a table on which a
 * scabble game is at hand. We might want
 * to check the board for an update simultaniusly
 * BUT someone might also play their turn
 *
 * @author RakNoel
 * @version 1.0
 * @since 24.11.17
 */
public class readerWriter {
    public static void main(String[] args) {
        board b = board.getSingleInstance(20, 20);

        ArrayList<player> ps = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            ps.add(new player(b));
            ps.get(i).start();
        }
    }
}

class player extends Thread {
    board b;

    public player(board b) {
        this.b = b;
    }

    @Override
    public void run() {
        while (true) {
            Random rnd = new SecureRandom();

            if (rnd.nextInt(1000) == 999) {
                System.out.println("LUCKY");
                Object[] holder = new Object[b.getWidth() * b.getWidth()];
                for (int i = 0; i < holder.length; i++)
                    holder[i] = rnd.nextBoolean();

                b.setBoard(holder);
            } else {
                System.out.printf("Thread %s: %s %n",Thread.currentThread().getId(),b.getTile(rnd.nextInt(20), rnd.nextInt(20)));
            }
        }
    }
}
