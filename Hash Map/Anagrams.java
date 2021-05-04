

import java.io.*;
import java.util.*;

public class Anagrams {
	
	//initialize the primes array consisting of the first 26 prime numbers
	final Integer[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67,
			71, 73, 79, 83, 89, 97, 101
	};
	
	
	Map<Character, Integer> letterTable = new HashMap<>();
	
	Map<Long, ArrayList<String>> anagramTable = new HashMap<>();
	
	static int length = 0;
	static long key = 0;
	
	public Anagrams() {
		
		buildLetterTable();
		
	}
	
	//4 method buildlettertable
	private void buildLetterTable() {
		
		final Character[] letters = {
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
				'u', 'v', 'w', 'x', 'y', 'z'
		};
		for (int i = 0; i < 26; i++) 
		{
			letterTable.put(letters[i], primes[i]);
		}
		
	}
	
	//6 method addword
	private void addWord(String s) {
		
		s = s.toLowerCase();
		long hash = myHashCode(s);
		if (anagramTable.get(hash) != null) 
		{
			ArrayList<String> hashlist = anagramTable.get(hash);
			hashlist.add(s);
			anagramTable.put(hash, hashlist);
		}
		else {
			ArrayList<String> hasharray = new ArrayList<String>();
			hasharray.add(s);
			anagramTable.put(hash, hasharray);
		}
		
	}
	
	//5 method myhashcode
	private Long myHashCode(String s) {
		
		s = s.toLowerCase();
		char[] letters = s.toCharArray();
		long res = 1;
		
		for (int i =0; i <s.length(); i++) 
		{
			res *= letterTable.get(letters[i]); 
		}
		
		return res;
		
	}
	
	//3 method processfile
	public void processFile(String s) throws IOException {
		
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while((strLine = br.readLine()) != null) 
		{
			this.addWord(strLine);
		}
		br.close();
		
	}
	
	//7 method getmaxentries
	private ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries(){
		
		int max = 0;
		ArrayList<Map.Entry<Long, ArrayList<String>>> hasharray = new ArrayList<Map.Entry<Long, ArrayList<String>>>();
		//get the max
		for (Map.Entry<Long, ArrayList<String>> entry: anagramTable.entrySet()) 
		{
			if (entry.getValue().size() > max) {
				max = entry.getValue().size();
			}
		}
		//set the max
		for (Map.Entry<Long, ArrayList<String>> entry: anagramTable.entrySet()) 
		{
			
			if (entry.getValue().size() == max) {
				
				hasharray.add(entry);
				key = entry.getKey();
				
			}
		}
		
		length = max;
		
		return hasharray;
		
	}
	
	public static void main(String[] args) {
		
		Anagrams a = new Anagrams();
		final long startTime = System.nanoTime();
		try {
			
			a.processFile("words_alpha.txt");
			
			/* for test
			a.processFile("/Users/ruohuanxu/Desktop/words_alpha.txt");
			a.processFile("/Users/ruohuanxu/Desktop/words_alpha2.txt");
			*/
			
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double)estimatedTime / 1000000000 );
		System.out.println("Time: " + seconds);
		System.out.println("Key of max anagrams: " + key);
		System.out.println("List of max anagrams: " + maxEntries);
		System.out.println("Length of list of max anagrams: " + length);
		
	}

}
