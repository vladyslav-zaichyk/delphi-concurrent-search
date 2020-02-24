package vz.concurrent_search.searcher;

import java.util.List;
import java.util.concurrent.Callable;

public class FindAndReplaceTask<T> implements Callable<Long> {
    private final T searched;
    private final T replacer;
    private final List<T> list;

    FindAndReplaceTask(T searched, T replacer, List<T> list) {
        this.searched = searched;
        this.replacer = replacer;
        this.list = list;
    }

    @Override
    public Long call() {
        return list.stream()
                .filter(value -> value.equals(searched))
                .map(value -> value = replacer)
                .count();
    }
}
