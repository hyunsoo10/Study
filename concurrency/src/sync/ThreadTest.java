package sync;

public class ThreadTest extends Thread{
    PrintTest printTest;
    int number;

    public ThreadTest(PrintTest printTest, int number) {
        this.printTest = printTest;
        this.number = number;
    }

    @Override
    public void run() {
        printTest.print(number);
//        printTest.print2(number);
    }
}
