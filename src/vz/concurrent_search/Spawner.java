package vz.concurrent_search;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Spawner implements Runnable {
    private final int minElement;
    private final int maxElement;
    private final int minAmountOfElements;
    private final int maxAmountOfElements;
    private final List<Integer> elements;

    Spawner(int minElement, int maxElement, int minAmountOfElements, int maxAmountOfElements, List<Integer> elements) {
        this.minElement = minElement;
        this.maxElement = maxElement;
        this.minAmountOfElements = minAmountOfElements;
        this.maxAmountOfElements = maxAmountOfElements;
        this.elements = elements;
    }

    @Override
    public void run() {
        spawn(ThreadLocalRandom.current().nextInt(minAmountOfElements, maxAmountOfElements));
    }

    public void spawn(int count) {
        long startTime = System.currentTimeMillis();
        System.out.printf("%s spawning %s elements start\n", Thread.currentThread().getName(), count);
        if (elements.isEmpty()) {
            elements.add(randomElement());
            count--;
        }
        while (count > 0) {
            elements.add(ThreadLocalRandom.current().nextInt(0, elements.size()), randomElement());
            count--;
        }
        long finishTime = System.currentTimeMillis();
        System.out.printf("Spawning end. Time: %s. List size: %s\n\n", finishTime - startTime, elements.size());
    }

    private int randomElement() {
        return ThreadLocalRandom.current().nextInt(minElement, maxElement + 1);
    }
}
