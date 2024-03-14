package sample;

public class CircularBuffer<E> {

    private static final int DEFAULT_CAPACITY = 8;

    private final int capacity;
    private final E[] data;
    private volatile int writeSequence, readSequence;

    @SuppressWarnings("unchecked")
    public CircularBuffer(int capacity) {
        this.capacity = (capacity < 1) ? DEFAULT_CAPACITY : capacity;
        this.data = (E[]) new Object[this.capacity];
        this.readSequence = 0;
        this.writeSequence = -1;
    }

    public boolean write(E element) {
        if (isFull()) {
        	return false;
        }
        int nextWriteSeq = writeSequence + 1;
        data[nextWriteSeq % capacity] = element;
        writeSequence++;
        return true;
    }

    public E read() {
    	if (isEmpty()) {
    		return null;
    	}
    	E nextValue = data[readSequence % capacity];
        readSequence++;
        return nextValue;
    }

    public int size() {
        return (writeSequence - readSequence) + 1;
    }

    public boolean isEmpty() {
        return writeSequence < readSequence;
    }

    public boolean isFull() {
        return size() >= capacity;
    }
}
