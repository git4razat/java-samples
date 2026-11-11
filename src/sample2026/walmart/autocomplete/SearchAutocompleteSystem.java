package sample2026.walmart.autocomplete;

import java.util.*;

public class SearchAutocompleteSystem {

    private static class TrieNode {
        private Map<Character, TrieNode> children;
        //in case of lexicographical sort consider String.. means we just consider score.
        private List<String> topSuggestions;

        TrieNode() {
            children = new HashMap<>();
            topSuggestions = new ArrayList<>();
        }

        void updateSuggestions(String product) {
            topSuggestions.removeIf(p -> p.equals(product));

            // Maintain lexicographical order
            Collections.sort(topSuggestions);
            if (topSuggestions.size() > 3) {
                topSuggestions.remove(topSuggestions.size() - 1); // Keep top 3
            }
        }

        public List<String> getTopSuggestions() {
            return topSuggestions;
        }

        public Map<Character, TrieNode> getChildren() {
            return children;
        }
    }

    private TrieNode root;

    // Catalog Build Time (Constructor): O(N) Time Complexity: O(N* LlogN + N*L)
    // Space Complexity: O(N*L)
    // N number of input product array
    // L max length of a product string..
    public SearchAutocompleteSystem(String[] products) {
        if (products == null || products.length <= 0) {
            throw new IllegalArgumentException("Please procide a valid list of products");
        }
        this.root = new TrieNode();
        Arrays.sort(products);
        for (String product: products) {
            insert(product);
        }
    }

    public void insert(String product) {
        TrieNode current = root;
        for (char ch: product.toCharArray()) {
            current.getChildren().putIfAbsent(ch, new TrieNode());
            current = current.getChildren().get(ch);
            // pre-compute top suggestion including new product...
            current.updateSuggestions(product);
        }
    }

    //time complexity - o(p) and
    public List<String> getSuggestions(String prefix) {
        if (prefix ==null || prefix.isEmpty()) return Collections.emptyList();

        TrieNode current = root;
        for (char ch: prefix.toCharArray()) {
            TrieNode nextNode = current.getChildren().get(ch);
            if (nextNode == null) return Collections.emptyList();
            current = nextNode;
        }
        return current.getTopSuggestions();
    }

    // =========================================================================
    // Execution & Verification Main Method
    // =========================================================================
    public static void main(String[] args) {
        String[] catalog = {"mobile", "mouse", "moneypot", "monitor", "mousepad", "modem"};
        SearchAutocompleteSystem engine = new SearchAutocompleteSystem(catalog);

        System.out.println("Searching for prefix 'm': " + engine.getSuggestions("m"));
        // Expected: [mobile, modem, monitor] (Top 3 lexicographical)

        System.out.println("Searching for prefix 'mo': " + engine.getSuggestions("mo"));
        // Expected: [mobile, modem, monitor]

        System.out.println("Searching for prefix 'mou': " + engine.getSuggestions("mou"));
        // Expected: [mouse, mousepad]

        System.out.println("Searching for non-existent prefix 'xyz': " + engine.getSuggestions("xyz"));
        // Expected: []
    }

}
