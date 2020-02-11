package vz.concurrent_search;

import java.util.List;

public class Searcher implements Runnable {
    private final int startIndex;
    private final int endIndex;
    private final int searchedElement;
    private final int replacingElement;
    private final List<Integer> list;

    public Searcher(int startIndex, int endIndex, int searchedElement, int replacingElement, List<Integer> list) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.searchedElement = searchedElement;
        this.replacingElement = replacingElement;
        this.list = list;
    }

    @Override
    public void run() {
        search();
    }

    private synchronized int search() {
        int count = 0;
        for (int i = startIndex; i < endIndex; i++) {
            if (list.get(i) == searchedElement) {
                list.set(i, replacingElement);
                count++;
            }
        }
        return count;
    }
}
