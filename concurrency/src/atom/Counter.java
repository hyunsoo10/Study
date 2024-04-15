package atom;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private int number;
    private AtomicInteger atomNumber = new AtomicInteger(0);

    public int getNumber() {
        return number;
    }

    public AtomicInteger getAtomNumber() {
        return atomNumber;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setAtomNumber(AtomicInteger atomNumber) {
        this.atomNumber = atomNumber;
    }
}
