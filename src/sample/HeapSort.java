package sample;

public class HeapSort {

	public void sort(int[] arr) {
		int n = arr.length;
		// build heap (rearrange array);
		// leaf nodes in heap starts from n/2 to n
		// so heapify should happen between 1 to (n/2 -1)
		for (int i = n / 2 - 1; i >= 0; i--) {
			heapify(arr, n, i);
		}
		// maxHeap - delete
		for (int i = n - 1; i >= 0; i--) {
			swap(arr, i, 0);
			heapify(arr, i, 0);
		}
	}

	public void heapify(int[] arr, int n, int i) {
		int largest = i;
		int left = 2 * i;
		int right = (2 * i) + 1;
		if ((left < n) && (arr[left] > arr[largest])) {
			largest = left;
		}
		if (right < n && arr[right] > arr[largest]) {
			largest = right;
		}
		if (largest != i) {
			swap(arr, largest, i);
			heapify(arr, n, largest);
		}
	}

	public void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	static void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	// Driver program
	public static void main(String args[]) {
		int arr[] = { 12, 11, 13, 5, 6, 7 };
		//int n = arr.length;

		HeapSort ob = new HeapSort();
		ob.sort(arr);

		System.out.println("Sorted array is");
		printArray(arr);
	}
}