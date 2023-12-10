package rider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SolutionUserVisit {
	
	public List<String> mostVisitPattern(String[] userName, String[] website, int[] timestamp) {
		Map<String, TreeMap<Integer, String>> map = new HashMap();
		for(int i = 0; i < timestamp.length; i++) {
			
			if (!map.containsKey(userName[i])) {
				map.put(userName[i], new TreeMap<Integer, String>());
			}
			
			TreeMap<Integer, String> timeMap = map.get(userName[i]);
			timeMap.put(timestamp[i], website[i]);
			
			map.put(userName[i], timeMap);
		}
		
		
		Map<String, Integer> seqMap = new HashMap<String, Integer>();
		for (String user: map.keySet()) {
			TreeMap<Integer, String> timeMap = map.get(user);
			if (timeMap.size() < 3) {
				continue;
			} else {
				List<Integer> times = new ArrayList<Integer>();
				for(int key: timeMap.keySet()) {
					times.add(key);
				}
				List<String> allSeqs = formAllSeq(times, timeMap);
				for(String seq: allSeqs) {
					if (!seqMap.containsKey(seq)) {
						seqMap.put(seq, 1);
					} else {
						int count = seqMap.get(seq);
						seqMap.put(seq, count + 1);
					}
				}
			}
		}
		
		System.out.print(seqMap);
		
		return null;
		
	}
	
	private List<String> formAllSeq(List<Integer> time, TreeMap<Integer, String> timeMap) {
		List<String> result = new ArrayList<String>();
		for(int i = 0 ; i < time.size() - 2; i++) {
			for (int j = i+1; j < time.size() - 1; j++) {
				for (int k = j+1; k < time.size(); k++) {
					
					result.add(timeMap.get(time.get(i)) + "->" + timeMap.get(time.get(j)) + " -> " + timeMap.get(time.get(k)));
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		SolutionUserVisit visit = new SolutionUserVisit();
		String[] names = {"joe","joe","joe","james","james","james","james","mary","mary","mary"};
		int[] ts = {1,2,3,1,2,3,7,8,9,10};
		String[] pages = {"home","about","career","home","cart","maps","home","home","about","career"};
		visit.mostVisitPattern(names, pages, ts);
	}

}
