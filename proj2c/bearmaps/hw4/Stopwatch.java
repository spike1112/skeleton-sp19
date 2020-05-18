package bearmaps.hw4;

public class Stopwatch {
    private final long start = System.currentTimeMillis();


    public Stopwatch() {
    }

    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (double)(now - this.start) / 1000.0D;
    }
}
