package sample;

public class MergeTwoSortedArrays {
	
	public static void main(String[] args) {
		int[] arr1 = {1,4,8,10,14,18};
		int[] arr2 = {2,3,5,12,15,16,20};
		int result[] = merge(arr1, arr2);
		printArray(result);
	}
	
	public static int[] merge(int[] arr1, int[] arr2) {
		int[] mergedArr = new int[arr1.length + arr2.length];
		int i = 0,j = 0,k = 0;

		// items for both arr1 & arr2
		while(i < arr1.length && j < arr2.length) {
			if(arr1[i] < arr2[j]) {
				mergedArr[k] = arr1[i];
				k++;
				i++;
			} else {
				mergedArr[k] = arr2[j];
				k++;
				j++;
			}
		}
		// remaining items of arr1
		while(i < arr1.length) {
			mergedArr[k] = arr1[i];
			k++;
			i++;
		}
		
		// remaining items of arr2
		while(j < arr2.length) {
			mergedArr[k] = arr2[j];
			k++;
			j++;
		}
		return mergedArr;
	}
	
	public static void printArray(int[] arr) {
		for(int i=0 ; i < arr.length; i++) {
			System.out.print(arr[i]+ " ");
		}
	}

}
