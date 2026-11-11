package sample2026.walmart.autocomplete;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeScoredAutocompleteSystem {

    private static class TrieNode {
        private final Map<Character, TrieNode> children = new ConcurrentHashMap<>();
        private final List<ProductScore> topSuggestions = new ArrayList<>();
        private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        /**
         * Thread-safe method to update pre-computed suggestions at this node.
         */
        void updateSuggestions(String productName, double score) {
            lock.writeLock().lock();
            try {
                // Remove existing entry if product already exists with old score
                topSuggestions.removeIf(p -> p.name.equals(productName));

                // Add new product score and re-sort
                topSuggestions.add(new ProductScore(productName, score));
                Collections.sort(topSuggestions);

                // Bound the list size to top 3
                if (topSuggestions.size() > 3) {
                    topSuggestions.remove(topSuggestions.size() - 1);
                }
            } finally {
                lock.writeLock().unlock();
            }
        }

        /**
         * Returns a thread-safe defensive copy of current top suggestions.
         */
        List<ProductScore> getTopSuggestionsSnapshot() {
            lock.readLock().lock();
            try {
                return new ArrayList<>(topSuggestions);
            } finally {
                lock.readLock().unlock();
            }
        }
    }

    private final TrieNode root = new TrieNode();

    /**
     * Initializes the autocomplete system with a product catalog map (Product Name -> Relevance Score).
     */
    public ThreadSafeScoredAutocompleteSystem(Map<String, Double> initialCatalog) {
        if (initialCatalog != null) {
            for (Map.Entry<String, Double> entry : initialCatalog.entrySet()) {
                insert(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Thread-safe insertion or score update for a product.
     */
    public void insert(String productName, double score) {
        if (productName == null || productName.isEmpty()) {
            return;
        }

        TrieNode curr = root;
        for (char ch : productName.toCharArray()) {
            curr.children.putIfAbsent(ch, new TrieNode());
            curr = curr.children.get(ch);
            curr.updateSuggestions(productName, score);
        }
    }

    /**
     * Retrieves top 3 scored suggestions for a given prefix in O(P) time.
     * Fully thread-safe for concurrent read traffic.
     */
    public List<String> getSuggestions(String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            return Collections.emptyList();
        }

        TrieNode curr = root;
        for (char ch : prefix.toCharArray()) {
            TrieNode nextNode = curr.children.get(ch);
            if (nextNode == null) {
                return Collections.emptyList(); // Prefix path not found
            }
            curr = nextNode;
        }

        // Retrieve thread-safe snapshot and map back to string names
        List<ProductScore> snapshot = curr.getTopSuggestionsSnapshot();
        List<String> resultNames = new ArrayList<>();
        for (ProductScore ps : snapshot) {
            resultNames.add(ps.name);
        }
        return resultNames;
    }

    // =========================================================================
    // Verification Main Method
    // =========================================================================
    public static void main(String[] args) {
        Map<String, Double> catalog = new HashMap<>();
        catalog.put("mobile", 85.0);
        catalog.put("monitor", 92.5); // Highest score
        catalog.put("mouse", 88.0);
        catalog.put("mousepad", 75.0);
        catalog.put("modem", 60.0);

        ThreadSafeScoredAutocompleteSystem system = new ThreadSafeScoredAutocompleteSystem(catalog);

        System.out.println("Suggestions for 'mo': " + system.getSuggestions("mo"));
        // Expected: [monitor, mouse, mobile] (Sorted by relevance score descending)

        System.out.println("Suggestions for 'mou': " + system.getSuggestions("mou"));
        // Expected: [mouse, mousepad]
    }
}
