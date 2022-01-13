import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    
    	//declare variables
    	int testValue = 0;
    	ArrayList<Integer> myArrayList = new ArrayList<Integer>();
    	
    	//get input
    	Scanner scanner = new Scanner(System.in);
    	int n = scanner.nextInt();
    	scanner.nextLine();
    	String input = scanner.nextLine();
    	
    	//split the input into separate numbers and store in a string array
    	String[] tokens = input.split(" ");
    	int[] A = new int[tokens.length];
    	
    	// convert the string array to an integer array
    	for(int i = 0; i < n; i++) {
    		A[i] = Integer.parseInt(tokens[i]);
    	}
    	
    	//look for negative sub arrays
    	for(int j = 0; j < A.length; j++) {
    		for(int k = j; k < A.length; k++) {
    			//System.out.println("adding " + A[k] + " to testValue");
    			testValue += A[k]; 
    			//System.out.println("testValue is now " + testValue);
        		if(testValue < 0) {
        			//System.out.println("Found a negative sub array: " + testValue);
        			myArrayList.add(testValue);
    		}
    		//System.out.println(testValue);
    		//if(testValue < 0) {
    		//	System.out.println("Found a negative sub array: " + testValue);
    		//	myArrayList.add(testValue);
    		//}

    		}
    		testValue = 0;
    		//System.out.println("reset testValue to zero");
    	}
    	
    	//for(int l = 0; l < myArrayList.size(); l++) {
    	//	System.out.println(myArrayList.get(l));
    	//	}
    	System.out.println(myArrayList.size());
    	
    }
}