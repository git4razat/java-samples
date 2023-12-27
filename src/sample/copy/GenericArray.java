package sample.copy;

public class GenericArray<T> {
	
	T[] genericArray;
	int size = 0;
	int index = 0;
	
	public GenericArray(int size) {
		this.size = size;
		genericArray = (T[]) new Object[size];
	}
	
	public void add(T data) {
		if (index >= size) {
			return;
		}
		genericArray[index++] = data;
	}
	
	public T get(int position) {
		return genericArray[position];
	}
	
	public static void main(String[] args) {
		
		GenericArray<Integer> genericType = new GenericArray<Integer>(5);
		genericType.add(1);
		genericType.add(2);
		genericType.add(3);
		genericType.add(4);
		//genericType.add(5);
		System.out.println("genericType.genericArray::" + genericType.genericArray);
		for (int i = 0; i < genericType.size; i++) {
			System.out.println(genericType.get(i));
		}
		
		
		GenericArray<String> genStringType = new GenericArray<String>(5);
		genStringType.add("A");
		genStringType.add("B");
		genStringType.add("C");
		genStringType.add("D");
		
		for (int i = 0; i < genStringType.size; i++) {
			System.out.println(genericType.get(i));
		}
		
	}

}
