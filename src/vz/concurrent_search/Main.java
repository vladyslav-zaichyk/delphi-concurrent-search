package vz.concurrent_search;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main {
    private final static int MIN_ELEMENT = 0;
    private final static int MAX_ELEMENT = 100;
    private final static int MIN_AMOUNT_OF_ELEMENTS = 1_000;
    private final static int MAX_AMOUNT_OF_ELEMENTS = 10_000;

    private final static int SEARCHED_ELEMENT = 50;
    private final static int REPLACED_ELEMENT = 0;

    final int avaibleProccesors = Runtime.getRuntime().availableProcessors();

    private volatile List<Integer> elements = new CopyOnWriteArrayList<>();

    private final ScheduledExecutorService spawnerService = Executors.newSingleThreadScheduledExecutor();

    public Main() {
        // spawnerService.scheduleAtFixedRate(new Spawner(list), 0, 5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        new Main();
    }
}
