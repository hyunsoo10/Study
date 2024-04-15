import atom.Counter;
import queue.Producer;
import sync.PrintTest;
import sync.ThreadTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {

//         sync
        PrintTest printTest = new PrintTest();

        ThreadTest thread1 = new ThreadTest(printTest, 10);
        ThreadTest thread2 = new ThreadTest(printTest, 20);
        ThreadTest thread3 = new ThreadTest(printTest, 30);
        ThreadTest thread4 = new ThreadTest(printTest, 40);
//        ThreadTest thread3 = new ThreadTest(new PrintTest(), 30);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
//        thread3.start();

//        try {
//            thread1.start();
//            thread1.join();
//            thread2.start();
//            thread2.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

//        블로킹 큐
//        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);
//
//        Producer producer = new Producer(queue);
//        Consumer consumer = new Consumer(queue);
//
//        new Thread(producer).start();
//        new Thread(consumer).start();

//        //atomic
//        Counter counter = new Counter();
//
//        Thread thread1 = new Thread(() -> {
//            for (int i = 0; i < 1000; i++) {
//                counter.getAtomNumber().incrementAndGet();
//                counter.setNumber(counter.getNumber()+1);
//            }
//        });
//
//        Thread thread2 = new Thread(() -> {
//            for (int i = 0; i < 1000; i++) {
//                counter.getAtomNumber().incrementAndGet();
//                counter.setNumber(counter.getNumber()+1);
//            }
//        });
//        // 스레드 시작
//        thread1.start();
//        thread2.start();
//
//        System.out.println("counter.getAtomNumber() = " + counter.getAtomNumber());
//        System.out.println("counter.getNumber() = " + counter.getNumber());

    }
}