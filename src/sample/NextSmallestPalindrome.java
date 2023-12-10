package sample;

import java.math.BigInteger;
import java.util.Arrays;

//left + mid + reversed(left)
/**
 * Details - https://www.youtube.com/watch?v=3COJ95juomM
 * 
 * 
 * Odd Cases
 * get the right and left substrings (middle not included).
 * check if the reversed(left substring) is greater than right substring. 
 * 		True: copy paste the reversed left substring in-place of right
 * 		False: increase the middle value by 1 and then do the same as above: For cases like 9 in middle it will become 0 and carry will be fwded...
 * 
 * 
 * Even Cases
 * find the left and right substrings
 * reversed(left) > right?
 * 		True: return left + reversed(left)
 * 		False: add 1 to left and do the same as above
 * 
 * 
 * Palindrome Cases
 * Same as even..
 */
		

public class NextSmallestPalindrome {
	
	
	private static void getNextPalindrome(String numberStr) {
        
        /** To handle the case when all digits in number are 9. */
        /*if((int)Math.log10(number+1)!=(int)Math.log10(number)) {
             System.out.println("All 9s");
             System.out.println("Next Palidrome for the number "+ number +" is: "+(number+2));
             return;
        }*/
		
		if (isAll9(numberStr)) {
            System.out.print("1");
            for (int i = 0; i < numberStr.length() - 1; i++)
                System.out.print("0");
            System.out.println("1");
            return;
        }

        //int[] array = getNumberArray(number);
		//String[] array = numberStr.split("");
		
		int[] array = getNumberArray(numberStr);
		 System.out.println("array:" + Arrays.toString(array));

        int length = array.length;

        // find the index of middle digit
        int mid = length/2;

        // A boolean variable to check if copy of left side to right is sufficient or not
        boolean leftSmaller = false;

        // end of left side is always 'mid -1'
        int i = mid - 1;

        // Beginning of right side depends if n is odd or even
        int j = (length%2)!=0 ? mid + 1 : mid;

        // move pointers in both left and right parts in respective directions, till they match..
        while(i >= 0 && array[i]==array[j]) {
             i--;
             j++;
        }


        /*
         * leftSmaller when the left side array small then right one
         * or left array is equal to right array (i<0)
         */
        if(i < 0 || array[i] < array[j]) {
             leftSmaller = true;
        }


        // Copy the mirror of left to right
        if(!leftSmaller) {
             while (i >= 0) {
                  array[j] = array[i];
                  i--;
                  j++;
             }
        } else {
             /* Case: When left side is small or equal to right side.
              * need to increment the middle element.
              * */
             i = mid - 1;
             int carry = 1;
             if(length%2==1) {
                  int temp = array[mid] + carry;
                  carry = temp/10;
                  array[mid] = temp%10;
                  j = mid+1;
             } else {
                  j = mid;
             }

             while(i>=0) {
                  int temp = array[i]+carry;
                  carry = temp/10;
                  temp = temp%10;
                  array[j] = temp;
                  array[i]  = temp;
                  i--;
                  j++;
             }
        }

        System.out.print("Next Palidrome for the number "+ numberStr +" is: ");
        printArray(array);
  }
	
	private static boolean isAll9(String num) {
		String[] strArr = num.split("");
		
		for (int i = 0; i < strArr.length; i++) {
			if (!strArr[i].equals("9"))
				return false; 
		}
		return true;
	}
	
	// convert string to numberarr
	  private static int[] getNumberArray(String str) {
		  String[] strArr = str.split("");
		  int[] numberArr = new int[str.length()];
		  for (int i =0; i < str.length() ; i++) {
			numberArr[i] = Integer.valueOf(strArr[i]);
		  }
		  return numberArr;
	  }
	
	
	
  /**
   * Convert number into int Array
   * @param number
   * @return int[] array
   */
  private static int[] getNumberArray(int number) {

        int length = (int)(Math.log10(number)+1);

        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
             int val = number%10;
             number = number/10;
             array[length-i-1] = val;
        }
        return array;
  }
  
  
  


  /**
   * Utility Method to print numbers.
   * @param number
   */
  private static void printArray(int[] number) {
        for(int value : number) {
             System.out.print(value);
        }
  }

  /**
   * Driver Method
   * @param args
   */
  public static void main(String[] args) {

        int number = 13932;
        getNextPalindrome(String.valueOf(number));
  }
}
