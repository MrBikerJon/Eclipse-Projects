import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    
    	try {
    	
    		Scanner scanner = new Scanner(System.in);
    		String input = scanner.nextLine();
    	
    		MessageDigest digest = MessageDigest.getInstance("MD5");
    	
    		byte[] messageDigest = digest.digest(input.getBytes());
    	
    		BigInteger no = new BigInteger(1, messageDigest);
    	
    		String hashtext = no.toString(16);
    		while (hashtext.length() < 32) {
    			hashtext = "0" + hashtext;

    		}
    		System.out.println(hashtext);
    	
    	}
    	
    	catch (NoSuchAlgorithmException e) {
    		throw new RuntimeException(e);
    	}

    
    }
}