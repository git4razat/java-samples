package sample2026.walmart.autocomplete;

public class ProductScore implements Comparable<ProductScore> {
    String name;
    double score;

    ProductScore(String name, double score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(ProductScore other) {
        // Primary sort: Higher score comes first (descending)
        if (Double.compare(other.score, this.score) != 0) {
            return Double.compare(other.score, this.score);
        }
        // Secondary tie-breaker: Alphabetical order if scores are equal
        return this.name.compareTo(other.name);
    }
}
