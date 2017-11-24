package boundedBuffer;

/**
 * TODO: Describe class
 *
 * @author RakNoel
 * @version 1.0
 * @since 23.11.17
 */
public class boundedBuffer {
    public static void main(String[] args) {
        myQueue mq = myQueue.getInstance();

        Thread p1 = new producer(mq);
        Thread p2 = new producer(mq);

        Thread c1 = new consumer(mq);
        Thread c2 = new consumer(mq);
        Thread c3 = new consumer(mq);

        p1.start();
        p2.start();

        c1.start();
        c2.start();
        c3.start();
    }
}
