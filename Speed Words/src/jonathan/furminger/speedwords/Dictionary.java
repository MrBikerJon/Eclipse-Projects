package jonathan.furminger.speedwords;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Dictionary {
	
	private static final String FILE_NAME = "/enable1_2-7.txt";
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	@SuppressWarnings("unchecked")
	private ArrayList<String>[] wordLists = (ArrayList<String>[])new ArrayList[26];

	public static void main(String[] args) {
		Dictionary dictionary = new Dictionary();
		String word = "Hello World";
		if(dictionary.isAWord(word)) {
			System.out.println(word + " is a word");
		} 
		else {
			System.out.println(word + " is not a word");
		}
	}
	
	public Dictionary() {
		for(int i = 0; i < ALPHABET.length(); i++) {
			wordLists[i] = new ArrayList<String>();
		}
		try {
			InputStream input = getClass().getResourceAsStream(FILE_NAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			String word = in.readLine();
			
			while(word != null) {
				char letter = word.charAt(0);
				int list = ALPHABET.indexOf(letter);
				wordLists[list].add(word);
				word = in.readLine();
			}
			in.close();
		}
		catch (FileNotFoundException e) {
			String message = "File: " + FILE_NAME + " was not found";
			JOptionPane.showMessageDialog(null, message);
		}
		catch(IOException e) {
			String message = "File: " + FILE_NAME + " could not be opened";
			JOptionPane.showMessageDialog(null, message);
		}
	}
	
	public boolean isAWord(String word) {
		boolean found = false;
		word = word.toUpperCase();
		char letter = word.charAt(0);
		int list = ALPHABET.indexOf(letter);
		int index = 0;
		String word2 = "";
		
		while(index < wordLists[list].size() 
				&& word2.compareTo(word) < 0 
				&& !found) {
			ArrayList<String> wordList = wordLists[list];
			word2 = wordList.get(index);
			if(word2.equals(word)) {
				found = true;
			}
			index++;
		}
		return found;
		
	}
	

}
