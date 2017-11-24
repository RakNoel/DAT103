package boundedBuffer;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * singleton queue that will only
 * let one thread do one thing at a time
 *
 * @author RakNoel
 * @version 1.0
 * @since 24.11.17
 */
public class myQueue {
    private static myQueue singleInstace = null;
    private static Queue<String> pq;
    private static final int maxLength = 10;
    private static Semaphore sem;
    private static Semaphore spaces;
    private static Semaphore ready;

    private myQueue() {
        sem = new Semaphore(1, true);
        ready = new Semaphore(0, true);
        spaces = new Semaphore(maxLength, true);
        pq = new LinkedList<>();
    }

    public static myQueue getInstance() {
        if (singleInstace == null) singleInstace = new myQueue();
        return singleInstace;
    }

    public void add(String t) {
        try {
            spaces.acquire();
            sem.acquire();
            assert (maxLength > pq.size());
            pq.add(t);
            ready.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sem.release();
        }
    }

    public String poll() throws EmptyStackException {
        try {
            ready.acquire();
            sem.acquire();
            return pq.poll();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            sem.release();
            spaces.release();
        }
    }
}
