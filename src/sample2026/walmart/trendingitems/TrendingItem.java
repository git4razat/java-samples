package sample2026.walmart.trendingitems;

public class TrendingItem {

    private String itemId;

    private long frequency;

    public TrendingItem(String itemId, long frequency) {
        this.itemId = itemId;
        this.frequency = frequency;
    }

    public String getItemId() {
        return itemId;
    }

    public long getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return "Item{id='" + itemId + "', freq=" + frequency + "}";
    }
}
