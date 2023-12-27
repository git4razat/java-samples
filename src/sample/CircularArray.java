package sample;

public class CircularArray {
	
	int[] numArray;
	int size;
	
	int front,rear = -1;
	
	public CircularArray(int size) {
		this.size = size;
		numArray = new int[size];
	}
	
	
	
	// enqueue - write to tail
	
	public void enqueue(int data) {
		if (isFull()) {
			return;
		}
		
		else if (isEmpty()) {
			front = 0;
			rear = 0;
		} else {
			rear = (rear + 1) % size;
		}
		numArray[rear] = data;
		
	}
	
	public int dequeue() {
		int result = -1;
		if (isEmpty()) {
			return result;
		} else if (front == rear) { // only one element in queue
			result =  numArray[front];
			front = -1;
			rear = -1;
		} else {
			result =  numArray[front];
			front = (front + 1) % size;
		}
		return result;
	}
	
	public boolean isFull() {
		return (rear + 1) % size == front;
	}
	
	public boolean isEmpty() {
		return (front == -1 && rear == -1);
	}
	
	public static void main(String[] args) {
		
		CircularArray cb = new CircularArray(10);
		
		cb.enqueue(5);
        cb.enqueue(6);
        cb.enqueue(7);
        cb.enqueue(1);
        cb.enqueue(4);
        
        
     // Printing the elements
        System.out.println(
            "The elements are printed in the order :-");
        System.out.println(cb.dequeue());
        System.out.println(cb.dequeue());
        System.out.println(cb.dequeue());
        System.out.println(cb.dequeue());
        System.out.println(cb.dequeue());
		
		
	}

}
