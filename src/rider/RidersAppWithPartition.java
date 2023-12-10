package rider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RidersAppWithPartition {
	
	private String inputFilePath = "/Users/rgupta30/old_laptop_bkup/bk4/tutorials/personal_git_hub/java-samples/src/rider/riders.txt";
	
	private String partitionedFileDir = "/Users/rgupta30/old_laptop_bkup/bk4/tutorials/personal_git_hub/java-samples/src/rider/out";
	
	FileUtils fileUtils = new FileUtils();
	
	public static void main(String[] args) throws IOException {
		RidersAppWithPartition app = new RidersAppWithPartition();
		app.processRiders();
	}
	
	public void processRiders() throws IOException {
		partitionFileByRider(inputFilePath, partitionedFileDir);
		Map<String, TreeMap<Integer, String>> userMap = processPartitionedFilesAndCreateUserMap();
		System.out.println("==========================");
		System.out.println("userMap::" + userMap);
		Map<String, Integer> seqMap = mapSequence(userMap);
		System.out.println("==========================");
		System.out.println("seqMap::" + seqMap);
		System.out.println("==========================");
		fileUtils.deleteFiles(partitionedFileDir);
		System.out.println("files deleted");
	}
	
	protected void partitionFileByRider(String filePath, String outPath) throws IOException {
		// The try-with-resources statement is a try statement that declares one or more resources. 
		// A resource is an object that must be closed after the program is finished with it. 
		// The try-with-resources statement ensures that each resource is closed at the end of the statement.
		// Any object that implements java.lang.AutoCloseable, which includes all objects which implement java.io.Closeable, can be used as a resource.
		try (Stream<String> lFileStream = Files.lines(Paths.get(filePath)).skip(1)) {
			lFileStream.forEach(line -> {
				String[] tokens = line.split("	");
				String rider = tokens[0];
				File outputFile = new File(outPath + File.separator + rider + ".txt");
				fileUtils.dumpLineIntoFile(outputFile, line);
			});
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
	}
	
	protected Map<String, TreeMap<Integer, String>> processPartitionedFilesAndCreateUserMap() {
		Map<String, TreeMap<Integer, String>> userMap = new HashMap<String, TreeMap<Integer, String>>();
		try (Stream<Path> paths = Files.walk(Paths.get(partitionedFileDir)).filter(Files::isRegularFile)) {
		    paths
		        .forEach(file -> {
		        	TreeMap<Integer, String> timeMap = new TreeMap<Integer, String>();
		        	try (Stream<String> lines = Files.lines(file)) {
		    		    lines.forEach(line -> {
		    		    	String[] tokens = line.split("	");
		    		    	timeMap.put(Integer.valueOf(tokens[2]), tokens[1]);
		    		    	userMap.put(tokens[0], timeMap);
		    		    });
		        	} catch (IOException ex) {
		        		ex.printStackTrace();
		        	}
		        });
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return userMap;
	}
	
	protected Map<String, Integer> mapSequence(Map<String, TreeMap<Integer, String>> userMap) {
		Map<String, Integer> seqMap = new HashMap<String, Integer>();
		for (String user: userMap.keySet()) {
			TreeMap<Integer, String> timeMapForUser = userMap.get(user);
			if (timeMapForUser.size() < 3) {
				continue;
			} 
			List<Integer> timesForUser = timeMapForUser.keySet().stream().collect(Collectors.toList());
			List<String> allSeqsForUser = formAllSeqForUser(timesForUser, timeMapForUser);
			for(String seq: allSeqsForUser) {
				if (!seqMap.containsKey(seq)) {
					seqMap.put(seq, 1);
				} else {
					int count = seqMap.get(seq);
					seqMap.put(seq, count + 1);
				}
			}
		}
		return seqMap;
	}
	
	protected List<String> formAllSeqForUser(List<Integer> time, TreeMap<Integer, String> timeMap) {
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
