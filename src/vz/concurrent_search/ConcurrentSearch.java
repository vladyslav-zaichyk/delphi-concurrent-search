package vz.concurrent_search;

import vz.concurrent_search.listener.SizeChangedListenerThread;
import vz.concurrent_search.producer.ProducerService;
import vz.concurrent_search.searcher.SearcherService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentSearch {
    private final static int MIN_ITEM = 0;
    private final static int MAX_ITEM = 100;
    private final static int MIN_AMOUNT_OF_ITEMS = 1_000;
    private final static int MAX_AMOUNT_OF_ITEMS = 10_000;

    private final static int SEARCHED_ITEM = 50;
    private final static int REPLACE_ITEM = 0;

    private final int avaibleProccesors = Runtime.getRuntime().availableProcessors();
    private final ReadWriteLock locker = new ReentrantReadWriteLock();

    private List<Integer> items = new CopyOnWriteArrayList<>();

    public void start() {
        SizeChangedListenerThread listenerThread = new SizeChangedListenerThread(locker, items);
        SearcherService searcher = new SearcherService(locker, SEARCHED_ITEM, REPLACE_ITEM, avaibleProccesors, items);
        ProducerService producer = new ProducerService(locker, MIN_ITEM, MAX_ITEM, MIN_AMOUNT_OF_ITEMS, MAX_AMOUNT_OF_ITEMS, items);
        listenerThread.setListener(searcher::search);
        listenerThread.start();
        producer.startProducing();
    }
}
