package vz.concurrent_search;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    final int avaibleProccesors = Runtime.getRuntime().availableProcessors();
    private final ScheduledExecutorService spawnerService = Executors.newSingleThreadScheduledExecutor();
    private List<Integer> list = new ArrayList<>();

    public Main() {
        spawnerService.scheduleAtFixedRate(new Spawner(list), 0, 5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        new Main();
    }
}
