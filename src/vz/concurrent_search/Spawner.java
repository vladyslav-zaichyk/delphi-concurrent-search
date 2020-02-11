package vz.concurrent_search;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Spawner implements Runnable {
    private final static int MIN_ELEMENT = 0;
    private final static int MAX_ELEMENT = 100;
    private final static int MIN_AMOUNT_OF_ELEMENTS = 10_000;
    private final static int MAX_AMOUNT_OF_ELEMENTS = 1_00_000;
    private final List<Integer> list;

    public Spawner(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        spawn(ThreadLocalRandom.current().nextInt(MIN_AMOUNT_OF_ELEMENTS, MAX_AMOUNT_OF_ELEMENTS));
    }

    public void spawn(int count) {
        long startTime = System.currentTimeMillis();
        System.out.printf("Spawning %s elements start\n", count);
        if (list.isEmpty()) {
            list.add(randomElement());
            count--;
        }
        while (count > 0) {
            list.add(ThreadLocalRandom.current().nextInt(0, list.size()), randomElement());
            count--;
        }
        long finishTime = System.currentTimeMillis();
        System.out.printf("Spawning end. Time: %s. List size: %s\n\n", finishTime - startTime, list.size());
    }

    private int randomElement() {
        return ThreadLocalRandom.current().nextInt(MIN_ELEMENT, MAX_ELEMENT + 1);
    }
}
