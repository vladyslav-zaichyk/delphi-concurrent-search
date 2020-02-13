package vz.concurrent_search.listener;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;

public class SizeChangedListenerThread extends Thread {
    private final ReadWriteLock locker;
    private final List<Integer> items;
    private SizeChangedListener listener;
    private int oldSize;

    public SizeChangedListenerThread(ReadWriteLock locker, List<Integer> items) {
        this.locker = locker;
        this.items = items;
        oldSize = items.size();
    }

    public void setListener(SizeChangedListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        while (true) {
            int newSize = items.size();
            if (newSize != oldSize) {
                locker.readLock().lock();
                listener.onSizeChanged();
                locker.readLock().unlock();
            }
        }
    }
}
