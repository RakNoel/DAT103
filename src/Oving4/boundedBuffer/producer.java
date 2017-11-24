package boundedBuffer;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Class that will produce random strings
 * and try to fit them on the queue if
 * when there is space
 *
 * @author RakNoel
 * @version 1.0
 * @since 23.11.17
 */
public class producer extends Thread {

    private myQueue s;

    public producer(myQueue s) {
        this.s = s;
    }

    @Override
    public void run() {
        Random rnd = new SecureRandom();

        while (true) {

            String x = "";
            for (int i = 0; i < 10; i++)
                x += rnd.nextInt();

            s.add(x);

            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
