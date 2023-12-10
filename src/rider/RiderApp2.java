package rider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RiderApp2 {

	public Map<String, TreeMap<Integer, String>> readFileAndCreateMap(String filePath) throws IOException{
		Map<String, TreeMap<Integer, String>> userMap = new HashMap<String, TreeMap<Integer,String>>();
		Files.lines(Paths.get(filePath))
		.skip(1)
		.forEach(line -> {
			String[] tokens = line.split("	");
			if(!userMap.containsKey(tokens[0])) {
				userMap.put(tokens[0], new TreeMap<Integer, String>());
			}
			TreeMap<Integer, String> timeMap = userMap.get(tokens[0]);
			timeMap.put(Integer.valueOf(tokens[2]), tokens[1]);
		});
		return userMap;
	}
	
	
	// S, S, C = 3
	public void mapSequence(Map<String, TreeMap<Integer, String>> userMap) {
		Map<String, Integer> seqMap = new HashMap<String, Integer>();
		for (String user: userMap.keySet()) {
			TreeMap<Integer, String> timeMap = userMap.get(user);
			if (timeMap.size() < 3)
				continue;
			List<Integer> times = timeMap.keySet().stream().collect(Collectors.toList());
			List<String> seqs = formSequences(times, timeMap);
			
		}
	}
	
	
	public List<String> formSequences(List<Integer> times, TreeMap<Integer, String> timeMap) {
		List<String> result = new ArrayList<String>();
		for(int i = 0; i < times.size() - 2; i++) {
			for (int j = i+1 ; j < times.size() - 1; j++ ) {
				for (int k = j + 1; k < times.size(); k++) {
					result.add(
							timeMap.get(times.get(i)) + "," + 	timeMap.get(times.get(j)) + "," + timeMap.get(times.get(k)));
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		String path = "/Users/rgupta30/old_laptop_bkup/bk4/tutorials/personal_git_hub/java-samples/src/rider/riders.txt";
		RiderApp2 obj = new RiderApp2();
		Map<String, TreeMap<Integer, String>> userMap = obj.readFileAndCreateMap(path);
		System.out.print(userMap);
	}

}
