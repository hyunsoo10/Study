package queue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    protected BlockingQueue<Integer> queue = null;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                queue.take(); // 아이템을 큐에서 꺼냄
                System.out.println("Consumed: " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}