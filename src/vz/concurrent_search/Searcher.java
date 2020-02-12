package vz.concurrent_search;

import java.util.List;
import java.util.concurrent.*;

public class Searcher {
    private final int searchedElement;
    private final int replacingElement;
    private final int poolSize;
    private final List<Integer> elements;

    Searcher(int searchedElement, int replacingElement, int poolSize, List<Integer> elements) {
        this.searchedElement = searchedElement;
        this.replacingElement = replacingElement;
        this.poolSize = poolSize;
        this.elements = elements;
    }

    public int search() {
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        CompletionService<Integer> searchCompletionService = new ExecutorCompletionService<>(executor);
        int elementsPerThread = elements.size() / poolSize;

        for (int i = 0; i < poolSize; i++) {
            int startIndex = i * elementsPerThread;
            int endIndex = ((i + 1) * elementsPerThread) - 1;
            searchCompletionService.submit(new SearchThread(startIndex, endIndex, searchedElement, replacingElement, elements));
        }

        int result = 0;
        int receivedResults = 0;
        boolean error = false;

        while (receivedResults < poolSize && !error) {
            try {
                result += searchCompletionService.take().get();
                receivedResults++;
                System.out.printf("result: %s, received results: %s\n", result, receivedResults);
            } catch (InterruptedException | ExecutionException e) {
                error = true;
                e.printStackTrace();
            }
        }

        return result;
    }
}
