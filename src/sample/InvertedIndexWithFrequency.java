package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;


class TermData {
	private int termFrequency;
	private HashSet<TermPosting> termPostings;
	public TermData() {
		termPostings = new HashSet<TermPosting>();
	}
	public TermData(Integer termFrequency, HashSet<TermPosting> termPostings) {
		super();
		this.termFrequency = termFrequency;
		this.termPostings = termPostings;
	}
	public Integer getTermFrequency() {
		return termFrequency;
	}
	public void setTermFrequency(int termFrequency) {
		this.termFrequency = termFrequency;
	}
	public HashSet<TermPosting> getTermPostings() {
		return termPostings;
	}
	public void setTermPostings(HashSet<TermPosting> termPostings) {
		this.termPostings = termPostings;
	}
	@Override
	public String toString() {
		return "TermData [termFrequency=" + termFrequency + ", termPostings=" + termPostings + "]";
	}
	
	
}

class TermPosting {
	private int docId;
	private List<Integer> occurances;
	
	public TermPosting(int docId, List<Integer> occurances) {
		super();
		this.docId = docId;
		this.occurances = occurances;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(docId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TermPosting other = (TermPosting) obj;
		return Objects.equals(docId, other.docId);
	}

	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	public List<Integer> getOccurance() {
		return occurances;
	}
	public void setOccurance(List<Integer> occurances) {
		this.occurances = occurances;
	}

	@Override
	public String toString() {
		return "TermPosting [docId=" + docId + ", occurances=" + occurances + "]";
	}
	
	
}

public class InvertedIndexWithFrequency {
	
	Map<String, TermData> invIndex;
	
	Map<Integer, String> documents;
	
	
	public InvertedIndexWithFrequency(Map<Integer, String> documents) {
		invIndex = new TreeMap<String, TermData>();
		this.documents = documents;
		buildIndex(documents);
	}
	
	public static void main(String[] args) {
		Map<Integer, String> inputDocs = new HashMap<Integer, String>();
		inputDocs.put(1, "The quick brown fox jumps over the lazy dog.");
		inputDocs.put(2, "A brown fox is fast and the dog is lazy. But dog is loyal.");
		inputDocs.put(3, "The sun is shining, and the weather is warm. brown in color.");
		
		InvertedIndexWithFrequency invIndexApp = new InvertedIndexWithFrequency(inputDocs);
		
		System.out.println("invIndex:::" + invIndexApp.invIndex);
		
		List<TermPosting> termPostings = invIndexApp.search("brown OR dog NOT sun");
		// try lazy and weather for intersection....
		System.out.println("docs for :: lazy weather ::" + termPostings.size());
		for(TermPosting termPosting: termPostings) {
			System.out.println("id::" + termPosting.getDocId() + ":msg:" + invIndexApp.documents.get(termPosting.getDocId()));
		}
	}
	
	public void buildIndex(Map<Integer, String> documents) {
		for(int docId: documents.keySet()) {
			String msg = documents.get(docId);
			indexDoc(docId, msg);
		}
	}
	
	public void indexDoc(int docId, String msg) {
		String[] words = msg.split("\\W+");
		int count = 1;
		for(String word:words){
            word = word.toLowerCase();
        	if (!invIndex.containsKey(word))
            	invIndex.put(word, new TermData());
        	TermData termData = invIndex.get(word);
        	HashSet<TermPosting> postings = termData.getTermPostings();
        	List<TermPosting> postingList = postings.stream().filter(posting -> posting.getDocId() == docId).collect(Collectors.toList());
        	if (postingList.size() > 0) {
        		postingList.get(0).getOccurance().add(count);
        	} else {
        		List<Integer> occurances = new ArrayList<Integer>();
        		occurances.add(count);
        		termData.getTermPostings().add(new TermPosting(docId, occurances));
        		
        	}
        	int termFreq = termData.getTermFrequency();
        	termData.setTermFrequency(termFreq + 1);
        	
        	count++;
        }
	}
	
	public boolean IsStopWord(String word) {
		List<String> list = Arrays.asList(new String[]{"a", "an", "the", "and", "but", "or", "in", "on", "at", "he", "she", "it", "they", "is", "are", "was", "were", "not"});
		// also consider stemming and lemmatization... reduce the word to its root like caring to care...
		return list.indexOf(word) != -1;
	}
	
	public List<TermPosting> search(String query) {
		List<String> words = Arrays.asList(query.split("\\W+"));
		boolean intersectionQuery = false;
		boolean unionQuery = false;
		boolean removeQuery = false;
		
		TermData termData = invIndex.get(words.get(0).toLowerCase());
		HashSet<TermPosting> res = termData.getTermPostings();
		
		for(String word: words){
			
			if (word.toLowerCase().equals("and")) {
				intersectionQuery = true;
				continue;
			}
			
			if (word.toLowerCase().equals("or")) {
				unionQuery = true;
				continue;
			}
			
			if (word.toLowerCase().equals("not")) {
				removeQuery = true;
				continue;
			}
			
			
			System.out.println("word::" + word);
			/*if (IsStopWord(word.toLowerCase())) {
				continue;
			}*/
			if (intersectionQuery) {
				// set intersection
				res.retainAll(invIndex.get(word.toLowerCase()).getTermPostings());
				intersectionQuery = false;
			}	
			if (removeQuery) {
				res.removeAll(invIndex.get(word.toLowerCase()).getTermPostings());
				removeQuery = false;
			}
				
			else
				// covers set union
				res.addAll(invIndex.get(word.toLowerCase()).getTermPostings());
		}
		
        if(res.size()==0) {
            System.out.println("Not found");
        }
        return null;
        
	}
}

