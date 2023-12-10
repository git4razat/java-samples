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
import java.util.stream.Stream;

public class RidersApp {
	
	public static void main(String[] args) throws IOException {
		String path = "/Users/rgupta30/old_laptop_bkup/bk4/tutorials/personal_git_hub/java-samples/src/rider/riders.txt";
		RidersApp app = new RidersApp();
		Map<String, TreeMap<Integer, String>> ridersMap = app.readFileAndMapByRider(path);
		System.out.println(ridersMap);
		Map<String, Integer> seqMap = app.mapSequence(ridersMap);
		System.out.println(seqMap);
		
	}
	
	protected Map<String, TreeMap<Integer, String>> readFileAndMapByRider(String filePath) throws IOException {
		Map<String, TreeMap<Integer, String>> map = new HashMap();
		try (Stream<String> IStream = Files.lines(Paths.get(filePath)).skip(1)) {
			IStream.forEach(line -> {
				String[] tokens = line.split("	");
				System.out.println("tokens:" + tokens.length + ":line:" + line);
				if (!map.containsKey(tokens[0])) {
					map.put(tokens[0], new TreeMap<Integer, String>());
				}
				TreeMap<Integer, String> timeMap = map.get(tokens[0]);
				timeMap.put(Integer.valueOf(tokens[2]), tokens[1]);
				map.put(tokens[0], timeMap);
			});
		}
		return map;
	}
	
	protected Map<String, Integer> mapSequence(Map<String, TreeMap<Integer, String>> map) {
		Map<String, Integer> seqMap = new HashMap<String, Integer>();
		for (String user: map.keySet()) {
			TreeMap<Integer, String> timeMapForUser = map.get(user);
			if (timeMapForUser.size() < 3) {
				continue;
			} //else {
				//List<Integer> times = new ArrayList<Integer>();
				/*for(int key: timeMap.keySet()) {
					times.add(key);
				}*/
				List<Integer> timesForUser = timeMapForUser.keySet().stream().collect(Collectors.toList());
				List<String> allSeqsForUser = formAllSeq(timesForUser, timeMapForUser);
				for(String seq: allSeqsForUser) {
					if (!seqMap.containsKey(seq)) {
						seqMap.put(seq, 1);
					} else {
						int count = seqMap.get(seq);
						seqMap.put(seq, count + 1);
					}
				}
			//}
		}
		return seqMap;
	}
	
	protected List<String> formAllSeq(List<Integer> time, TreeMap<Integer, String> timeMap) {
		List<String> result = new ArrayList<String>();
		for(int i = 0 ; i < time.size() - 2; i++) {
			for (int j = i+1; j < time.size() - 1; j++) {
				for (int k = j+1; k < time.size(); k++) {
					result.add(timeMap.get(time.get(i)) + "," + timeMap.get(time.get(j)) + "," + timeMap.get(time.get(k)));
				}
			}
		}
		return result;
	}
} 
