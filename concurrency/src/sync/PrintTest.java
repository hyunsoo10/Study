package sync;

public class PrintTest {
    public synchronized void print(int number) {
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread = " + Thread.currentThread().getName() + " value : " + number+i);
        }
    }

    public void print2(int number) {
        synchronized (this) {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());

        }
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread = " + Thread.currentThread().getName() + " value : " + number+i);
        }
    }
}
