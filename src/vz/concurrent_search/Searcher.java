package vz.concurrent_search;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Searcher {
    private final int searchedElement;
    private final int replacingElement;
    private final int poolSize;
    private final List<Integer> elementsList;

    public Searcher(int searchedElement, int replacingElement, int poolSize, List<Integer> elementsList) {
        this.searchedElement = searchedElement;
        this.replacingElement = replacingElement;
        this.poolSize = poolSize;
        this.elementsList = elementsList;
    }

    public int search() {
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        List<Future<Integer>> futureCounts = new ArrayList<>(poolSize);
        int elementsPerThread = elementsList.size() / poolSize;

        for (int i = 0; i < poolSize; i++) {
            int startIndex = i * elementsPerThread;
            int endIndex = (i + 1) * elementsPerThread;
            futureCounts.add(executor.submit(new SearchThread(startIndex, endIndex, searchedElement, replacingElement, elementsList)));
        }

        return futureCounts.stream().mapToInt(future -> {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return 0;
        }).sum();
    }

}
