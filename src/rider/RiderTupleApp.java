package rider;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Ride {
	private String name;
	private String city;
	private int timeStamp;
	
	public Ride(String name, String city, int timeStamp) {
		super();
		this.name = name;
		this.city = city;
		this.timeStamp = timeStamp;
	}
	public String getName() {
		return name; 
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(int timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "Ride [name=" + name + ", city=" + city + ", timeStamp=" + timeStamp + "]";
	}
	
	
}


public class RiderTupleApp {
	
	public static void main(String[] args) {
		List<Ride> rides = new ArrayList<Ride>();
		rides.add(new Ride("Jamie", "City1", 4));
		rides.add(new Ride("Dana", "City1", 3));
		rides.add(new Ride("Jamie", "City4", 2));
		rides.add(new Ride("Dana", "City3", 2));
		rides.add(new Ride("Jamie", "City3", 3));
		rides.add(new Ride("Jamie", "City2", 1));
		rides.add(new Ride("Dana", "City4", 1));
		//rides.sort(null);
		rides.forEach(ride -> System.out.println(ride));
		//System.out.println(", " + rides);
		System.out.println("*****");
		
		Comparator<Ride> compareByName = Comparator
                .comparing(Ride::getName)
                .thenComparing(Ride::getTimeStamp);

		List<Ride> sortedRides = rides.stream()
            .sorted(compareByName)
            .collect(Collectors.toList());
		
		//System.out.println(", " + sortedRides);
		sortedRides.forEach(ride -> System.out.println(ride));
		
	}

}
