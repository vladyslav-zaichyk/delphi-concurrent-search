package vz.concurrent_search.listener;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;

public class ListSizeChangeListenerThread extends Thread {
    private final ReadWriteLock locker;
    private final List<?> list;
    private final Runnable onSizeChangeAction;
    private int oldSize;

    public ListSizeChangeListenerThread(ReadWriteLock locker, List<?> list, Runnable onSizeChangeAction) {
        this.locker = locker;
        this.list = list;
        this.onSizeChangeAction = onSizeChangeAction;
        oldSize = list.size();
    }

    @Override
    public void run() {
        while (true) {
            locker.readLock().lock();

            if (isSizeChanged()) {
                onSizeChangeAction.run();
                oldSize = list.size();
            }

            locker.readLock().unlock();
        }
    }

    private boolean isSizeChanged() {
        return list.size() != oldSize;
    }
}
