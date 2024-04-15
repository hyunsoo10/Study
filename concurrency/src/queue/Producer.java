package queue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    protected BlockingQueue<Integer> queue = null;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                queue.put(i); // 아이템을 큐에 넣음
                System.out.println("Produced: " + i);
                Thread.sleep(100); // 생산자는 1초마다 생산
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}