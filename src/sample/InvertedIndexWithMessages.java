package sample;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class InvertedIndexWithMessages {
	
	Map<String, HashSet<String>> invIndex;
	
	Map<String, String> documents;
	
	
	public InvertedIndexWithMessages(Map<String, String> documents) {
		invIndex = new HashMap();
		this.documents = documents;
		buildIndex(documents);
	}
	
	public static void main(String[] args) {
		Map<String, String> inputDocs = new HashMap<String, String>();
		inputDocs.put("id1", "The quick brown fox jumps over the lazy dog.");
		inputDocs.put("id2", "A brown fox is fast and the dog is lazy.");
		inputDocs.put("id3", "The sun is shining, and the weather is warm.");
		
		InvertedIndexWithMessages invIndexApp = new InvertedIndexWithMessages(inputDocs);
		
		System.out.println("invIndex:::" + invIndexApp.invIndex);
		//HashSet<String> docIds = invIndexApp.search("brown fox");
		/*System.out.println("docs for :: brown fox ::" + docIds.size());
		for(String docId: docIds) {
			System.out.println("id::" + docId + ":msg:" + invIndexApp.documents.get(docId));
		}*/
		
		
		//HashSet<String> docIds = invIndexApp.search("lazy weather");
		//sun shining
		HashSet<String> docIds = invIndexApp.search("brown AND dog");
		// try lazy and weather for intersection....
		System.out.println("docs for :: lazy weather ::" + docIds.size());
		for(String docId: docIds) {
			System.out.println("id::" + docId + ":msg:" + invIndexApp.documents.get(docId));
		}
	}
	
	public void buildIndex(Map<String, String> documents) {
		for(String docId: documents.keySet()) {
			String msg = documents.get(docId);
			indexDoc(docId, msg);
		}
	}
	
	public void indexDoc(String docId, String msg) {
		String[] words = msg.split("\\W+");
		for(String word:words){
            word = word.toLowerCase();
        	if (!invIndex.containsKey(word))
            	invIndex.put(word, new HashSet<String>());
            invIndex.get(word).add(docId);
        }
	}
	
	/*public boolean IsStopWord(String word) {
		List<String> list = Arrays.asList(new String[]{"a", "an", "the", "and", "but", "or", "in", "on", "at", "he", "she", "it", "they", "is", "are", "was", "were"});
		// also consider stemming and lemmatization... reduce the word to its root like caring to care...
		return list.indexOf(word) != -1;
	}*/
	
	public HashSet<String> search(String phrase) {
		List<String> words = Arrays.asList(phrase.split("\\W+"));
		boolean intersectionQuery = false;//for intersection
		if (words.indexOf("AND") != -1) {
			intersectionQuery = true;
		}
		

		HashSet<String> res = invIndex.get(words.get(0).toLowerCase());
		for(String word: words){
			System.out.println("word::" + word);
			if (intersectionQuery)
				// set intersection
				res.retainAll(invIndex.get(word.toLowerCase()));
			else
				// covers set union
				res.addAll(invIndex.get(word.toLowerCase()));
		}
		
        if(res.size()==0) {
            System.out.println("Not found");
        }
        return res;
        
	}
	
	/**
	 * public void find(String phrase){
        String[] words = phrase.split("\\W+");
        HashSet<Integer> res = new HashSet<Integer>(index.get(words[0].toLowerCase()));
        for(String word: words){
            res.retainAll(index.get(word));
        }

        if(res.size()==0) {
            System.out.println("Not found");
            return;
        }
        System.out.println("Found in: ");
        for(int num : res){
            System.out.println("\t"+sources.get(num));
        }
    }
	 */

}
