package vz.concurrent_search.searcher;

import java.util.List;
import java.util.concurrent.Callable;

public class SearcherThread implements Callable<Integer> {
    private final int startIndex;
    private final int endIndex;
    private final int searchedItem;
    private final int replaceItem;
    private final List<Integer> items;

    SearcherThread(int startIndex, int endIndex, int searchedItem, int replaceItem, List<Integer> items) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.searchedItem = searchedItem;
        this.replaceItem = replaceItem;
        this.items = items;
    }

    @Override
    public Integer call() {
        int count = 0;
        for (int i = startIndex; i < endIndex; i++) {
            if (items.get(i) == searchedItem) {
                items.set(i, replaceItem);
                count++;
            }
        }
        return count;
    }
}
