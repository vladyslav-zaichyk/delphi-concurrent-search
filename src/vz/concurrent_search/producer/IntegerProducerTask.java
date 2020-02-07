package vz.concurrent_search.producer;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntegerProducerTask implements Runnable {
    private final static int AMOUNT_OF_NUMBERS = 5_000;
    private final static int MAX_VALUE = 100;
    private final ReadWriteLock locker;
    private final List<Integer> numbersList;

    public IntegerProducerTask(ReadWriteLock locker, List<Integer> numbersList) {
        this.locker = locker;
        this.numbersList = numbersList;
    }

    @Override
    public void run() {
        produce(AMOUNT_OF_NUMBERS);
    }

    public void produce(int numbersToProduce) {
        locker.writeLock().lock();

        Random random = new Random();
        numbersList.addAll(Stream.generate(() -> random.nextInt(MAX_VALUE))
                .limit(numbersToProduce)
                .collect(Collectors.toList()));
        Collections.shuffle(numbersList);

        locker.writeLock().unlock();
    }
}
