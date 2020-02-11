package vz.concurrent_search;

import java.util.List;
import java.util.concurrent.Callable;

public class SearchThread implements Callable<Integer> {
    private final int startIndex;
    private final int endIndex;
    private final int searchedElement;
    private final int replacingElement;
    private final List<Integer> elementsList;

    public SearchThread(int startIndex, int endIndex, int searchedElement, int replacingElement, List<Integer> list) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.searchedElement = searchedElement;
        this.replacingElement = replacingElement;
        this.elementsList = list;
    }

    @Override
    public Integer call() throws Exception {
        try {
            int count = 0;
            for (int i = startIndex; i < endIndex; i++) {
                if (elementsList.get(i) == searchedElement) {
                    elementsList.set(i, replacingElement);
                    count++;
                }
            }
            return count;
        } finally {

        }
    }
}
