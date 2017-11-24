package readerWriter;

import java.util.concurrent.Semaphore;

/**
 * a random grid class that handles the
 * readers-writers problem
 *
 * @author RakNoel
 * @version 1.0
 * @since 24.11.17
 */
public class board {
    private static board singleInstance = null;
    private static Object[] grid;
    private static int width;

    private static Semaphore writer;
    private static Semaphore reader;
    private static int reading;

    private board(int w, int h) {
        writer = new Semaphore(1, true);
        reader = new Semaphore(1, true);
        grid = new Object[w * h];

        for (int i = 0; i < grid.length; i++)
            grid[i] = false;

        width = w;
        reading = 0;
    }

    public static board getSingleInstance(int x, int y) {
        if (singleInstance == null) singleInstance = new board(x, y);
        return singleInstance;
    }

    public static board getSingleInstance() {
        return singleInstance;
    }

    public int getWidth() {
        return width;
    }

    public void setBoard(Object[] o) {
        try {
            writer.acquire();
            grid = o;
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writer.release();
        }
    }

    public Object getTile(int x, int y) {
        try {
            reader.acquire();
            if (++reading == 1) {
                writer.acquire();
            }
            reader.release();

            Object o = grid[(y * width) + x];

            reader.acquire();
            if (--reading == 0) {
                writer.release();
            }
            reader.release();

            return o;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
