package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaStreams {
	
	public static void main(String[] args) {
		// stream has multiple intermediate and one terminal functions
		// takes data from a source like array, list or collection, below it takes from range function
		// intermediate function -  always returns a stream e.g: filter, map, sorted
		// terminal function - always one terminal function per dtream e.g: reduce, collect, forEach.
		
		// print 1-9
		IntStream.
			range(1, 10).forEach(System.out::print);
		
		System.out.println("");
		
		// print 1-9 and skip upto 5
		IntStream.range(1,10).skip(5).forEach(x -> System.out.print(x));
		System.out.println("");
		
		// terminal function - sum
		int result = IntStream.range(1, 10).sum();
		System.out.println("sum::"+ result);
		
		//apply sort and get first element
		Stream.of("Axe", "Ace", "Ape", "Ball", "Boy").sorted().findFirst().ifPresent(System.out::print);
		System.out.println("");
		
		//same with list
		String[] str = {"Axe", "Ace", "Ape", "Ball", "Boy"};
		List<String> list = Arrays.asList(str);
		list.stream().sorted().findFirst().ifPresent(System.out::print);
		
		System.out.println("");
		
		// sort, filter and print
		System.out.println("Filtered & Sorted Elements");
		String[] names = {"Axe", "Ace", "Ape", "Ball", "Boy"};
		Arrays.stream(names).
			filter((s) -> {
				return s.startsWith("A");
			}).forEach(System.out::println);
			
		System.out.println("");
		System.out.println("Original:::"+ names.length);
		
		// map (to square) and find average
		Arrays.stream(new int[] {2,4,6,8}).map(x -> x*x).average().ifPresent(System.out::print);
		
		System.out.println("");
		
		// list -> map to convert lowercase, and then apply filter
		String[] namesArr = {"Axe", "Ace", "Ape", "Ball", "Boy"};
		List<String> namesList = Arrays.asList(namesArr);
		System.out.println("Converted to lower case , Filtered & Sorted Elements");
		namesList.stream().map(String::toLowerCase).filter(x -> x.startsWith("a")).forEach(System.out::println);;
		
		// collect function after filter
		System.out.println("\nCollector Function");
		List<String> outputList = namesList.stream().filter(x -> x.startsWith("A")).collect(Collectors.toList());
		outputList.forEach(System.out::println);
		
		// sum of int using sum function
		int sum = Arrays.stream(new int[] {2,4,6,8}).sum();
		System.out.println("sum::"+ sum);
		
		// use reduce function to sum of int
		int total = Stream.of(2,4,6,8).reduce(0, (a,b) -> a+b);
		int total1 = IntStream.of(2,4,6,8).sum();
		// 0 in reduce fn is the strating value, 
		// a is the total sum after every step and b is the 
		// fresh number  eerytime
		System.out.println("total::"+ total);
		System.out.println("total1::"+ total1);
		
		IntSummaryStatistics summary = IntStream.of(2,4,6,8).summaryStatistics();
		System.out.println("summary::"+ summary);
		
	}

}
