package jonathan.furminger.wordbuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Dictionary {

	private static final String FILENAME = "enable1_3-15.txt";
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	@SuppressWarnings("unchecked")
	private ArrayList<String>[] wordList = (ArrayList<String>[])new ArrayList[26]; 
	
	public Dictionary() {
		for(int i = 0; i <26; i++) {
			wordList[i] = new ArrayList<String>();
		}
		try {
			BufferedReader in = new BufferedReader(new FileReader(FILENAME));
			String word = in.readLine();
			while(word != null) {
				char letter = word.charAt(0);
				int list = ALPHABET.indexOf(letter);
				wordList[list].add(word);
				word = in.readLine();
			}
		in.close();
		}
		catch (FileNotFoundException e) {
			String message = "File " + FILENAME + " was not found.";
			JOptionPane.showMessageDialog(null, message);
		}
		catch (IOException e) {
			String message = "File " + FILENAME + " could not be opened.";
			JOptionPane.showMessageDialog(null, message);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
