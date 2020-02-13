package vz.concurrent_search.producer;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

public class ProducerService {
    private final ReadWriteLock locker;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private int minItems;
    private int maxItems;
    private int minAmountOfItems;
    private int maxAmountOfItems;
    private List<Integer> items;

    public ProducerService(ReadWriteLock locker, int minItems, int maxItems, int minAmountOfItems, int maxAmountOfItems, List<Integer> items) {
        this.locker = locker;
        this.minItems = minItems;
        this.maxItems = maxItems;
        this.minAmountOfItems = minAmountOfItems;
        this.maxAmountOfItems = maxAmountOfItems;
        this.items = items;
    }

    public void startProducing() {
        executorService.scheduleAtFixedRate(new ProducerThread(locker, minItems, maxItems, minAmountOfItems, maxAmountOfItems, items),
                0, 5, TimeUnit.SECONDS);
    }
}
