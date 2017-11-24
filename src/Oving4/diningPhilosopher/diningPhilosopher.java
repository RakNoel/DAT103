package diningPhilosopher;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Class to handle an example of
 * the dining-philosophers problem
 *
 * @author RakNoel
 * @version 1.0
 * @since 24.11.17
 */
public class diningPhilosopher {
    public static void main(String[] args) {
        Object[] chopsticks = new Object[5];

        for (int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new Object();
        }

        Thread ph1 = new philosopher(chopsticks[0], chopsticks[1]);
        Thread ph2 = new philosopher(chopsticks[1], chopsticks[2]);
        Thread ph3 = new philosopher(chopsticks[2], chopsticks[3]);
        Thread ph4 = new philosopher(chopsticks[3], chopsticks[4]);
        Thread ph5 = new philosopher(chopsticks[4], chopsticks[0]);

        ph1.start();
        ph2.start();
        ph3.start();
        ph4.start();
        ph5.start();
    }
}


/**
 * CLass to represent a philosopher which has two
 * chopsticks
 *
 * if can and will not use a chopstick if it is
 * alreaddy in use by another process due to
 * syncronized(){}
 */
class philosopher extends Thread {

    private final Object leftStick;
    private final Object rightStick;
    private static Semaphore sem;

    public philosopher(Object l, Object r) {
        leftStick = l;
        rightStick = r;
        sem = new Semaphore(2);
    }

    @Override
    public void run() {
        Random rnd = new Random();
        while (true) {
            try {
                sleep(rnd.nextInt(1000)); //Think

                sem.acquire();
                synchronized (leftStick) {
                    System.out.printf("%s picked up left %n", Thread.currentThread().getId());

                    sleep(rnd.nextInt(1000));
                    synchronized (rightStick) {
                        System.out.printf("%s is now eating %n", Thread.currentThread().getId());

                        sleep(rnd.nextInt(1000)); //Eat
                    }
                }
                sem.release();

                System.out.printf("%s is now back to thinking %n", Thread.currentThread().getId());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}