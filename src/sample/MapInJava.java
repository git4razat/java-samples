package sample;

public class MapInJava {
	public static void main(String[] args) {

		MyMap<String, String> myMap = new MyMap<>();
		myMap.put("USA", "Washington DC");
		myMap.put("Nepal", "Kathmandu");
		myMap.put("India", "New Delhi");
		myMap.put("Australia", "Sydney");
	}
}

class MyMap<K, V> {
	private Entry<K, V>[] buckets;
	private static final int INITIAL_CAPACITY = 16; // 16
	private int size = 0;

	public MyMap() {
		this(INITIAL_CAPACITY);
	}

	public MyMap(int capacity) {
		this.buckets = new Entry[capacity];
	}

	public void put(K key, V value) {
		Entry<K, V> entry = new Entry<>(key, value, null);
		int bucket = (key.hashCode()) % INITIAL_CAPACITY;
		Entry<K, V> existing = buckets[bucket];
		if (existing == null) {
			buckets[bucket] = entry;
			size++;
		} else {
			// compare the keys see if key already exists
			while (existing.next != null) {
				if (existing.key.equals(key)) {
					existing.value = value;
					return;
				}
				existing = existing.next;
			}
			if (existing.key.equals(key)) {
				existing.value = value;
			} else {
				existing.next = entry;
				size++;
			}
		}
	}

	public V get(K key) {
		Entry<K, V> bucket = buckets[(key.hashCode()) % INITIAL_CAPACITY];
		while (bucket != null) {
			if (bucket.key.equals(key)) {
				return bucket.value;
			}
			bucket = bucket.next;
		}
		return null;
	}
}

class Entry<K, V> {
	final K key;
	V value;
	Entry<K, V> next;

	public Entry(K key, V value, Entry<K, V> next) {
		this.key = key;
		this.value = value;
		this.next = next;
	}
	// getters, equals, hashCode and toString
}
