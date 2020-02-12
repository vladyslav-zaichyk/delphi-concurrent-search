package vz.concurrent_search;

import java.util.List;
import java.util.concurrent.Callable;

public class SearchThread implements Callable<Integer> {
    private final int startIndex;
    private final int endIndex;
    private final int searchedElement;
    private final int replacingElement;
    private final List<Integer> elements;

    SearchThread(int startIndex, int endIndex, int searchedElement, int replacingElement, List<Integer> list) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.searchedElement = searchedElement;
        this.replacingElement = replacingElement;
        this.elements = list;
    }

    @Override
    public Integer call() {
        System.out.printf("%s started\n", Thread.currentThread().getName());
        int count = 0;
        for (int i = startIndex; i < endIndex; i++) {
            if (elements.get(i) == searchedElement) {
                elements.set(i, replacingElement);
                count++;
            }
        }
        return count;
    }
}
