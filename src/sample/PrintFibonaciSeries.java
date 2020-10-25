package sample;

public class PrintFibonaciSeries {
	
	// In fibonacci series, next number is the sum of previous two numbers 
	// for example 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55 etc. 
	// The first two numbers of fibonacci series are 0 and 1.

	int n1 = 0;
	int n2 = 1;
	int n3 = 0;
	
	public static void main(String[] args) {
		PrintFibonaciSeries fb = new PrintFibonaciSeries();
		System.out.print(fb.n1+ " " + fb.n2);
		fb.printFibonaciTillCount(10);
	}
	
	void printFibonaciTillCount(int count) {
		if (count > 0) {
			n3 = n2 + n1;
			n1 = n2;
			n2 = n3;
			System.out.print(" "+ n3);
			printFibonaciTillCount(count - 1);
		}
	}

}
