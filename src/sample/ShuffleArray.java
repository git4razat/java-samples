package sample;

public class ShuffleArray {

	private int[] shuffle(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int index = (int) (Math.random() * array.length);
			swap(array, i, index);
		}
		return array;
	}

	private int[] swap(int[] arr, int i, int j) {
		int temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
		return arr;
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7 };
		ShuffleArray shuffle = new ShuffleArray();
		shuffle.shuffle(array);
		for (int str : array) {
			System.out.print(str + " ");
		}
	}

}
