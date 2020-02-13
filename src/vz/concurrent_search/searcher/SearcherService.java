package vz.concurrent_search.searcher;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReadWriteLock;

public class SearcherService {
    private final int searchedItem;
    private final int replaceItem;
    private final int poolSize;
    private final List<Integer> items;

    public SearcherService(ReadWriteLock locker, int searchedItem, int replaceItem, int poolSize, List<Integer> items) {
        this.searchedItem = searchedItem;
        this.replaceItem = replaceItem;
        this.poolSize = poolSize;
        this.items = items;
    }

    public int search() {
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        CompletionService<Integer> searchCompletionService = new ExecutorCompletionService<>(executor);
        int elementsPerThread = items.size() / poolSize;

        for (int i = 0; i < poolSize; i++) {
            int startIndex = i * elementsPerThread;
            int endIndex = ((i + 1) * elementsPerThread) - 1;
            searchCompletionService.submit(new SearcherThread(startIndex, endIndex, searchedItem, replaceItem, items));
        }

        int result = 0;
        int receivedResults = 0;
        boolean error = false;

        while (receivedResults < poolSize && !error) {
            try {
                result += searchCompletionService.take().get();
                receivedResults++;
            } catch (InterruptedException | ExecutionException e) {
                error = true;
                e.printStackTrace();
            }
        }
        executor.shutdown();
        return result;
    }
}
