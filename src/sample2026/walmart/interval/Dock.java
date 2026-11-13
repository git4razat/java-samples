package sample2026.walmart.interval;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Dock {

    public String getDockId() {
        return dockId;
    }

    private String dockId;
    private TreeMap<Long, Long> bookedSlots;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public Dock(String dockId) {
        this.dockId = dockId;
        bookedSlots = new TreeMap<>();
    }

    public boolean book(long start, long end) {
        lock.writeLock().lock();
        try {
            Map.Entry prevStartEntry = bookedSlots.floorEntry(start);
            if (prevStartEntry != null && (long)prevStartEntry.getValue() > start) {
                return false;
            }

            Map.Entry nextStartEntry = bookedSlots.ceilingEntry(start);
            if (nextStartEntry != null && (long)nextStartEntry.getKey() < end) {
                return false;
            }

            bookedSlots.put(start, end);
            return true;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public boolean cancel(long start, long end) {
       lock.writeLock().lock();
       try {
           return bookedSlots.remove(start, end);
       } finally {
           lock.writeLock().unlock();
       }
    }
}
