package sample;

import java.util.ArrayList;
import java.util.List;

public class LocalMinimaIndices {
	
	public static void main(String[] args) {
		LocalMinimaIndices obj = new LocalMinimaIndices();
		int[] arr = { 10, 20, 15, 14, 13, 25, 5, 4, 3 };
		System.out.print(obj.findLocalMinIndices(arr));
	}
	
	Integer[] findLocalMinIndices(int[] arr) {
		
		List<Integer> min = new ArrayList<Integer>();
		
		if (arr[0] <  arr[1])
			min.add(0);
		
		
		for (int i = 1; i < (arr.length -2); i++) {
			if (arr[i] < arr[i - 1] &&
					arr[i] < arr[i+1]) {
				min.add(i);
			} 
		}
		
		if(arr[arr.length - 1] < arr[arr.length - 2]) {
			min.add(arr.length - 1);
		}

		return min.stream().toArray(Integer[]::new);
	}

}
