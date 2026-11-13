package sample2026.walmart.interval;

import java.util.concurrent.ConcurrentHashMap;

public class WarehouseDASS {

    private ConcurrentHashMap<String, Dock> docks = new ConcurrentHashMap<>();

    public void registerDock(String dockId) {
        if (dockId != null && !dockId.isEmpty()) {
            docks.putIfAbsent(dockId, new Dock(dockId));
        }
    }

    public boolean bookDock(String dockId, long start, long end) {
        validateSlot(start, end);
        Dock dock = docks.get(dockId);
        if (dock == null) throw new IllegalArgumentException("Dock not found : " + dockId);
        return dock.book(start, end);
    }

    public String bookAnyAvailableDock(long start, long end) {
        validateSlot(start, end);
        for (String dockId: docks.keySet()) {
            Dock dock = docks.get(dockId);
            if (dock.book(start, end)) {
                return dock.getDockId();
            }
        }
        return null; // all docks are occupied in this window
    }

    public boolean cancelAppointment(String dockId, long start, long end) {
        validateSlot(start, end);
        Dock dock = docks.get(dockId);
        if (dock == null) throw new IllegalArgumentException("Dock not found :: " + dockId);

        return dock.cancel(start, end);
    }

    public void validateSlot(long start, long end) {
        if (start >= end) throw new IllegalArgumentException("Please validate the interval");
    }

    // =========================================================================
    // Verification Main Method
    // =========================================================================
    public static void main(String[] args) {
        WarehouseDASS dass = new WarehouseDASS();
        dass.registerDock("Dock-A");
        dass.registerDock("Dock-B");

        long t1 = 1000L;
        long t2 = 2000L;

        System.out.println("Booking specific Dock-A [1000, 2000]: " + dass.bookDock("Dock-A", t1, t2)); // true
        System.out.println("Booking specific Dock-A (overlapping): " + dass.bookDock("Dock-A", 1500L, 2500L)); // false

        System.out.println("Booking any available dock [1500, 2500]: " + dass.bookAnyAvailableDock(1500L, 2500L)); // Dock-B
        System.out.println("Booking any available dock [1500, 2500] again: " + dass.bookAnyAvailableDock(1500L, 2500L)); // null (both full)
    }


}
