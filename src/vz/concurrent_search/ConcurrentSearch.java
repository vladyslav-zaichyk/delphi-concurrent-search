package vz.concurrent_search;

import vz.concurrent_search.listener.ListSizeChangeListenerThread;
import vz.concurrent_search.producer.IntegerProducerTask;
import vz.concurrent_search.searcher.ConcurrentFindAndReplaceService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentSearch implements Runnable {
    private final static int SEARCHED_NUMBER = 50;
    private final static int REPLACE_NUMBER = 0;

    private final int poolSize = Runtime.getRuntime().availableProcessors();
    private final ReadWriteLock locker = new ReentrantReadWriteLock();
    private List<Integer> numbersList = new ArrayList<>();

    @Override
    public void run() {
        ScheduledExecutorService producerExecutorService = Executors.newSingleThreadScheduledExecutor();
        producerExecutorService.scheduleAtFixedRate(new IntegerProducerTask(locker, numbersList), 0, 5, TimeUnit.SECONDS);

        ConcurrentFindAndReplaceService<Integer> searcher = new ConcurrentFindAndReplaceService<>(locker);

        ListSizeChangeListenerThread listenerThread = new ListSizeChangeListenerThread(locker, numbersList,
                () -> searcher.findAndReplace(SEARCHED_NUMBER, REPLACE_NUMBER, numbersList, poolSize));
        listenerThread.start();
    }
}
