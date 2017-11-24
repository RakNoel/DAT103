package boundedBuffer;

import java.util.PriorityQueue;

/**
 * TODO: Describe class
 *
 * @author RakNoel
 * @version 1.0
 * @since 23.11.17
 */
public class consumer extends Thread{

    private myQueue pq;
    private int counter;

    public consumer(myQueue pq){
        this.pq = pq;
        this.counter = 0;
    }

    @Override
    public void run() {
        while (true){
            try{
                System.out.printf("Process %s: %s %n", Thread.currentThread().getId(), pq.poll());
                counter++;
                if (counter > 100)
                    System.exit(0);
                sleep(200);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
