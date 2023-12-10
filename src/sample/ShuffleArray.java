package sample;

public class ShuffleArray {

	// shold not be used, use new method listed below... shuffleNew();
	/*private int[] shuffle(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int index = (int) (Math.random() * array.length);
			swap(array, i, index);
		}
		return array;
	}*/

	private int[] swap(int[] arr, int i, int j) {
		int temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
		return arr;
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7 };
		ShuffleArray shuffle = new ShuffleArray();
		shuffle.shuffleNew(array);
		for (int str : array) {
			System.out.print(str + " ");
		}
	}
	
	// Fisher-Yates method of shuffling
	// o(n) and o(1) - time and space..
	// probability of finding random number in old method is not great...
	private int[] shuffleNew(int[] array)
	{
	    int n = array.length;
		for (int j = n - 1; j > 0; j--)
	    {
	        int i = (int) (Math.random() * j);
	        swap(array, i, j);
	    }
		return array;
	}

}
