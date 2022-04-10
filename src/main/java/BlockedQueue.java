import java.util.LinkedList;
import java.util.Queue;

public class BlockedQueue {
    Queue<Runnable> queue = new LinkedList<>();
    Queue<Runnable> queue1 = new LinkedList<>();
    Queue<Runnable> queue2 = new LinkedList<>();
    Object monitor = new Object();

    public void add(Runnable task) {
        synchronized (monitor) {
            queue.add(task);

            monitor.notify();
        }
    }

    public Runnable task() {
        synchronized (monitor) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return queue.poll();
        }
    }


    public Runnable task1() {
        synchronized (monitor) {
            while (queue1.isEmpty()) {
                try {
                    queue1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return queue1.poll();
        }

    }
}
