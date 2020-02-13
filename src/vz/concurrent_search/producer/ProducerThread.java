package vz.concurrent_search.producer;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReadWriteLock;

public class ProducerThread implements Runnable {
    private final ReadWriteLock locker;
    private final int minItem;
    private final int maxItem;
    private final int minAmountOfItems;
    private final int maxAmountOfItems;
    private final List<Integer> items;

    ProducerThread(ReadWriteLock locker, int minItem, int maxItem, int minAmountOfItems, int maxAmountOfItems, List<Integer> items) {
        this.locker = locker;
        this.minItem = minItem;
        this.maxItem = maxItem;
        this.minAmountOfItems = minAmountOfItems;
        this.maxAmountOfItems = maxAmountOfItems;
        this.items = items;
    }

    @Override
    public void run() {
        produce(ThreadLocalRandom.current().nextInt(minAmountOfItems, maxAmountOfItems));
    }

    public void produce(int count) {
        locker.writeLock().lock();
        if (items.isEmpty()) {
            items.add(generateRandomElement());
            count--;
        }
        while (count > 0) {
            items.add(ThreadLocalRandom.current().nextInt(0, items.size()), generateRandomElement());
            count--;
        }
        locker.writeLock().lock();
    }

    private int generateRandomElement() {
        return ThreadLocalRandom.current().nextInt(minItem, maxItem + 1);
    }
}
