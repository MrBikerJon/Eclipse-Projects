package jonathan.furminger.wordbuilder;

import java.util.ArrayList;

public class Dictionary {

	private static final String FILENAME = "enable1_3-15.txt";
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	@SuppressWarnings("unchecked")
	private ArrayList<String>[] wordList = (ArrayList<String>[])new ArrayList[26]; 
	
	public Dictionary() {
		for(int i = 0; i <26; i++) {
			wordList[i] = new ArrayList<String>();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
